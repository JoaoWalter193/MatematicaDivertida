package com.example.matematicadivertida

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.jvm.java

class MainActivity : AppCompatActivity() {
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

    fun irContagem(view: View){

        val intent = Intent(this, ContagemActivity::class.java)
        startActivity(intent)
    }

    fun irAritimetica(view: View){
        val intent = Intent(this, AritimeticaActivity::class.java)
        startActivity(intent)
    }

    fun irMaior(view: View){
        val intent = Intent(this, MaiorActivity::class.java)
        startActivity(intent)
    }
}