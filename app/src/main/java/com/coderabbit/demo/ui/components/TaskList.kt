package com.coderabbit.demo.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.coderabbit.demo.model.Task
import com.coderabbit.demo.model.TaskCategory
import com.coderabbit.demo.ui.theme.CodeRabbitDemoTheme

@Composable
fun TaskList(
    tasks: List<Task>,
    modifier: Modifier = Modifier
) {
    if (tasks.isEmpty()) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .padding(24.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "No tasks yet",
                style = MaterialTheme.typography.bodyLarge
            )
        }
        return
    }

    LazyColumn(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(tasks) { task ->
            Card(modifier = Modifier.fillMaxWidth()) {
                ListItem(
                    headlineContent = { Text(task.name) },
                    supportingContent = { Text(task.category.displayName) }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun TaskListPreview() {
    CodeRabbitDemoTheme {
        TaskList(
            tasks = listOf(
                Task(name = "Buy groceries", category = TaskCategory.HOME),
                Task(name = "Prepare sprint report", category = TaskCategory.WORK),
                Task(name = "Pick up toys", category = TaskCategory.KIDS)
            ),
            modifier = Modifier.padding(16.dp)
        )
    }
}
