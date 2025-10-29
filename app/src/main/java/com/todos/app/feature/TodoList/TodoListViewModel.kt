package com.todos.app.feature.TodoList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.todos.app.domain.usecase.GetTodosUseCase
import com.todos.app.ui.mapper.toUi
import com.todos.app.ui.model.TodoUi
import com.todos.app.ui.model.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

@HiltViewModel
class TodoListViewModel @Inject constructor(
    private val getTodos: GetTodosUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<TodoUi>>>(UiState.Loading)
    val uiState: StateFlow<UiState<List<TodoUi>>> = _uiState.asStateFlow()

    val query = MutableStateFlow("")
    val showOnlyCompleted = MutableStateFlow(false)

    private var loadJob: Job? = null

    init {
        refresh()
    }

    fun refresh() {
        loadJob?.cancel()
        loadJob = viewModelScope.launch {
            try {
                _uiState.value = UiState.Loading
                combine(
                    getTodos(),
                    query,
                    showOnlyCompleted
                ) { list, q, only ->
                    list.filter { (q.isBlank() || it.title.contains(q, ignoreCase = true)) && (!only || it.completed) }
                }
                    .map { it.map { d -> d.toUi() } }
                    .collect { filtered -> _uiState.value = UiState.Success(filtered) }
            } catch (t: Throwable) {
                _uiState.value = UiState.Error(t.message ?: "Unknown error")
            }
        }
    }

    fun onQueryChange(value: String) { query.value = value }
    fun onToggleCompleted(value: Boolean) { showOnlyCompleted.value = value }
}
