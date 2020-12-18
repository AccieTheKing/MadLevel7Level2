package com.example.madlevel7task2.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.madlevel7task2.models.Quiz
import com.example.madlevel7task2.repository.QuizRepository
import kotlinx.coroutines.launch

class QuizViewModel(application: Application) : AndroidViewModel(application) {
    private val quizRepository = QuizRepository()

    val quiz = quizRepository.quiz
    val createSuccess = quizRepository.createSuccess

    fun getQuiz() {
        viewModelScope.launch {
            try {
                quizRepository.getQuiz()
            } catch (e: QuizRepository.QuizRetrievalError) {
                val errorMsg = "Something went wrong while saving profile"
                Log.e("Error firebase", e.message ?: errorMsg)
            }
        }
    }

    fun createQuiz(quiz: Quiz) {
        viewModelScope.launch {
            try {
                quizRepository.createQuiz(quiz)
            } catch (e: QuizRepository.QuizSaveError) {
                val errorMsg = "Something went wrong while saving profile"
                Log.e("Error firebase", e.message ?: errorMsg)
            }
        }
    }

}