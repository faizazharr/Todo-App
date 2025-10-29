package com.todos.app.domain.usecase

import com.todos.app.domain.model.Todo
import com.todos.app.domain.repository.TodoRepository
import javax.inject.Inject

class SetTodoCompletedUseCase @Inject constructor(
    private val repository: TodoRepository
) {
    suspend operator fun invoke(id: Int, completed: Boolean): Todo = repository.setCompleted(id, completed)
}

