package com.example.matematicadivertida

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.random.Random

class MaiorActivity : AppCompatActivity() {


    private lateinit var escolha1: Button
    private lateinit var escolha2: Button
    private lateinit var escolha3: Button
    private lateinit var numMostrado: TextView




    private var pontuacao: Int = 0
    private var qntJogos: Int = 0
    private var qntAcertos: Int = 0

    private var numSorteado: Int = 0



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_maior)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        escolha1 = findViewById(R.id.escolha1)
        escolha2 = findViewById(R.id.escolha2)
        escolha3 = findViewById(R.id.escolha3)
        numMostrado = findViewById(R.id.numMostrado)

        gerarNUmero()

    }




    fun gerarNUmero(){


        if( qntJogos >= 5){
            // logica para encerrar o jogo quando fechar as 5 perguntas
            finalizarJogo()
            return

        }

        numSorteado = Random.nextInt(100,1000)

        numMostrado.setText(numSorteado.toString())

        var numRandom = Random.nextInt(0,3)

        when {

            numRandom == 0 -> {
                escolha1.setText(numSorteado.toString())
                escolha2.setText(Random.nextInt(100,1000).toString())
                escolha3.setText(Random.nextInt(100,1000).toString())
            }
            numRandom == 1 -> {
                escolha2.setText(numSorteado.toString())
                escolha3.setText(Random.nextInt(100,1000).toString())
                escolha1.setText(Random.nextInt(100,1000).toString())

            }
            numRandom == 2 ->  {
                escolha3.setText(numSorteado.toString())
                escolha2.setText(Random.nextInt(100,1000).toString())
                escolha1.setText(Random.nextInt(100,1000).toString())

            }

        }

        escolha1.setOnClickListener { verificarResposta(escolha1.text.toString().toInt()) }
        escolha2.setOnClickListener { verificarResposta(escolha2.text.toString().toInt()) }
        escolha3.setOnClickListener { verificarResposta(escolha3.text.toString().toInt()) }


    }




    fun verificarResposta(respUser: Int){
        val msg: String
        if (respUser == numSorteado){
            pontuacao+= 20
            qntAcertos++
            msg = "Voce Acertou!!!"
        } else {
            msg = "Voce Errou!! A resposta correta era: $numSorteado"
        }

        AlertDialog.Builder(this)
            .setTitle("Resultado")
            .setMessage(msg)
            .setPositiveButton("OK") { _, _ ->
                qntJogos++
                numMostrado.setText("")
                gerarNUmero()
            }
            .setCancelable(false)
            .show()

    }


    fun finalizarJogo(){

        AlertDialog.Builder(this)
            .setTitle("Fim do Jogo")
            .setMessage("Você acertou $qntAcertos de 5.\nNota final: $pontuacao")
            .setPositiveButton("OK") { _, _ ->
                finish()
            }
            .setCancelable(false)
            .show()

    }

}