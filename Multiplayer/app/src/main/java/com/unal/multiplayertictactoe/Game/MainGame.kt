


package com.unal.multiplayertictactoe.Game

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.unal.multiplayertictactoe.BackCommunication.BackCommunication
import com.unal.multiplayertictactoe.R

class MainGame : AppCompatActivity() {

    private var btn_11: CardView? = null
    private var btn_21: CardView? = null
    private var btn_31: CardView? = null
    private var btn_12: CardView? = null
    private var btn_22: CardView? = null
    private var btn_32: CardView? = null
    private var btn_13: CardView? = null
    private var btn_23: CardView? = null
    private var btn_33: CardView? = null


    private var txt_11: TextView? = null
    private var txt_21: TextView? = null
    private var txt_31: TextView? = null
    private var txt_12: TextView? = null
    private var txt_22: TextView? = null
    private var txt_32: TextView? = null
    private var txt_13: TextView? = null
    private var txt_23: TextView? = null
    private var txt_33: TextView? = null

    var ownGame = ArrayList<String>()
    var otherGame = ArrayList<String>()

    var back = BackCommunication()

    var ownLetter: String? = null
    var otherLetter: String? = null
    var gammer: Int? = null

    private var txtOwnPoints: TextView? =null
    private var txtOtherPoints: TextView? =null
    private var txtTiePoints: TextView? =null
    private var txtGammer: TextView? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_game)

        ownLetter = intent.getStringExtra("letter")
        gammer = Integer.parseInt(intent.getStringExtra("gammer").toString())
        otherLetter = "O"

        txtOwnPoints = findViewById(R.id.txt_own_points)
        txtOtherPoints = findViewById(R.id.txt_other_points)
        txtTiePoints = findViewById(R.id.txt_tie_points)
        txtGammer = findViewById(R.id.txt_player)

        btn_11 = findViewById(R.id.btn11)
        btn_21 = findViewById(R.id.btn21)
        btn_31 = findViewById(R.id.btn31)
        btn_12 = findViewById(R.id.btn12)
        btn_22 = findViewById(R.id.btn22)
        btn_32 = findViewById(R.id.btn32)
        btn_13 = findViewById(R.id.btn13)
        btn_23 = findViewById(R.id.btn23)
        btn_33 = findViewById(R.id.btn33)


        txt_11 = findViewById(R.id.txt11)
        txt_21 = findViewById(R.id.txt21)
        txt_31 = findViewById(R.id.txt31)
        txt_12 = findViewById(R.id.txt12)
        txt_22 = findViewById(R.id.txt22)
        txt_32 = findViewById(R.id.txt32)
        txt_13 = findViewById(R.id.txt13)
        txt_23 = findViewById(R.id.txt23)
        txt_33 = findViewById(R.id.txt33)
        block(false)
        startGame(gammer!!)

    }

    fun startGame(gamer: Int) {
        if(gamer==1){
            txtGammer!!.text = "Juegas TU!!"
            block(true)
            listen()
        }else{
            txtGammer!!.text = "Esperando..."
            block(false)
            dogameOther()
        }
    }

    fun listen() {
        btn_11!!.setOnClickListener {
            dogameOwn(ownLetter!!, 1, 1, btn_11!!)
        }
        btn_21!!.setOnClickListener {
            dogameOwn(ownLetter!!, 2, 1, btn_21!!)
        }
        btn_31!!.setOnClickListener {
            dogameOwn(ownLetter!!, 3, 1, btn_31!!)
        }
        btn_12!!.setOnClickListener {
            dogameOwn(ownLetter!!, 1, 2, btn_12!!)
        }
        btn_22!!.setOnClickListener {
            dogameOwn(ownLetter!!, 2, 2, btn_22!!)
        }
        btn_32!!.setOnClickListener {
            dogameOwn(ownLetter!!, 3, 2, btn_32!!)
        }
        btn_13!!.setOnClickListener {
            dogameOwn(ownLetter!!, 1, 3, btn_13!!)
        }
        btn_23!!.setOnClickListener {
            dogameOwn(ownLetter!!, 2, 3, btn_23!!)
        }
        btn_33!!.setOnClickListener {
            dogameOwn(ownLetter!!, 3, 3, btn_33!!)
        }
    }


    fun dogameOwn(letter: String, position_x: Int, position_y: Int, btn: CardView) {
        var position: String = (position_x.toString() + "," + position_y.toString())
        btn.isEnabled = false
        changeLetter(letter, btn)
        changeColorOwn(btn!!)
        ownGame.add(position)
        System.out.println("Mi Juego -------------->" + position)
        var response = checkWinner(ownGame, "Yo")
        if (response == "Ganaste") {
            block(false)
        } else {
            startGame(0)
        }
    }

    fun changeLetter(letter: String, position: CardView) {
        var txtToChange: TextView? = null
        when (position) {
            btn_11 -> txtToChange = txt_11
            btn_21 -> txtToChange = txt_21
            btn_31 -> txtToChange = txt_31
            btn_12 -> txtToChange = txt_12
            btn_22 -> txtToChange = txt_22
            btn_32 -> txtToChange = txt_32
            btn_13 -> txtToChange = txt_13
            btn_23 -> txtToChange = txt_23
            btn_33 -> txtToChange = txt_33
        }
        txtToChange!!.text = letter
    }

    fun dogameOther() {
        var otherPossition =  back.getPossition()
        var btn : CardView? = null
        when (otherPossition){
            "1,1"-> btn = btn_11
            "1,2"-> btn = btn_12
            "1,3"-> btn = btn_13
            "2,1"-> btn = btn_21
            "2,2"-> btn = btn_22
            "2,3"-> btn = btn_23
            "3,1"-> btn = btn_31
            "3,2"-> btn = btn_32
            "3,3"-> btn = btn_33
        }
        btn!!.isEnabled = false
        changeLetter(otherLetter!!, btn)
        changeColorOther(btn!!)
        System.out.println("Otro Jugador -------------->" + otherPossition)
        var response = checkWinner(otherGame, "Yo")
        if (response == "Ganaste") {
            block(false)
        } else {
            startGame(1)
        }
    }

    fun changeColorOwn(btn: CardView) {
        btn.setBackgroundColor(Color.rgb(255, 55, 0))
    }

    fun changeColorOther(btn: CardView) {
        btn.setBackgroundColor(Color.rgb(107, 255, 51))
    }

    fun checkWinner(listGame: ArrayList<String>, gamer: String): String {
        if (listGame.contains("1,1") && listGame.contains("2,2") && listGame.contains("3,3")) {
            System.out.println("Ganaste" + gamer)
            return ("Ganaste")
        } else if (listGame.contains("3,1") && listGame.contains("2,2") && listGame.contains("1,3")) {
            System.out.println("Ganaste" + gamer)
            return ("Ganaste")
        } else if (listGame.contains("1,1") && listGame.contains("2,1") && listGame.contains("3,1")) {
            System.out.println("Ganaste" + gamer)
            return ("Ganaste")
        } else if (listGame.contains("1,2") && listGame.contains("2,2") && listGame.contains("3,2")) {
            System.out.println("Ganaste" + gamer)
            return ("Ganaste")
        } else if (listGame.contains("1,3") && listGame.contains("2,3") && listGame.contains("3,3")) {
            System.out.println("Ganaste" + gamer)
            return ("Ganaste")
        } else if (listGame.contains("1,1") && listGame.contains("1,2") && listGame.contains("1,3")) {
            System.out.println("Ganaste" + gamer)
            return ("Ganaste")
        } else if (listGame.contains("2,1") && listGame.contains("2,2") && listGame.contains("2,3")) {
            System.out.println("Ganaste" + gamer)
            return ("Ganaste")
        } else if (listGame.contains("3,1") && listGame.contains("3,2") && listGame.contains("3,3")) {
            System.out.println("Ganaste" + gamer)
            return ("Ganaste")
        }

        return ("")
    }

    fun block(state:Boolean) {
        btn_11!!.isEnabled = state
        btn_21!!.isEnabled = state
        btn_31!!.isEnabled = state
        btn_12!!.isEnabled = state
        btn_22!!.isEnabled = state
        btn_32!!.isEnabled = state
        btn_13!!.isEnabled = state
        btn_23!!.isEnabled = state
        btn_33!!.isEnabled = state
    }


}