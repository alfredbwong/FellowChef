package android.com.fellowchef.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.room.util.StringUtil

open class BaseViewModel : ViewModel() {
    fun clearToast() {
        isShowToast.value = false
        toastErrorMessage.value = ""
    }

    var isShowToast = MutableLiveData<Boolean>()
    var toastErrorMessage = MutableLiveData<String>()

}