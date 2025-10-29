package com.todos.app.feature.TodoList

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.todos.app.R
import com.todos.app.ui.component.EmptyView
import com.todos.app.ui.component.ErrorView
import com.todos.app.ui.component.ListToolbar
import com.todos.app.ui.component.LoadingView
import com.todos.app.ui.component.TodoList
import com.todos.app.ui.model.UiState
import com.todos.app.ui.model.TodoUi

@Composable
fun TodoListScreenUI(
    onSelect: (Int) -> Unit,
    vm: TodoListViewModel,
    modifier: Modifier = Modifier
) {
    val uiState by vm.uiState.collectAsState()
    val query by vm.query.collectAsState()
    val showOnlyCompleted by vm.showOnlyCompleted.collectAsState()

    when (uiState) {
        is UiState.Loading -> LoadingView()
        is UiState.Error -> ErrorView((uiState as UiState.Error).message, onRetry = { vm.refresh() })
        is UiState.Success -> {
            val data = (uiState as UiState.Success<List<TodoUi>>).data
            Column(modifier = modifier.fillMaxSize()) {
                ListToolbar(
                    query = query,
                    onQueryChange = vm::onQueryChange,
                    showOnlyCompleted = showOnlyCompleted,
                    onToggleCompleted = vm::onToggleCompleted
                )
                if (data.isEmpty()) {
                    EmptyView(stringResource(R.string.no_data))
                } else {
                    TodoList(
                        items = data,
                        onItemClick = { onSelect(it.id) },
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    }
}