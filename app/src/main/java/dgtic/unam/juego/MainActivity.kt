package dgtic.unam.juego

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dgtic.unam.juego.databinding.ActivityMainBinding
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /* CoroutineScope(Dispatchers.Default).launch {
             var contador = 0
             while (true){

                 withContext(Dispatchers.Main) {
                     delay(2000)
                     binding.text.text = contador.toString()
                     contador++

                 }

             }
         }*/

        /* GlobalScope.launch {
             var contador = 0
             while (true){

                 withContext(Dispatchers.Main) {
                     delay(2000)
                     binding.text.text = contador.toString()
                     contador++

                 }

             }


       }*/




    }




}