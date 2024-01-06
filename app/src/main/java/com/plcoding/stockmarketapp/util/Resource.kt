package com.plcoding.stockmarketapp.util

/*
This structure is common in Kotlin when dealing with asynchronous operations, especially in Android development using coroutines and Retrofit for network calls. It encapsulates the result in a Resource class, which is a common pattern for handling success and error states in API calls.
 */
sealed class Resource<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T?): Resource<T>(data)
    class Error<T>(message: String, data: T? = null): Resource<T>(data, message)
    class Loading<T>(val isLoading: Boolean = true): Resource<T>(null)
}
