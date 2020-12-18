package com.example.madlevel7task2.models

data class Quiz(
    val questions: List<Question>
)

data class Question(
    val question: String,
    val answers: List<String>,
    val correctAnswer: String
)