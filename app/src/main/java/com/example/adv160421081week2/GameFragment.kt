package com.example.adv160421081week2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.navigation.Navigation
import com.example.adv160421081week2.databinding.FragmentGameBinding
import com.google.android.material.snackbar.Snackbar

class GameFragment : Fragment() {
    private lateinit var binding: FragmentGameBinding
    override fun onCreateView(inflater: LayoutInflater, container:
    ViewGroup?,savedInstanceState: Bundle?): View? {
        binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btnSubmit = view.findViewById<Button>(R.id.btnSubmit)
        val txtTurn = view.findViewById<TextView>(R.id.txtTurn)
        val txtAnswer = view.findViewById<EditText>(R.id.txtAnswer)
        var playerScore: Int = 0
        var playerName: String = ""

        var num1 = view.findViewById<TextView>(R.id.txtNum1)
        var num2 = view.findViewById<TextView>(R.id.txtNum2)

        var randomNum1 = (0..99).random()
        var randomNum2 = (0..99).random()
        num1.text = randomNum1.toString()
        num2.text = randomNum2.toString()

        if(arguments != null) {
            playerName =
                GameFragmentArgs.fromBundle(requireArguments()).playerName
            txtTurn.text = "$playerName's Turn"
            playerScore =
                GameFragmentArgs.fromBundle(requireArguments()).playerScore
        }
        btnSubmit.setOnClickListener {
            var result: Int = randomNum1 + randomNum2
            if(txtAnswer.text.toString() == ""){
                Snackbar.make(view, "Answer cannot be empty", Snackbar.LENGTH_SHORT).show()
            }else{
                var answer = txtAnswer.text.toString().toInt()
                if(answer == result){
                    randomNum1 = (0..99).random()
                    randomNum2 = (0..99).random()
                    num1.text = randomNum1.toString()
                    num2.text = randomNum2.toString()
                    playerScore += 1
                    val action = GameFragmentDirections.actionGameFragmentSelf(playerName, playerScore)
                    Navigation.findNavController(it).navigate(action)
                }else{
                    val action = GameFragmentDirections.actionResultFragment(playerScore)
                    Navigation.findNavController(it).navigate(action)
                }
            }
        }
    }
}