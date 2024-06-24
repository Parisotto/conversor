package net.parisotto.conversor

import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private var timer: CountDownTimer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val buttonIniciar: Button = findViewById(R.id.buttonIniciar)
        val buttonParar: Button = findViewById(R.id.buttonParar)

        buttonIniciar.setOnClickListener{ iniciar() }
        buttonParar.setOnClickListener{ parar() }
    }

    fun iniciar(){
        val textViewMsg: TextView = findViewById(R.id.textViewMsg)
        val textViewContador: TextView = findViewById(R.id.textViewContador)
        textViewMsg.text = "Cronômetro Iniciado!"
        timer?.cancel()


        val min: Long = 1
        timer = object : CountDownTimer(min * 60 * 1000, 1000){
            override fun onTick(c: Long) {
                var segundos = c / 1000
                var minutos = segundos / 60
                segundos = segundos % 60

                textViewContador.text = String.format("%02d:%02d", minutos, segundos)
            }

            override fun onFinish() {
                textViewMsg.text = "Cronômetro Zerado"
                textViewContador.text = "0"
            }
        }

        timer?.start()
    }

    fun parar(){
        val textViewMsg: TextView = findViewById(R.id.textViewMsg)
        val textViewContador: TextView = findViewById(R.id.textViewContador)
        textViewMsg.text = "Cronômetro Parado!"
        timer?.cancel()
    }
}