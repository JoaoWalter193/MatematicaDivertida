    package com.example.matematicadivertida

    import android.os.Bundle
    import android.widget.Button
    import android.widget.ImageView
    import android.widget.ProgressBar
    import androidx.activity.enableEdgeToEdge
    import androidx.appcompat.app.AlertDialog
    import androidx.appcompat.app.AppCompatActivity
    import androidx.core.view.ViewCompat
    import androidx.core.view.WindowInsetsCompat

    class ContagemActivity : AppCompatActivity() {


        private lateinit var escolha1: Button
        private lateinit var escolha2: Button
        private lateinit var escolha3: Button
        private lateinit var img: ImageView

        private lateinit var progresso: ProgressBar

        val imagemQuantidade = mapOf(
            R.drawable.img1 to 1,
            R.drawable.img2 to 2,
            R.drawable.img3 to 3,
            R.drawable.img4 to 4,
            R.drawable.img5 to 5,
            R.drawable.img6 to 6,
            R.drawable.img7 to 7,
            R.drawable.img8 to 8,
            R.drawable.img9 to 9,
            R.drawable.img10 to 10
        )

        private lateinit var listImg: List<Int>
        private var pontuacao: Int = 0
        private var qntJogos: Int = 0
        private var qntAcertos: Int = 0


        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            enableEdgeToEdge()
            setContentView(R.layout.activity_contagem)
            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }


            escolha1 = findViewById(R.id.btnEscolha1)
            escolha2 = findViewById(R.id.btnEscolha2)
            escolha3 = findViewById(R.id.btnEscolha3)
            img = findViewById(R.id.imageView3)
            progresso = findViewById(R.id.progresso)
            progresso.progress = 0

            // gerar as 5 imagens que vamos usar
            listImg = imagemQuantidade.keys.shuffled().take(5)

            gerarNovaImagem()
        }


        fun gerarNovaImagem(){

            if( qntJogos >= 5){
                // logica para encerrar o jogo quando fechar as 5 perguntas
                finalizarJogo()
                return

            }

            progresso.progress = qntJogos

            val imgAtual = listImg[qntJogos]
            img.setImageResource(imgAtual)

            val respCorreta: Int = imagemQuantidade[imgAtual]!!

            val respErradas: List<Int> = (1..10)
                .filter{it != respCorreta}
                .shuffled()
                .take(2)

            val opcpes = (respErradas + respCorreta).shuffled()

            escolha1.setText(opcpes[0].toString())
            escolha2.setText(opcpes[1].toString())
            escolha3.setText(opcpes[2].toString())


            escolha1.setOnClickListener { verificarResp(opcpes[0], respCorreta) }
            escolha2.setOnClickListener { verificarResp(opcpes[1], respCorreta) }
            escolha3.setOnClickListener { verificarResp(opcpes[2], respCorreta) }

        }

        fun verificarResp(escolha: Int, correta: Int){
            val msg: String
            if (escolha == correta){
                pontuacao+= 20
                qntAcertos++
                msg = "Voce Acertou!!!"
            } else {
                msg = "Voce Errou!! A resposta correta era: $correta"
            }

            AlertDialog.Builder(this)
                .setTitle("Resultado")
                .setMessage(msg)
                .setPositiveButton("OK") { _, _ ->
                    qntJogos++
                    gerarNovaImagem()
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