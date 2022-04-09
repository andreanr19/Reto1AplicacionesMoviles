package edu.co.icesi.reto1aplicacionesmoviles

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import edu.co.icesi.reto1aplicacionesmoviles.fragments.Fragment
import edu.co.icesi.reto1aplicacionesmoviles.fragments.SecondActivity

class MainActivity : AppCompatActivity() {

    //Declaración de variables
    private lateinit var usernameET: EditText
    private lateinit var passwordET: EditText
    private lateinit var loginBTN: Button
    private lateinit var rememberCB : CheckBox


    //Declaración del sharedpreferences
    lateinit var sharedPreferences: SharedPreferences
    var isRemembered = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Inicialización de variables
        usernameET = findViewById(R.id.usernameET)
        passwordET = findViewById(R.id.passwordET)
        loginBTN = findViewById(R.id.loginBTN)
        rememberCB = findViewById(R.id.rememberCB)
        sharedPreferences = getSharedPreferences("SHARED_PREF", MODE_PRIVATE)

        isRemembered = sharedPreferences.getBoolean("CHECKBOX",false)
        if(isRemembered){
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
            finish()
        }




        loginBTN.setOnClickListener {
            val username : String = usernameET.text.toString()
            val password : String = passwordET.text.toString()
            val checked : Boolean = rememberCB.isChecked
            val editor : SharedPreferences.Editor = sharedPreferences.edit()

            if((username == "alfa@gmail.com" || username == "beta@gmail.com") && password == "aplicacionesmoviles"){
                if(username=="alfa@gmail.com"){
                    editor.putString("NAME_ALFA",username)
                    editor.putString("PASSWORD_ALFA", password)
                    editor.putBoolean("IS_ALFA", true)
                    editor.putBoolean("CHECKBOX",checked)
                    editor.apply()
                    Toast.makeText(this, "Information saved", Toast.LENGTH_LONG).show()

                    val intent = Intent(this, SecondActivity::class.java )
                    startActivity(intent)
                    finish()
                }else{
                    editor.putString("NAME_BETA",username)
                    editor.putString("PASSWORD_BETA", password)
                    editor.putBoolean("IS_ALFA", false)
                    editor.putBoolean("CHECKBOX",checked)
                    editor.apply()
                    Toast.makeText(this, "Information saved", Toast.LENGTH_LONG).show()

                    val intent = Intent(this, Fragment::class.java )
                    startActivity(intent)
                    finish()
                }
            }else{
                Toast.makeText(this, "Credenciales incorrectas", Toast.LENGTH_LONG).show()
            }
        }
    }

}


