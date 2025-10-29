package com.todos.app.data.remote

import com.todos.app.data.model.Todo
import retrofit2.http.GET
import retrofit2.http.Path


interface TodoApi {
    @GET("todos")
    suspend fun getTodos(): List<Todo>

    @GET("todos/{id}")
    suspend fun getTodo(@Path("id") id: Int): Todo
}