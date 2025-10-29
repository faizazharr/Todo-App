package com.todos.app.domain.repository

import com.todos.app.domain.model.Todo
import kotlinx.coroutines.flow.Flow

interface TodoRepository {
    fun getTodos(): Flow<List<Todo>>
    fun getTodoById(id: Int): Flow<Todo>
    suspend fun setCompleted(id: Int, completed: Boolean): Todo
}
