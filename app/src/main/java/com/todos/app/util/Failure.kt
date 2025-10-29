package com.todos.app.util

sealed class Failure(open val message: String? = null, open val cause: Throwable? = null) {
    data class Network(override val message: String? = null, override val cause: Throwable? = null) : Failure(message, cause)
    data class NotFound(override val message: String? = null) : Failure(message)
    data class Unknown(override val message: String? = null, override val cause: Throwable? = null) : Failure(message, cause)
}
