package com.example.madlevel7task2.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import com.example.madlevel7task2.models.Quiz
import com.example.madlevel7task2.R
import com.example.madlevel7task2.viewmodels.QuizViewModel
import kotlinx.android.synthetic.main.fragment_quiz.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class QuizFragment : Fragment() {
    private val viewModel: QuizViewModel by activityViewModels()
    private val questionNumber: MutableLiveData<Int> = MutableLiveData()
    private var quiz = Quiz(listOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quiz, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initQuizObservers()

        view.findViewById<Button>(R.id.btnConfirmQuiz).setOnClickListener {
            val radioButton: RadioButton? =
                requireActivity().findViewById(radioGroup.checkedRadioButtonId)

            if (radioButton?.text == quiz.questions[questionNumber.value!!].correctAnswer) {
                nextQuestion()
            } else {
                Toast.makeText(context, "Incorrect answer try again", Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.getQuiz()
    }

    private fun initQuizObservers() {
        viewModel.quiz.observe(viewLifecycleOwner, {
            quiz = it
            questionNumber.value = 1 // set question number to 0
            initRadioButton(questionNumber.value!!)
        })

        questionNumber.observe(viewLifecycleOwner, {
            initRadioButton(it)
        })
    }

    private fun nextQuestion() {
        if ((questionNumber.value?.plus(1)) == quiz.questions.size) {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
            Toast.makeText(context, "You completed the quiz!", Toast.LENGTH_SHORT).show()
        } else {
            questionNumber.value = questionNumber.value!! + 1
        }
    }

    private fun initRadioButton(questionNumber: Int) {
        radioGroup.removeAllViews()
        val question = quiz.questions[questionNumber]
        txtQuizQuestionNumber.text = getString(R.string.txt_quiz_rate, questionNumber.toString())

        txtQuizQuestion.text = question.question

        for (answer in question.answers) {
            val radioButton = RadioButton(context)
            radioButton.text = answer
            radioGroup.addView(radioButton)
        }

    }
}