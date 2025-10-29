package com.todos.app.domain.usecase

import com.todos.app.domain.model.Todo
import com.todos.app.domain.repository.TodoRepository
import kotlinx.coroutines.flow.Flow

/** Use case to stream a single Todo by id. */
class GetTodoByIdUseCase(
    private val repository: TodoRepository
) {
    operator fun invoke(id: Int): Flow<Todo> = repository.getTodoById(id)
}

