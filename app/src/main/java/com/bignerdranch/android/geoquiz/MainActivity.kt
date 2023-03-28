package com.bignerdranch.android.geoquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.bignerdranch.android.geoquiz.databinding.ActivityMainBinding

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding

    private val questionBank = listOf(
        Question(R.string.question_australia, true),
        Question(R.string.question_oceans, true),
        Question(R.string.question_mideast, false),
        Question(R.string.question_africa, false),
        Question(R.string.question_americas, true),
        Question(R.string.question_asia, true),
    )
    private var currentIndex = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate(Bundle?) called")
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.nextButton.setOnClickListener{
            currentIndex = (currentIndex+1) % questionBank.size
            updateQuestion()
        }

        binding.trueButton.setOnClickListener{
            checkAnswer(true)
        }
        binding.falseButton.setOnClickListener{
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
        val questionTextResId = questionBank[currentIndex].textResId
        binding.questionTextView.setText(questionTextResId)
    }

    private fun checkAnswer(userAnswer: Boolean){
        val correctAnswer = questionBank[currentIndex].answer

        val messageResID = if(userAnswer==correctAnswer){
            R.string.correct_toast
        } else{
            R.string.incorrect_toast
        }

        Toast.makeText(this, messageResID, Toast.LENGTH_SHORT).show()
    }
}