package com.example.quizappv2.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.quizappv2.databinding.ActivityQuizBinding
import com.example.quizappv2.model.Question
import com.example.quizappv2.viewmodel.QuizViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class QuizActivity : AppCompatActivity() {

    lateinit var binding: ActivityQuizBinding
    lateinit var quizViewModel: QuizViewModel
    lateinit var questionList: List<Question>

    companion object {
        var result = 0
        var totalQuestion = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)

        result = 0
        totalQuestion = 0

        quizViewModel = ViewModelProvider(this)
            .get(QuizViewModel::class.java)

        GlobalScope.launch(Dispatchers.Main) {
            quizViewModel.getQuestionsFromLiveData().observe(this@QuizActivity, Observer {
                if (it.size > 0) {
                    questionList = it
                    Log.i("TAGY", "Thís the 1st question: ${questionList[0]}")
                    binding.apply {
                        txtQuestion.text = questionList!![0].question
                        btn0.text = questionList!![0].option1
                        btn1.text = questionList!![0].option2
                        btn2.text = questionList!![0].option3
                        btn3.text = questionList!![0].option4
                    }
                }
            })
        }


        var i = 1
        binding.apply {
            nextBtn.setOnClickListener {
                val selectdOption = radiogroup?.checkedRadioButtonId
                if(selectdOption != -1){
                    val radbutton = findViewById<View>(selectdOption!!) as RadioButton

                    questionList.let{
                        if (i <it.size){
                            totalQuestion = it.size

                            if(radbutton.text.toString().equals(it[i-1].correct_option)){
                                result++;
                                txtResult?.text = "Correct Answer: $result"
                            }

                            txtQuestion.text = "Question ${i+1}: "+ it[i].question
                            btn0.text = it[i].option1
                            btn1.text = it[i].option2
                            btn2.text = it[i].option3
                            btn3.text = it[i].option4

                            if(i == it.size!!.minus(1)){
                                nextBtn.text = "Finish"
                            }
                            radiogroup?.clearCheck()
                            i++

                        }else{
                            if(radbutton.text.toString().equals(it[i-1].correct_option)){
                                result++
                                txtResult?.text="Correct Answer: "
                            }else{

                            }

                            val intent = Intent(this@QuizActivity, ResultsActivity::class.java)
                            startActivity(intent)
                            finish()
                        }
                    }
                }else{
                    Toast.makeText(this@QuizActivity,"Please correct one option",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
