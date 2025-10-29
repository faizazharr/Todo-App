package com.todos.app.data.repository

import com.todos.app.domain.model.Todo
import com.todos.app.domain.repository.TodoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FakeTodoRepository @Inject constructor() : TodoRepository {
    private val state = MutableStateFlow(
        listOf(
            Todo(id = 1, userId = 1, title = "Buy milk", completed = false),
            Todo(id = 2, userId = 1, title = "Walk the dog", completed = true),
            Todo(id = 3, userId = 2, title = "Read a book", completed = false)
        )
    )

    override fun getTodos(): Flow<List<Todo>> = state

    override fun getTodoById(id: Int): Flow<Todo> = state.map { list ->
        list.first { it.id == id }
    }

    override suspend fun setCompleted(id: Int, completed: Boolean): Todo {
        val updated = state.value.map { if (it.id == id) it.copy(completed = completed) else it }
        state.value = updated
        return updated.first { it.id == id }
    }
}

