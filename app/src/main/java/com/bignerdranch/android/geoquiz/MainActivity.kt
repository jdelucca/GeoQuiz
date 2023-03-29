package com.bignerdranch.android.geoquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.bignerdranch.android.geoquiz.databinding.ActivityMainBinding
import androidx.lifecycle.*
import androidx.activity.viewModels

private const val TAG = "MainActivity"

class  MainActivity : AppCompatActivity() {

    private val quizViewModel: QuizViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate(Bundle?) called")
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d(TAG, "Got a QuizViewModel: $quizViewModel")

        binding.nextButton.setOnClickListener{view: View ->
            quizViewModel.moveToNext()
            updateQuestion()
        }

        binding.trueButton.setOnClickListener{view: View ->
            checkAnswer(true)
        }
        binding.falseButton.setOnClickListener{view: View ->
            checkAnswer(false)
        }
        updateQuestion()
    }
    override fun onStart(){
        super.onStart()
        Log.d(TAG, "onStart")
    }
    override fun onResume(){
        super.onResume()
        Log.d(TAG, "onResume")
    }
    override fun onPause(){
        super.onPause()
        Log.d(TAG, "onPause")
    }
    override fun onStop(){
        super.onStop()
        Log.d(TAG, "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy")
    }

    private fun updateQuestion() {
        val questionTextResId = quizViewModel.currentQuestionText
        binding.questionTextView.setText(questionTextResId)
    }

    private fun checkAnswer(userAnswer: Boolean){
        val correctAnswer = quizViewModel.currentQuestionAnswer

        val messageResID = if(userAnswer==correctAnswer){
            R.string.correct_toast
        } else{
            R.string.incorrect_toast
        }

        Toast.makeText(this, messageResID, Toast.LENGTH_SHORT).show()
    }
}