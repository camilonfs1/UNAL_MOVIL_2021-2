package com.unal.a2021_2_pddm

import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    var HUMAN_PLAYER = 'X'
    var COMPUTER_PLAYER = 'O'

    private var boardButtons = arrayOf<Button>()
    private var toast: Toast? = null
    private var selected = 0
    private var endgame = false

    private var difficultyLevel: TextView? = null
    private var mInfoTextView: TextView? = null
    private var pcScore: TextView? = null
    private var human: TextView? = null
    private var empate: TextView? = null
    private var mGame: TicTacToeGame = TicTacToeGame()
    private var GameOver = false
    private var humanStarts: kotlin.Boolean = false
    private var humanSc = 0
    private var androidScore: Int? = 0
    private var empateScore: Int? = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        boardButtons = arrayOf(
            findViewById(R.id.one),
            findViewById(R.id.two),
            findViewById(R.id.three),
            findViewById(R.id.four),
            findViewById(R.id.five),
            findViewById(R.id.six),
            findViewById(R.id.seven),
            findViewById(R.id.eight),
            findViewById(R.id.nine)
        )
        mGame = TicTacToeGame()
        mGame.TicTacToeGame()

        human = findViewById(R.id.humanScore)
        pcScore = findViewById(R.id.pcScore)
        empate = findViewById(R.id.empate)
        mInfoTextView = findViewById(R.id.information)
        difficultyLevel= findViewById(R.id.txt_dificulty)


        human!!.text = humanSc.toString()
        pcScore!!.text = androidScore.toString()
        empate!!.text = empateScore.toString()

        humanStarts = true
        selected = 0
        startNewGame()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.new_game -> startNewGame()
            R.id.ai_difficulty -> dialog()
            R.id.quit -> Toast.makeText(this, "Quitar Juego", Toast.LENGTH_LONG).show()
        }
        return super.onOptionsItemSelected(item)
    }

    fun dialog() {
        // dialog.show(supportFragmentManager, "customDialog")
        val builder = AlertDialog.Builder(this)
        val view = layoutInflater.inflate(R.layout.fragment_custom_dialog, null)

        builder.setView(view)

        // Create dailog
        val dialog = builder.create()
        dialog.show()

        //button
        val cancel = view.findViewById<Button>(R.id.cancel_button)
        val accept = view.findViewById<Button>(R.id.accept_button)
        val radioGroup = view.findViewById<RadioGroup>(R.id.radopGroup)

        cancel.setOnClickListener {
            dialog.hide()
        }
        accept.setOnClickListener {
            val selectedId = radioGroup.checkedRadioButtonId
            val radioButton = view.findViewById<RadioButton>(selectedId)
            dialog.hide()
            Toast.makeText(this, radioButton.text, Toast.LENGTH_LONG).show()
            when (radioButton.text) {
                "Facil" -> {
                    mGame.setmDifficultyLevel(TicTacToeGame.DifficultyLevel.Easy)
                    startNewGame()
                }
                "Medio" -> {
                    mGame.setmDifficultyLevel(TicTacToeGame.DifficultyLevel.Harder)
                    startNewGame()
                }
                "Dificil" -> {
                    mGame.setmDifficultyLevel(TicTacToeGame.DifficultyLevel.Expert)
                    startNewGame()
                }
            }
        }
    }


    private fun startNewGame() {
        difficultyLevel!!.text = mGame.getmDifficultyLevel().toString()
        endgame = false
        mGame!!.clearBoard()
        for (i in 0 until boardButtons.size) {
            boardButtons[i].text = ""
            boardButtons[i].isEnabled = true
            boardButtons[i].setOnClickListener { ButtonClickListener(i) }
        }
        GameOver = false
        if (humanStarts) {
            mInfoTextView!!.text = "VAS PRIMERO"
            humanStarts = !humanStarts
        } else {
            mInfoTextView!!.text = "LA MAQUINA PRIMERO."
            val move = mGame.getComputerMove()
            setMove(HUMAN_PLAYER, move)
            humanStarts = !humanStarts
        }
    }

    private fun ButtonClickListener(location: Int) {
        if (boardButtons[location].isEnabled() && !endgame) {
            setMove(HUMAN_PLAYER, location)

            var winner = mGame!!.checkForWinner()
            if (winner == 0) {
                mInfoTextView!!.text = "TURNO DE LA MAQUINA"
                val move = mGame!!.getComputerMove()
                setMove(COMPUTER_PLAYER, move)
                winner = mGame!!.checkForWinner()
            }
            if (winner == 0) {
                mInfoTextView!!.setText("TU TURNO")
            } else if (winner == 1) {
                mInfoTextView!!.setText("EMPATE!!!!!")
                GameOver = true;
                empateScore = empateScore!!.plus(1)
                empate!!.setText(empateScore.toString())
                endgame = true
            } else if (winner == 2) {
                mInfoTextView!!.setText("GANASTE!")
                GameOver = true
                humanSc = humanSc!!.plus(1)
                human!!.setText(humanSc.toString())
                endgame = true;
            } else {
                mInfoTextView!!.setText("GANA LA MAQUINA!");
                GameOver = true
                androidScore = androidScore!!.plus(1)
                pcScore!!.setText(androidScore.toString())
                endgame = true;
            }
        }
    }

    private fun setMove(player: Char, location: Int) {
        if (!GameOver) {
            mGame!!.setMove(HUMAN_PLAYER, location)
            boardButtons[location].isEnabled = false
            boardButtons[location].text = player.toString()
            if (player == HUMAN_PLAYER) boardButtons[location].setTextColor(
                Color.rgb(
                    255,
                    128,
                    0
                )
            ) else boardButtons[location].setTextColor(
                Color.rgb(0, 0, 255)
            )
        }
    }


}

