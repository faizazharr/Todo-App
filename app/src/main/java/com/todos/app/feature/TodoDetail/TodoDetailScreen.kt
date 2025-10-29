package com.todos.app.feature.TodoDetail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.todos.app.R
import com.todos.app.ui.component.ErrorView
import com.todos.app.ui.component.ListToolbar
import com.todos.app.ui.component.LoadingView
import com.todos.app.ui.component.TodoDetailContent
import com.todos.app.ui.model.TodoUi
import com.todos.app.ui.model.UiState

@Composable
fun TodoDetailScreenUI(
    id: Int,
    vm: TodoDetailViewModel,
    modifier: Modifier = Modifier
) {
    LaunchedEffect(id) { vm.observe(id) }
    val uiState by vm.uiState.collectAsState()

    when (val state = uiState) {
        is UiState.Loading -> LoadingView()
        is UiState.Error -> ErrorView(state.message, onRetry = { vm.observe(id) })
        is UiState.Success -> {
            val todo = state.data
            Column(
                modifier = modifier.fillMaxSize().padding(16.dp),
                verticalArrangement = Arrangement.Top
            ) {
                TodoDetailContent(todo)
                Spacer(Modifier.height(16.dp))
                Button(onClick = { vm.setCompleted(todo.id, !todo.completed) }) {
                    Text(
                        if (todo.completed) stringResource(R.string.mark_as_pending) else stringResource(R.string.mark_as_completed),
                        style = MaterialTheme.typography.labelLarge
                    )
                }
            }
        }
    }
}