package com.example.matematicadivertida

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.w3c.dom.Text

class AritimeticaActivity : AppCompatActivity() {

    private lateinit var num1View: TextView
    private lateinit var num2View: TextView
    private lateinit var opView: TextView
    private lateinit var respView: EditText
    private lateinit var btnOk: Button



    private var pontuacao: Int = 0
    private var qntJogos: Int = 0
    private var qntAcertos: Int = 0


    private val listOp = listOf(
        "-",
        "+"
    )

    private var num1: Int = 0
    private var num2: Int = 0
    private var op: String = ""

    private var listEquacoes: ArrayList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_aritimetica)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        num1View = findViewById(R.id.num1)
        num2View = findViewById(R.id.num2)
        opView = findViewById(R.id.op)
        respView = findViewById(R.id.resposta)
        btnOk = findViewById(R.id.btnOk)

        gerarNovoCalculo()

    }


    fun gerarNovoCalculo() {

        if( qntJogos >= 5){
            // logica para encerrar o jogo quando fechar as 5 perguntas
            finalizarJogo()
            return

        }

        var equacaoString = gerarEquacao()
        // para não ocorrer a mesma equacao
        while(listEquacoes.contains(equacaoString) || (num1-num2) < 0) {
            equacaoString = gerarEquacao()
        }
        num1View.setText(num1.toString())
        num2View.setText(num2.toString())
        opView.setText(op)



        btnOk.setOnClickListener {

            var correto: Int = 0
            var respUser: Int = respView.text.toString().toInt()
            if (op.equals("+")){
                correto = num1 + num2
            } else if (op.equals("-")) {
                correto = num1 - num2
            } else {
                println("OCORREU UM ERRO ESCOLHENDO O OPERADOR")
            }


            verificarResposta(respUser, correto)




        }



    }

    fun gerarEquacao(): String{

        num1 = (0..9).random()
        num2 = (0..9).random()
        op = listOp[(0..1).random()]
        val stringEquacao = "$num1 $op $num2"
        return stringEquacao
    }

    fun verificarResposta(respUser: Int, correto:Int){
        val msg: String
        if (respUser == correto){
            pontuacao+= 20
            qntAcertos++
            msg = "Voce Acertou!!!"
        } else {
            msg = "Voce Errou!! A resposta correta era: $correto"
        }

        AlertDialog.Builder(this)
            .setTitle("Resultado")
            .setMessage(msg)
            .setPositiveButton("OK") { _, _ ->
                qntJogos++
                respView.setText("")
                gerarNovoCalculo()
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