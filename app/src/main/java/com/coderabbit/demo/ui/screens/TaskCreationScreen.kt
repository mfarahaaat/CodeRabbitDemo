package com.coderabbit.demo.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.coderabbit.demo.model.Task
import com.coderabbit.demo.model.TaskCategory
import com.coderabbit.demo.ui.components.CategoryDropdown
import com.coderabbit.demo.ui.components.TaskList
import com.coderabbit.demo.ui.theme.CodeRabbitDemoTheme

@Composable
fun TaskCreationScreen(modifier: Modifier = Modifier) {
    var taskName by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf(TaskCategory.HOME) }
    val tasks = remember { mutableStateListOf<Task>() }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = taskName,
                onValueChange = { taskName = it },
                label = { Text("Task name") },
                modifier = Modifier.weight(2f)
            )

            CategoryDropdown(
                selectedCategory = selectedCategory,
                onCategoryChange = { selectedCategory = it },
                modifier = Modifier.weight(0.5f)
            )

            Button(
                onClick = {
                    val trimmedTaskName = taskName.trim()
                    if (trimmedTaskName.isNotEmpty()) {
                        tasks.add(
                            Task(
                                name = trimmedTaskName,
                                category = selectedCategory
                            )
                        )
                        taskName = ""
                        selectedCategory = TaskCategory.HOME
                    }
                },
                modifier = Modifier.weight(0.5f)
            ) {
                Text("Add")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        TaskList(
            tasks = tasks,
            modifier = Modifier.weight(1f)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun TaskCreationScreenPreview() {
    CodeRabbitDemoTheme {
        TaskCreationScreen()
    }
}
