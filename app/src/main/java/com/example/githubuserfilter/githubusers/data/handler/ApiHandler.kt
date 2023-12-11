package com.example.githubuserfilter.githubusers.data.handler

import com.example.githubuserfilter.core.result.ApiError
import com.example.githubuserfilter.core.result.ApiErrorType
import com.example.githubuserfilter.core.result.ApiResult
import com.example.githubuserfilter.core.result.ApiSuccess
import retrofit2.Response
import java.net.SocketTimeoutException

inline fun <reified T> handleApi(execute: () -> Response<T>): ApiResult<T> {
    return try {
        val response = execute()
        val body = response.body()

        if (response.isSuccessful) {
            ApiSuccess(body ?: Unit as T)
        } else {
            try {
                val errorBody = response.errorBody()?.string().orEmpty()
                // val code = JSONObject(errorBody).getJSONObject("error").getInt("code")
                ApiError(type = ApiErrorType.SERVER_ERROR)
            } catch (e: Exception) {
                ApiError(type = ApiErrorType.EXCEPTION)
            }
        }
    } catch (e: SocketTimeoutException) {
        ApiError(ApiErrorType.CONNECTION_TIME_OUT)
    } catch (e: Exception) {
        ApiError(ApiErrorType.EXCEPTION)
    }
}
