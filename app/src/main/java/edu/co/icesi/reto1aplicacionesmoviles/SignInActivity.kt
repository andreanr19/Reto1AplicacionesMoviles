package edu.co.icesi.reto1aplicacionesmoviles

import android.content.Intent
import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import edu.co.icesi.reto1aplicacionesmoviles.Reto1Application.Companion.prefs
import edu.co.icesi.reto1aplicacionesmoviles.fragments.SecondActivity

class SignInActivity : AppCompatActivity() {

    //Declaración de variables
    private lateinit var email_login : EditText
    private lateinit var password_login : EditText
    private lateinit var wrong_credentials : TextView
    private lateinit var login_btn : Button
    private lateinit var signup_btn :Button
    private lateinit var rememberme_cb : CheckBox

    var isRemembered = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        //Inicialización de las variables
        email_login = findViewById(R.id.email_login)
        password_login = findViewById(R.id.password_login)
        wrong_credentials = findViewById(R.id.wrong_credentials)
        login_btn = findViewById(R.id.login_btn)
        signup_btn = findViewById(R.id.signup_btn)
        rememberme_cb = findViewById(R.id.rememberme_cb)
        signup_btn.paintFlags = signup_btn.paintFlags or Paint.UNDERLINE_TEXT_FLAG

        //prefs.saveRemember(false)
        isRemembered = prefs.getRemember()

        if(isRemembered){
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
            finish()
        }


        //evento signup
        signup_btn.setOnClickListener{
            startActivity(Intent(this, SignUpActivity::class.java))
        }

        //evento del signin
        login_btn.setOnClickListener {
            val email : String = email_login.text.toString()
            val password : String = password_login.text.toString()
            val checked : Boolean = rememberme_cb.isChecked

            if((email =="alfa@gmail.com" || email == "beta@gmail.com") && password == "aplicacionesmoviles"){
                if(email=="alfa@gmail.com"){

                    prefs.saveUsernameAlfa(email)
                    prefs.saveNameAlfa("Alfa")
                    prefs.saveBioAlfa("Hello, I'm alfa")
                    prefs.savePassword(password)
                    prefs.saveRemember(checked)
                    prefs.saveIsbeta(false)
                    Toast.makeText(this, "Information saved", Toast.LENGTH_LONG).show()
                    val intent = Intent(this, SecondActivity::class.java )
                    startActivity(intent)
                    finish()
                }else{
                    prefs.saveUsernameBeta(email)
                    prefs.saveNameBeta("Beta")
                    prefs.saveBioBeta("Hello, I'm beta")
                    prefs.savePassword(password)
                    prefs.saveRemember(checked)
                    prefs.saveIsbeta(true)

                    Toast.makeText(this, "Information saved", Toast.LENGTH_LONG).show()

                    val intent = Intent(this, SecondActivity::class.java )
                    startActivity(intent)
                    finish()
                }
            }else{

                val wrongcredentialstext = "Sorry, your password is incorrect. Please check your password carefully."
                wrong_credentials.setText(wrongcredentialstext)
                //Toast.makeText(this, "Credenciales incorrectas", Toast.LENGTH_LONG).show()

            }
        }
    }
}