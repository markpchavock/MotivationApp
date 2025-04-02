package com.markdev.motivationapp.ui

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.markdev.motivationapp.infra.MotivationConstants
import com.markdev.motivationapp.R
import com.markdev.motivationapp.data.Mock
import com.markdev.motivationapp.infra.SecurityPreferences
import com.markdev.motivationapp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() , View.OnClickListener{
    private lateinit var binding: ActivityMainBinding
    private var categoryId = MotivationConstants.FILTER.ALL

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.statusBarColor = ContextCompat.getColor(this, R.color.purple)
        WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars = false

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //Declarações dos componentes clicáveis

        binding.buttonNewPhrase.setOnClickListener(this)
        binding.imageAll.setOnClickListener(this)
        binding.imageHappy.setOnClickListener(this)
        binding.imageSunny.setOnClickListener(this)

        handleUserName()
        // "Finge" o clique do primeiro item na tela inicial para cair na primeira condição de if e pintar o primeiro item.
        handleFilter(R.id.image_all)
        handleNextPhrase()

    }
    override fun onClick(view: View) {
        if(view.id == R.id.button_new_phrase){
            handleNextPhrase()
        } else if(view.id in listOf(R.id.image_all,R.id.image_happy,R.id.image_sunny)){
            handleFilter(view.id)

        }
    }

    private fun handleNextPhrase(){
        binding.textMessageCenter.text = Mock().getPhrase(categoryId) // Conectamos o botão de nova frase com o  get das frases.
    }
    //Tratamento do icone que foi clicado
    private fun handleFilter(id: Int){

        //Pinta  todos os itens da cor padrão, como se estivesse apagado.
        binding.imageAll.setColorFilter(ContextCompat.getColor(this,R.color.dark_purple))
        binding.imageHappy.setColorFilter(ContextCompat.getColor(this,R.color.dark_purple))
        binding.imageSunny.setColorFilter(ContextCompat.getColor(this,R.color.dark_purple))

        // O icone que foi clicado ficará em destaque
        when (id) {
            R.id.image_all -> {
                binding.imageAll.setColorFilter(ContextCompat.getColor(this,R.color.white))
                categoryId = MotivationConstants.FILTER.ALL //Constantes criadas para representar as categorias de frases
            }
            R.id.image_happy -> {
                binding.imageHappy.setColorFilter(ContextCompat.getColor(this,R.color.white))
                categoryId = MotivationConstants.FILTER.HAPPY
            }
            R.id.image_sunny -> {
                binding.imageSunny.setColorFilter(ContextCompat.getColor(this,R.color.white))
                categoryId = MotivationConstants.FILTER.SUNNY
            }
        }
    }
    private fun handleUserName(){
        val name = SecurityPreferences(this).getString(MotivationConstants.KEY.USER_NAME)
        binding.textUserName.text = "Olá , $name"

    }

}
