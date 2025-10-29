package com.todos.app.domain.usecase

import com.todos.app.domain.model.Todo
import com.todos.app.domain.repository.TodoRepository
import kotlinx.coroutines.flow.Flow

/** Use case to stream the list of todos. */
class GetTodosUseCase(
    private val repository: TodoRepository
) {
    operator fun invoke(): Flow<List<Todo>> = repository.getTodos()
}

