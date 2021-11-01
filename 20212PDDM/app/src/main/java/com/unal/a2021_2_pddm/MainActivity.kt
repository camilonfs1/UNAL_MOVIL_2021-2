package com.unal.a2021_2_pddm

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    private var infoText : TextView? = null

    private var button1 : Button? = null
    private var button2 : Button? = null
    private var button3 : Button? = null
    private var button4 : Button? = null
    private var button5 : Button? = null
    private var button6 : Button? = null
    private var button7 : Button? = null
    private var button8 : Button? = null
    private var button9 : Button? = null

    private var reset : Button? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        infoText = findViewById(R.id.information)
        button1 = findViewById(R.id.one)
        button2 = findViewById(R.id.two)
        button3 = findViewById(R.id.three)
        button4 = findViewById(R.id.four)
        button5 = findViewById(R.id.five)
        button6 = findViewById(R.id.six)
        button7 = findViewById(R.id.seven)
        button8 = findViewById(R.id.eight)
        button9 = findViewById(R.id.nine)

        reset = findViewById(R.id.btn_restart)

        reset!!.setOnClickListener {
            reset()
        }
    }

    fun buttonsClick(view: View) {
        val buttonSelected = view as Button //Button selected
        var cellID = 0

        when (buttonSelected.id) {
            R.id.one -> cellID = 1
            R.id.two -> cellID = 2
            R.id.three -> cellID = 3
            R.id.four -> cellID = 4
            R.id.five -> cellID = 5
            R.id.six -> cellID = 6
            R.id.seven -> cellID = 7
            R.id.eight -> cellID = 8
            R.id.nine -> cellID = 9
        }
        playGame(cellID, buttonSelected)
    }

    var player1 = ArrayList<Int>()
    var player2 = ArrayList<Int>()

    var activePlayer = 1

    fun playGame(cellID: Int, buttonSelected: Button) {

        //Selectec player and add player cell selected
        if (activePlayer == 1) {
            buttonSelected.text = "X"
            buttonSelected.setBackgroundColor(Color.BLUE)
            player1.add(cellID)
            activePlayer = 2
            infoText!!.text=" \n MAQUINA"
            Autoplayer()
        } else {
            buttonSelected.text = "O"
            buttonSelected.setBackgroundColor(Color.GREEN)
            player2.add(cellID)
            infoText!!.text=" \n HUMANO"
            activePlayer = 1
        }

        // Block selected button
        buttonSelected.isEnabled = false

        checkWinner()
    }

    private fun checkWinner() {
        var winner = -1
        //row 1
        if (player1.contains(1) && player1.contains(2) && player1.contains(3)) {
            winner = 1
        }
        if (player2.contains(1) && player2.contains(2) && player2.contains(3)) {
            winner = 2
        }
        //row 2
        if (player1.contains(4) && player1.contains(5) && player1.contains(6)) {
            winner = 1
        }
        if (player2.contains(4) && player2.contains(5) && player2.contains(6)) {
            winner = 2
        }
        //row 3
        if (player1.contains(7) && player1.contains(8) && player1.contains(9)) {
            winner = 1
        }
        if (player2.contains(7) && player2.contains(8) && player2.contains(9)) {
            winner = 2
        }
        //col 1
        if (player1.contains(1) && player1.contains(4) && player1.contains(7)) {
            winner = 1
        }
        if (player2.contains(1) && player2.contains(4) && player2.contains(7)) {
            winner = 2
        }
        //col 2
        if (player1.contains(2) && player1.contains(5) && player1.contains(8)) {
            winner = 1
        }
        if (player2.contains(2) && player2.contains(5) && player2.contains(8)) {
            winner = 2
        }
        //col 3
        if (player1.contains(3) && player1.contains(6) && player1.contains(9)) {
            winner = 1
        }
        if (player2.contains(3) && player2.contains(6) && player2.contains(9)) {
            winner = 2
        }

        //Diagonal 1
        if (player1.contains(7) && player1.contains(5) && player1.contains(3)) {
            winner = 1
        }
        if (player2.contains(7) && player2.contains(5) && player2.contains(3)) {
            winner = 2
        }

        //Diagonal 2
        if (player1.contains(1) && player1.contains(5) && player1.contains(9)) {
            winner = 1
        }
        if (player2.contains(1) && player2.contains(5) && player2.contains(9)) {
            winner = 2
        }

        var name = ""
        //Winner
        if (winner != -1) {
            if(winner==1) name ="Humano"
            if(winner==2) name ="MAQUINA"
            infoText!!.text="Ganador!! \n Jugador ${name}"
            blockAll()
        }
    }

    private  fun Autoplayer(){
        var emptyCells = ArrayList<Int>()
        for(cellID in 1..9){
            if(!(player1.contains(cellID) || player2.contains(cellID))){
                emptyCells.add(cellID)
            }
        }

        var r = Random()
        val randIndex = r.nextInt(emptyCells.size-0)+0
        val cellID = emptyCells[randIndex]

        val buttonSelected: Button
        when(cellID){
            1-> buttonSelected = findViewById(R.id.one)
            2-> buttonSelected = findViewById(R.id.two)
            3-> buttonSelected = findViewById(R.id.three)
            4-> buttonSelected = findViewById(R.id.four)
            5-> buttonSelected = findViewById(R.id.five)
            6-> buttonSelected = findViewById(R.id.six)
            7-> buttonSelected = findViewById(R.id.seven)
            8-> buttonSelected = findViewById(R.id.eight)
            9-> buttonSelected = findViewById(R.id.nine)
            else-> buttonSelected = findViewById(R.id.one)
        }

        playGame(cellID,buttonSelected)
    }
    fun reset(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
    fun blockAll(){
        button1!!.isEnabled = false
        button2!!.isEnabled = false
        button3!!.isEnabled = false
        button4!!.isEnabled = false
        button5!!.isEnabled = false
        button6!!.isEnabled = false
        button7!!.isEnabled = false
        button8!!.isEnabled = false
        button9!!.isEnabled = false
    }
}

