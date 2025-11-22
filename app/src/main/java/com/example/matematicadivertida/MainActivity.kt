package com.example.matematicadivertida

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    // Carrega a animação uma vez
    private val clickAnimation by lazy {
        AnimationUtils.loadAnimation(this, R.anim.button_scale)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun irContagem(view: View) {
        view.startAnimation(clickAnimation)   // animação aplicada
        val intent = Intent(this, ContagemActivity::class.java)
        startActivity(intent)
    }

    fun irAritimetica(view: View){
        view.startAnimation(clickAnimation)   // animação aplicada
        val intent = Intent(this, AritimeticaActivity::class.java)
        startActivity(intent)
    }

    fun irMaior(view: View){
        view.startAnimation(clickAnimation)   // animação aplicada
        val intent = Intent(this, MaiorActivity::class.java)
        startActivity(intent)
    }
}
