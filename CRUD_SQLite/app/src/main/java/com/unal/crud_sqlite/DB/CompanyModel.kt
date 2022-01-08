package com.unal.crud_sqlite.DB

import java.util.*

data class CompanyModel (
    var id: Int = getAutoId(),
    var name: String = "",
    var email: String = "",
    var phone: String = "",
    var URL: String = "",
    var products: String = "",
    var classification: String = "",
){
    companion object{
        fun getAutoId():Int{
            val random = Random()
            return random.nextInt(100)
        }
    }
}