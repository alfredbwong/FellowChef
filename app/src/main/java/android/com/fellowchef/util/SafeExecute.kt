package android.com.fellowchef.util

import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.Call
import retrofit2.Response
import java.io.IOException

fun <T> Call<T>.safeExecute(): Response<T> {
    return try {
        this.execute()
    } catch (e: IOException) {
        Response.error<T>(400,
                "Error: No Network".toResponseBody("text/plain".toMediaTypeOrNull()))
    }
}