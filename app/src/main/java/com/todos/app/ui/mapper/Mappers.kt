package com.todos.app.ui.mapper

import com.todos.app.domain.model.Todo as DomainTodo
import com.todos.app.ui.model.TodoUi

fun DomainTodo.toUi(): TodoUi = TodoUi(
    id = id,
    title = title,
    completed = completed,
    userId = userId
)

