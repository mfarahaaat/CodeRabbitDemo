package com.coderabbit.demo

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * Composable that displays a simple to-do list screen with input, add, toggle, and delete functionality.
 *
 * Shows a header ("My To-Do List"), an input field with an "Add" button, and a scrollable list of to-do items.
 * The composable manages local UI state: the current input text and a mutable list of `TodoItem`s.
 * Tapping "Add" when the input is not blank appends a new `TodoItem` and clears the input.
 * Each list row exposes controls to toggle the item's `isDone` state and to remove the item from the list.
 *
 * @param modifier Optional [Modifier] applied to the root container.
 */
@Composable
fun TodoApp(modifier: Modifier = Modifier) {
    var text by remember { mutableStateOf("") }
    val todoList = remember { mutableStateListOf<TodoItem>() }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "My To-Do List",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(12.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            OutlinedTextField(
                value = text,
                onValueChange = { text = it },
                modifier = Modifier.weight(1f),
                placeholder = { Text("Enter task") }
            )

            Spacer(modifier = Modifier.width(8.dp))

            Button(onClick = {
                if (text.isNotBlank()) {
                    todoList.add(TodoItem(text))
                    text = ""
                }
            }) {
                Text("Add")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(todoList) { item ->
                TodoRow(
                    item = item,
                    onToggle = {
                        val index = todoList.indexOf(item)
                        todoList[index] = item.copy(isDone = !item.isDone)
                    },
                    onDelete = {
                        todoList.remove(item)
                    }
                )
            }
        }
    }
}

/**
 * Displays a single to-do item as a card row with a checkbox and a delete action.
 *
 * @param item The `TodoItem` to display (title and completion state).
 * @param onToggle Invoked when the item's completion checkbox is toggled.
 * @param onDelete Invoked when the item's Delete button is pressed.
 */
@Composable
fun TodoRow(
    item: TodoItem,
    onToggle: () -> Unit,
    onDelete: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = item.isDone,
                onCheckedChange = { onToggle() }
            )

            Text(
                text = item.title,
                modifier = Modifier.weight(1f),
                style = if (item.isDone)
                    MaterialTheme.typography.bodyLarge.copy(
                        textDecoration = androidx.compose.ui.text.style.TextDecoration.LineThrough
                    )
                else MaterialTheme.typography.bodyLarge
            )

            TextButton(onClick = onDelete) {
                Text("Delete")
            }
        }
    }
}