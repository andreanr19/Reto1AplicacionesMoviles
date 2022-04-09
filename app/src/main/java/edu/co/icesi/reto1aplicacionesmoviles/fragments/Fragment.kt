package edu.co.icesi.reto1aplicacionesmoviles.fragments

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import edu.co.icesi.reto1aplicacionesmoviles.MainActivity
import edu.co.icesi.reto1aplicacionesmoviles.R

class Fragment : AppCompatActivity() {

    lateinit var preferences: SharedPreferences

    private lateinit var username2ET : EditText
    private lateinit var logoutBTN : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment)

        username2ET = findViewById(R.id.username2ET)
        logoutBTN = findViewById(R.id.logoutBTN)

        preferences = getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE)

        val isAlfa : Boolean = preferences.getBoolean("IS_ALFA", true)
        if(isAlfa){
            val name = preferences.getString("NAME_ALFA", "")
            username2ET.setText(name)
        }else{
            val name = preferences.getString("NAME_BETA", "")
            username2ET.setText(name)
        }


        logoutBTN.setOnClickListener {
            val editor : SharedPreferences.Editor = preferences.edit()
            editor.clear()
            editor.apply()

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}