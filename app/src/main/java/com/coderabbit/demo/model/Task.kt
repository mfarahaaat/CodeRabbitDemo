package com.coderabbit.demo.model

data class Task(
    val name: String,
    val category: TaskCategory = TaskCategory.HOME
)
