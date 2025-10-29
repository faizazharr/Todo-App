package com.todos.app.data.mapper

import com.todos.app.domain.model.Todo as DomainTodo
import com.todos.app.data.model.Todo as DtoTodo

fun DtoTodo.toDomain(): DomainTodo = DomainTodo(
    id = id,
    userId = userId,
    title = title,
    completed = completed
)

