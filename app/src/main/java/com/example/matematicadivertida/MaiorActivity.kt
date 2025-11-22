package com.example.matematicadivertida

import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
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

    private lateinit var progresso: ProgressBar

    private var pontuacao: Int = 0
    private var qntJogos: Int = 0
    private var qntAcertos: Int = 0
    private var arraySorteado: ArrayList<Int> = ArrayList()
    private var maiorNumero: Int = 0

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
        progresso = findViewById(R.id.progresso)
        progresso.progress = 0

        gerarNUmero()
    }

    fun gerarNUmero() {
        if (qntJogos >= 5) {
            finalizarJogo()
            return
        }

        progresso.progress = qntJogos

        arraySorteado.clear()
        for (i in 1..3) {
            arraySorteado.add(Random.nextInt(0, 10))
        }

        maiorNumero = arraySorteado
            .sortedDescending()
            .joinToString("")
            .toInt()

        val opcoes = ArrayList<String>()

        opcoes.add(maiorNumero.toString())

        repeat(2) {
            var opcaoErrada: String
            do {
                opcaoErrada = arraySorteado.shuffled().joinToString("")
            } while (opcaoErrada == maiorNumero.toString() || opcoes.contains(opcaoErrada))
            opcoes.add(opcaoErrada)
        }
        opcoes.shuffle()

        escolha1.text = opcoes[0]
        escolha2.text = opcoes[1]
        escolha3.text = opcoes[2]

        escolha1.setOnClickListener { verificarResposta(escolha1.text.toString()) }
        escolha2.setOnClickListener { verificarResposta(escolha2.text.toString()) }
        escolha3.setOnClickListener { verificarResposta(escolha3.text.toString()) }
    }

    fun verificarResposta(respUser: String) {
        val msg: String
        if (respUser.toInt() == maiorNumero) {
            pontuacao += 20
            qntAcertos++
            msg = "Você Acertou!!!"
        } else {
            msg = "Você Errou!! O maior número possível era: $maiorNumero"
        }

        AlertDialog.Builder(this)
            .setTitle("Resultado")
            .setMessage(msg)
            .setPositiveButton("OK") { _, _ ->
                qntJogos++
                gerarNUmero()
            }
            .setCancelable(false)
            .show()
    }

    fun finalizarJogo() {
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