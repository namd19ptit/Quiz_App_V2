package com.example.quizappv2.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.quizappv2.model.QuestionList
import com.example.quizappv2.repository.QuizRepository

class QuizViewModel:ViewModel() {
    var repository: QuizRepository = QuizRepository()

    lateinit var questionsLiveData: LiveData<QuestionList>

    init{
        questionsLiveData = repository.getQuestionFromAPI()
    }

    fun getQuestionsFromLiveData():LiveData<QuestionList>{
        return questionsLiveData
    }
}