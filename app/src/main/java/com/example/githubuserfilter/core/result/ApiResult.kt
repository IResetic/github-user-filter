package com.example.githubuserfilter.core.result

sealed interface ApiResult<out T>
data class ApiSuccess<out T>(val data: T) : ApiResult<T>
data class ApiError<out T>(val type: ApiErrorType) : ApiResult<T>
