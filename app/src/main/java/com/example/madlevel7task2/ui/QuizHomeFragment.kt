package com.example.madlevel7task2.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.example.madlevel7task2.R

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class QuizHomeFragment : Fragment() {
//    private val quizViewModel: QuizViewModel by activityViewModels()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quiz_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.btn_start_quiz).setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }

//        quizViewModel.createQuiz(
//            Quiz(
//                listOf(
//                    Question(
//                        "Who is the best footballer in the world",
//                        listOf(
//                            "Cristiano Ronaldo",
//                            "Messi",
//                            "Mbappe"
//                        ),
//                        "Cristiano Ronaldo"
//                    ),
//                    Question(
//                        "What is the biggest planet in the solar system",
//                        listOf(
//                            "Jupiter",
//                            "The sun",
//                            "Earth",
//                            "The moon"
//                        ),
//                        "Jupiter"
//                    ),
//                )
//            )
//        )
    }
}