package android.com.fellowchef.repository.models

import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import kotlinx.coroutines.*
import android.com.fellowchef.repository.models.Failure
import android.com.fellowchef.repository.models.Resource
import android.com.fellowchef.repository.models.Response
import android.util.Log
import androidx.lifecycle.MutableLiveData


abstract class NetworkResource<T>(private val viewModelScope: CoroutineScope, private val shouldSaveToDisk : Boolean ){
    private val result = MediatorLiveData<Resource<T>>()
    init{
        launch()
    }

    @WorkerThread
    abstract suspend fun loadFromDisk(): LiveData<T>
    @MainThread
    abstract suspend fun shouldFetch(data : T?) : Boolean
    @WorkerThread
    abstract suspend fun fetchData() : Response<T>
    @WorkerThread
    abstract suspend fun saveToDisk(data: T): Boolean

    private fun launch() {
        viewModelScope.launch{
            val dataFromDisk = withContext(Dispatchers.IO){
                loadFromDisk()
            }
            if (shouldFetch(dataFromDisk.value)){

                // re-attach the disk source and dispatch a loading value,
                result.addSource(dataFromDisk) { newData ->
                    setValue(Resource.loading(newData))
                }

                // remove the source before the fetch as disk source can't be dispatched twice
                // in case of network failure,
                result.removeSource(dataFromDisk)
                // start network fetch,
                val fetchTask = async(Dispatchers.IO) { fetchData() }
                when (val response = fetchTask.await()) {
                    is Success -> {
                        if (shouldSaveToDisk) {
                            // save new data to disk and dispatch fresh disk value,
                            withContext(Dispatchers.IO) {
                                saveToDisk(response.data)
                            }

                            val diskResponse = withContext(Dispatchers.IO) { loadFromDisk() }

                            // add latest disk source and send success,
                            result.addSource(diskResponse) { newData ->
                                setValue(Resource.success(newData))
                            }
                        } else {
                            result.addSource(MutableLiveData(response.data)) { newData ->
                                setValue(Resource.success(newData))
                            }
                        }

                    }
                    is Failure -> {
                        // re-use the disk data and send the error response,
                        result.addSource(dataFromDisk) { newData ->
                            setValue(Resource.error(response.message, newData))
                        }
                    }
                }

            } else {
                // re-use disk source and send a success value,
                result.addSource(dataFromDisk) { data ->
                    setValue(Resource.success(data))
                }
            }

        }


    }

    @MainThread
    fun setValue(newValue : Resource<T>){
        if (newValue != result.value){
            result.value = newValue
        }
    }


    fun asLiveData(): LiveData<Resource<T>> = result
}