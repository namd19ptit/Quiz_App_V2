package com.example.quizappv2.retrofit

import com.example.quizappv2.model.QuestionList
import retrofit2.Response
import retrofit2.http.GET

interface QuestionAPI {

    @GET("questionsapi.php")
    suspend fun getQuestion():Response<QuestionList>

}