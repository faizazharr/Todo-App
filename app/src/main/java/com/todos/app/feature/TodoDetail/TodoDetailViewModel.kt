package com.todos.app.feature.TodoDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.todos.app.domain.usecase.GetTodoByIdUseCase
import com.todos.app.domain.usecase.SetTodoCompletedUseCase
import com.todos.app.ui.mapper.toUi
import com.todos.app.ui.model.TodoUi
import com.todos.app.ui.model.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class TodoDetailViewModel @Inject constructor(
    private val getTodoById: GetTodoByIdUseCase,
    private val setTodoCompleted: SetTodoCompletedUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<TodoUi>>(UiState.Loading)
    val uiState: StateFlow<UiState<TodoUi>> = _uiState.asStateFlow()

    private var observeJob: Job? = null

    fun observe(id: Int) {
        observeJob?.cancel()
        observeJob = viewModelScope.launch {
            try {
                _uiState.value = UiState.Loading
                getTodoById(id).collect { todo ->
                    _uiState.value = UiState.Success(todo.toUi())
                }
            } catch (t: Throwable) {
                _uiState.value = UiState.Error(t.message ?: "Unknown error")
            }
        }
    }

    fun setCompleted(id: Int, completed: Boolean) {
        viewModelScope.launch {
            try {
                setTodoCompleted(id, completed)
                // Flow observation will emit updated item; no manual state update needed
            } catch (t: Throwable) {
                _uiState.value = UiState.Error(t.message ?: "Unknown error")
            }
        }
    }
}

