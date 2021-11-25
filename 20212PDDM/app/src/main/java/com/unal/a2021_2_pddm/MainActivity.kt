package com.unal.a2021_2_pddm

import android.content.SharedPreferences
import android.graphics.Color
import android.media.MediaPlayer
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import java.util.concurrent.TimeUnit


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

    private var mp: MediaPlayer? = null
    private var mp2: MediaPlayer? = null
    private var mp3: MediaPlayer? = null

    private var mPrefs: SharedPreferences? = null

    private var currentSong = mutableListOf(R.raw.title, R.raw.sound1, R.raw.sound3)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mPrefs = getSharedPreferences("ttt_prefs", MODE_PRIVATE)

        // Restore the scores
        humanSc = mPrefs!!.getInt("mHumanWins", 0)
        androidScore = mPrefs!!.getInt("mComputerWins", 0)
        empateScore = mPrefs!!.getInt("mTies", 0)


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
        difficultyLevel = findViewById(R.id.txt_dificulty)


        human!!.text = humanSc.toString()
        pcScore!!.text = androidScore.toString()
        empate!!.text = empateScore.toString()

        humanStarts = true
        selected = 0

        if (savedInstanceState == null) {
            startNewGame()
        } else {
            human!!.text = savedInstanceState.getCharSequence("human")
            pcScore!!.text = savedInstanceState.getCharSequence("machine")
            empate!!.text = savedInstanceState.getCharSequence("empate")
            startNewGame()
        }


    }

    override fun onStop() {
        super.onStop()

        val ed = mPrefs!!.edit()
        ed.putInt("mHumanWins", humanSc)
        ed.putInt("mComputerWins", androidScore!!);
        ed.putInt("mTies", empateScore!!);
        ed.commit()
    }

    override fun onSaveInstanceState(outState: Bundle) { // Here You have to save count value
        super.onSaveInstanceState(outState)

        outState.putSerializable("human", human!!.text.toString())
        outState.putSerializable("machine", pcScore!!.text.toString())
        outState.putSerializable("empate", empate!!.text.toString())
    }

    private fun controlSound(id: Int, option: Int) {
        if (option == 1) {
            if (mp == null) {
                mp = MediaPlayer.create(this, id)
            }
            mp?.start()
        } else if (option == 2) {
            if (mp2 == null) {
                mp2 = MediaPlayer.create(this, id)
            }
            mp2?.start()
        } else if (option == 3) {
            mp?.stop()
            mp?.reset()
            mp?.release()
            mp = null
            if (mp3 == null) {
                mp3 = MediaPlayer.create(this, id)
            }
            mp3?.start()
        } else if (option == 4) {
            mp?.stop()
        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.new_game -> startNewGame()
            R.id.ai_difficulty -> dialog()
            R.id.music -> controlSound(currentSong[0], 4)
            R.id.quit -> {
                finish();
                System.exit(0);
            }
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
        controlSound(currentSong[0], 4)
        controlSound(currentSong[0], 1)
        difficultyLevel!!.text = mGame.getmDifficultyLevel().toString()
        endgame = false
        mGame!!.clearBoard()
        for (i in 0 until boardButtons.size) {
            boardButtons[i].text = ""
            boardButtons[i].setBackgroundResource(R.drawable.navidada)
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
                TimeUnit.SECONDS.sleep(1L)
                setMove(COMPUTER_PLAYER, move)
                winner = mGame!!.checkForWinner()
            }
            if (winner == 0) {
                mInfoTextView!!.setText("TU TURNO")
            } else if (winner == 1) {
                mInfoTextView!!.setText("EMPATE!!!!!")
                controlSound(currentSong[2], 3)
                GameOver = true;
                empateScore = empateScore!!.plus(1)
                empate!!.setText(empateScore.toString())
                endgame = true
            } else if (winner == 2) {
                controlSound(currentSong[2], 3)
                mInfoTextView!!.setText("GANASTE!")
                GameOver = true
                humanSc = humanSc!!.plus(1)
                human!!.setText(humanSc.toString())
                endgame = true;
            } else {
                controlSound(currentSong[2], 3)
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
            //boardButtons[location].text = player.toString()

            if (player.toString().equals("X")) {
                controlSound(currentSong[1], 2)
                boardButtons[location].setBackgroundResource(R.drawable.navidad1)

            } else {

                controlSound(currentSong[1], 2)
                boardButtons[location].setBackgroundResource(R.drawable.navidad2)
            }
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

