package com.engie.domain.util


/**
 * A generic class that holds data and status.
 */
data class Resource<out T>(
    val status: Status,
    val data: T?,
    val message: String
) {
    companion object {

        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data, "")
        }

        fun <T> error(message: String, data: T? = null): Resource<T> {
            return Resource(Status.ERROR, data, message)
        }

        fun <T> loading(data: T? = null): Resource<T> {
            return Resource(Status.LOADING, data, "")
        }
    }

    fun isSuccess(): Boolean =
        status == Status.SUCCESS

    fun isLoading(): Boolean =
        status == Status.LOADING

    fun isError(): Boolean =
        status == Status.ERROR
}

enum class Status {

    SUCCESS,

    ERROR,

    LOADING
}