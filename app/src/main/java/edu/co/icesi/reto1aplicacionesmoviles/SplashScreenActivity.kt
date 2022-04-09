package edu.co.icesi.reto1aplicacionesmoviles

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        Thread.sleep(2000)
        setTheme(R.style.Theme_Reto1AplicacionesMoviles)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
    }
}