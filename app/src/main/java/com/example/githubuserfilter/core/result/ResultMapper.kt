package com.example.githubuserfilter.core.result

suspend fun <T, C> ApiResult<T>.mapResult(mapper: suspend (T) -> C?): ApiResult<C> {
    return when (this) {
        is ApiError -> ApiError(type)
        is ApiSuccess -> {
            try {
                val mappingResult = mapper(data)
                if (mappingResult == null) ApiError(ApiErrorType.EXCEPTION) else ApiSuccess(mappingResult)
            } catch (e: Exception) {
                ApiError(ApiErrorType.EXCEPTION)
            }
        }
    }
}
