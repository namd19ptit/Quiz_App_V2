package com.example.quizappv2.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.quizappv2.model.QuestionList
import com.example.quizappv2.retrofit.QuestionAPI
import com.example.quizappv2.retrofit.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.create

class QuizRepository {
    var questionAPI: QuestionAPI
    init{
        questionAPI = RetrofitInstance().getRetrofitInstance()
            .create(QuestionAPI::class.java)
    }

    fun getQuestionFromAPI():LiveData<QuestionList>{
        var data = MutableLiveData<QuestionList>()

        var questionList: QuestionList
        GlobalScope.launch(Dispatchers.IO){
            // Return the Response
            var response = questionAPI.getQuestion()

            if(response != null){
                questionList= response.body()!!
                data.postValue(questionList)
                Log.i("TAGY","" +data.value)
            }
        }
        return data
    }
}