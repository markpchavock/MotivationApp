package com.markdev.motivationapp.infra

import android.content.Context
import android.content.SharedPreferences

class SecurityPreferences(context: Context) {

    private val security: SharedPreferences =
        context.getSharedPreferences("Motivation", Context.MODE_PRIVATE)

    //Para Salvar String:

    fun storeString(key: String, str: String) {
        security.edit().putString(key, str).apply()
    }

    //Para Recuperar Strings

    fun getString(key: String): String {
        return security.getString(key, "")
            ?: "" //Operador Elvis: Se a 1° expressão for nulo, use a segunda.
    }
}