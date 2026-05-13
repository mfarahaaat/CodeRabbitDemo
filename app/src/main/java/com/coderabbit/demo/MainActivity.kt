package com.coderabbit.demo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.coderabbit.demo.model.TaskCategory
import com.coderabbit.demo.ui.components.TaskInput
import com.coderabbit.demo.ui.theme.CodeRabbitDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CodeRabbitDemoTheme {
                var taskName by remember { mutableStateOf("") }
                var selectedCategory by remember { mutableStateOf(TaskCategory.HOME) }
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TaskInput(
                        taskName = taskName,
                        selectedCategory = selectedCategory,
                        onNameChange = { taskName = it },
                        onCategoryChange = { selectedCategory = it },
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}
