package com.todos.app.data.repository

import com.todos.app.data.mapper.toDomain
import com.todos.app.data.remote.TodoApi
import com.todos.app.domain.model.Todo
import com.todos.app.domain.repository.TodoRepository as DomainRepo
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

@Singleton
class NetworkTodoRepository @Inject constructor(
    private val api: TodoApi,
    @IoDispatcher private val io: CoroutineDispatcher
) : DomainRepo {

    private val scope = CoroutineScope(SupervisorJob() + io)
    private val _state = MutableStateFlow<List<Todo>>(emptyList())
    private val state = _state.asStateFlow()
    @Volatile private var loaded = false

    override fun getTodos(): Flow<List<Todo>> {
        ensureLoaded()
        return state
    }

    override fun getTodoById(id: Int): Flow<Todo> {
        ensureLoaded()
        return state.map { list -> list.first { it.id == id } }
    }

    override suspend fun setCompleted(id: Int, completed: Boolean): Todo {
        val updated = state.value.map { if (it.id == id) it.copy(completed = completed) else it }
        _state.value = updated
        return updated.first { it.id == id }
    }

    private fun ensureLoaded() {
        if (loaded) return
        loaded = true
        scope.launch {
            runCatching { api.getTodos().map { it.toDomain() } }
                .onSuccess { _state.value = it }
                .onFailure { /* keep empty or handle error via another channel */ }
        }
    }
}

