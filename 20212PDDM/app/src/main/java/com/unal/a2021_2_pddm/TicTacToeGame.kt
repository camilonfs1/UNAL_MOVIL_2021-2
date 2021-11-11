package com.unal.a2021_2_pddm

import java.util.*

class TicTacToeGame {
    enum class DifficultyLevel {
        Easy, Harder, Expert
    }

    private var mDifficultyLevel = DifficultyLevel.Expert

    fun getmDifficultyLevel(): DifficultyLevel? {
        return mDifficultyLevel
    }

    private var mBoard = charArrayOf(' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ')
    val BOARD_SIZE = 9

    var HUMAN_PLAYER = 'X'
    var COMPUTER_PLAYER = 'O'
    var OPEN_SPOT = ' '

    private var mRand: Random = Random()

    fun setmDifficultyLevel(mDifficultyLevel: DifficultyLevel?) {
        this.mDifficultyLevel = mDifficultyLevel!!
    }

    fun TicTacToeGame() {
        // Seed the random number generator
        mRand = Random()
        val turn = HUMAN_PLAYER // Human starts first
        val win = 0
    }

    fun clearBoard() {
        mBoard = charArrayOf(' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ')
    }

    fun setMove(player: Char, location: Int) {
        if (mBoard[location] === OPEN_SPOT) {
            mBoard[location] = player
        }
    }

    fun getComputerMove(): Int {
        var move = -1
        if (mDifficultyLevel === DifficultyLevel.Easy) move =
            getRandomMove() else if (mDifficultyLevel === DifficultyLevel.Harder) {
            move = getWinningMove()
            if (move == -1) move = getRandomMove()
        } else if (mDifficultyLevel === DifficultyLevel.Expert) {
            move = getWinningMove()
            if (move == -1) move = getBlockingMove()
            if (move == -1) move = getRandomMove()
        }
        return move
    }

    private fun getBlockingMove(): Int {
        val move = -1
        for (i in 0 until BOARD_SIZE) {
            if (mBoard[i] !== HUMAN_PLAYER && mBoard[i] !== COMPUTER_PLAYER) {
                val curr = mBoard[i] // Save the current number
                mBoard[i] = HUMAN_PLAYER
                if (checkForWinner() === 2) {
                    mBoard[i] = COMPUTER_PLAYER
                    println("Computer is moving to " + (i + 1))
                    return i
                } else {
                    mBoard[i] = curr
                }
            }
        }
        return move
    }

    private fun getWinningMove(): Int {
        val move = -1
        for (i in 0 until BOARD_SIZE) {
            if (mBoard[i] !== HUMAN_PLAYER && mBoard[i] !== COMPUTER_PLAYER) {
                val curr = mBoard[i]
                mBoard[i] = COMPUTER_PLAYER
                if (checkForWinner() === 3) {
                    println("Computer is moving to " + (i + 1))
                    return i
                } else {
                    mBoard[i] = curr
                }
            }
        }
        return move
    }

    private fun getRandomMove(): Int {
        var move = -1
        do {
            move = mRand!!.nextInt(BOARD_SIZE)
        } while (mBoard[move] === HUMAN_PLAYER || mBoard[move] === COMPUTER_PLAYER)
        return move
    }

    fun checkForWinner(): Int {
        // Check horizontal wins
        run {
            var i = 0
            while (i <= 6) {
                if (mBoard[i] === HUMAN_PLAYER && mBoard[i + 1] === HUMAN_PLAYER && mBoard[i + 2] === HUMAN_PLAYER
                ) return 2
                if (mBoard[i] === COMPUTER_PLAYER && mBoard[i + 1] === COMPUTER_PLAYER && mBoard[i + 2] === COMPUTER_PLAYER
                ) return 3
                i += 3
            }
        }

        // Check vertical wins
        for (i in 0..2) {
            if (mBoard[i] === HUMAN_PLAYER && mBoard[i + 3] === HUMAN_PLAYER && mBoard[i + 6] === HUMAN_PLAYER) return 2
            if (mBoard[i] === COMPUTER_PLAYER && mBoard[i + 3] === COMPUTER_PLAYER && mBoard[i + 6] === COMPUTER_PLAYER) return 3
        }

        // Check for diagonal wins
        if (mBoard[0] === HUMAN_PLAYER && mBoard[4] === HUMAN_PLAYER && mBoard[8] === HUMAN_PLAYER ||
            mBoard[2] === HUMAN_PLAYER && mBoard[4] === HUMAN_PLAYER && mBoard[6] === HUMAN_PLAYER
        ) return 2
        if (mBoard[0] === COMPUTER_PLAYER && mBoard[4] === COMPUTER_PLAYER && mBoard[8] === COMPUTER_PLAYER ||
            mBoard[2] === COMPUTER_PLAYER && mBoard[4] === COMPUTER_PLAYER && mBoard[6] === COMPUTER_PLAYER
        ) return 3

        // Check for tie
        for (i in 0 until BOARD_SIZE) {
            // If we find a number, then no one has won yet
            if (mBoard[i] !== HUMAN_PLAYER && mBoard[i] !== COMPUTER_PLAYER) return 0
        }

        return 1
    }
}