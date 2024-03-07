package com.example.quizappv2.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResult
import com.example.quizappv2.R
import com.example.quizappv2.databinding.ActivityQuizBinding
import com.example.quizappv2.databinding.ActivityResultsBinding

class ResultsActivity : AppCompatActivity() {
    lateinit var binding: ActivityResultsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.txtAnswer.text = "Your Score is: "+QuizActivity.result +"/"+QuizActivity.totalQuestion
        binding.btnBack.setOnClickListener {
            var intent = Intent(this@ResultsActivity, QuizActivity::class.java)
            startActivity(intent)
        }
    }
}