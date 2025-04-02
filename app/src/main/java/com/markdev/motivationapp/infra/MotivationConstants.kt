package com.markdev.motivationapp.infra

class MotivationConstants private constructor(){

    object KEY{
        const val USER_NAME = "USER_NAME"
    }

    // Constantes para utilizar nas categorias das frases (all,happy,sunny) e auxiliar na troca das frases.
    object FILTER {
        const val ALL = 1
        const val HAPPY = 2
        const val SUNNY = 3
    }
}