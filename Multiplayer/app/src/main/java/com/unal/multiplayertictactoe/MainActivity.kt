package com.unal.multiplayertictactoe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.unal.multiplayertictactoe.Game.MainGame
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {

    private var btnNewGame: Button? = null
    private var btnJoinGame: Button? = null
    private var btnExit: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnNewGame = findViewById(R.id.btn_newgame)
        btnJoinGame = findViewById(R.id.btn_joingame)
        btnExit = findViewById(R.id.btn_exit)


        btnNewGame!!.setOnClickListener {

        }
        btnJoinGame!!.setOnClickListener {
            val intent = Intent(this, MainGame::class.java)
            intent.putExtra("letter", "X")
            startActivity(intent)
        }
        btnExit!!.setOnClickListener {
            moveTaskToBack(true);
            exitProcess(-1)
        }
    }
}