package com.example.githubuserfilter.core.result

enum class ApiErrorType {
    SERVER_ERROR,
    NO_INTERNET_CONNECTION,
    CONNECTION_TIME_OUT,
    EXCEPTION,
    UNKNOWN_ERROR
}

inline fun <T> ApiResult<T>.onSuccess(action: (T) -> Unit): ApiResult<T> {
    if (this is ApiSuccess) {
        action(this.data)
        return this
    }
    return this
}

inline fun <T> ApiResult<T>.onError(action: (ApiErrorType?) -> Unit): ApiResult<T> {
    if (this is ApiError) {
        action(this.type)
        return this
    }
    return this
}
