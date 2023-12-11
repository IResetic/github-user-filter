package com.example.githubuserfilter.githubusers.data.handler

import android.util.Log
import com.example.githubuserfilter.core.result.ApiError
import com.example.githubuserfilter.core.result.ApiErrorType
import com.example.githubuserfilter.core.result.ApiResult
import com.example.githubuserfilter.core.result.ApiSuccess
import org.json.JSONObject
import retrofit2.Response
import java.net.ConnectException
import java.net.SocketTimeoutException

inline fun <reified T> handleApi(execute: () -> Response<T>): ApiResult<T> {
    return try {
        val response = execute()
        val body = response.body()
        Log.d("TEST_RESPONSE", "${response.message()}")

        if (response.isSuccessful) {
            ApiSuccess(body ?: Unit as T)
        } else {
            try {
                val errorBody = response.errorBody()?.string().orEmpty()
                val code = JSONObject(errorBody).getJSONObject("error").getInt("code")
                Log.d("TEST_ERROR", "$code")
                ApiError(type = ApiErrorType.SERVER_ERROR)
            } catch (e: Exception) {
                Log.d("TEST_EXCEPTION", "EXCEPTION")
                ApiError(type = ApiErrorType.EXCEPTION)
            }
        }
    } catch (e: SocketTimeoutException) {
        ApiError(ApiErrorType.CONNECTION_TIME_OUT)
    } catch (e: Exception) {
        Log.d("TEST_EXCEPTION", "${e.message}  ::: ${e.localizedMessage}  ::: ${e.cause}")
        ApiError(ApiErrorType.EXCEPTION)
    }
}
