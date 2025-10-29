package com.todos.app.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.todos.app.R
import com.todos.app.ui.model.TodoUi

@Composable
fun TodoList(
    items: List<TodoUi>,
    onItemClick: (TodoUi) -> Unit,
    modifier: Modifier = Modifier
) {
    if (items.isEmpty()) { EmptyView(stringResource(R.string.no_todos)) ; return }
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(12.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(items, key = { it.id }) { t ->
            TodoCard(item = t, onClick = { onItemClick(t) }, modifier = Modifier.fillMaxWidth())
        }
    }
}

@Composable
fun TodoDetailContent(todo: TodoUi) {
    Column(Modifier.fillMaxSize().padding(16.dp)) {
        SectionLabel(stringResource(R.string.section_todo_id, todo.id))
        Spacer(Modifier.height(4.dp))
        Text(
            todo.title,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onSurface
        )
        Spacer(Modifier.height(8.dp))
        StatusChip(todo.completed)
        Spacer(Modifier.height(12.dp))
        Text(
            stringResource(R.string.user_id_label, todo.userId),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}
