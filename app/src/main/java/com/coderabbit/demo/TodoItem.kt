package com.coderabbit.demo

import java.util.UUID

data class TodoItem(
    val title: String,
    val isDone: Boolean = false,
    val id: String = UUID.randomUUID().toString()
)