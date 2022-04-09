package edu.co.icesi.reto1aplicacionesmoviles

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import edu.co.icesi.reto1aplicacionesmoviles.Reto1Application.Companion.prefs
import edu.co.icesi.reto1aplicacionesmoviles.databinding.ActivityAccountSettingsBinding
import edu.co.icesi.reto1aplicacionesmoviles.databinding.FragmentProfileBinding
import edu.co.icesi.reto1aplicacionesmoviles.fragments.ProfileFragment
import edu.co.icesi.reto1aplicacionesmoviles.fragments.SecondActivity

class AccountSettingsActivity : AppCompatActivity() {

    private lateinit var binding : ActivityAccountSettingsBinding

    private lateinit var logout_btn : Button
    private lateinit var close_profile_btn : ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityAccountSettingsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        close_profile_btn = findViewById(R.id.close_profile_btn)
        logout_btn = findViewById(R.id.logout_btn)


        checkDetails()

        logout_btn.setOnClickListener {
            prefs.prefsClean()

            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.saveInfoProfileBtn.setOnClickListener{
            updateUserInfo()

            Toast.makeText(this, "Account information has been updated successfully", Toast.LENGTH_LONG).show()

            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    private fun updateUserInfo() {

        if(!(binding.fullNameProfilefragment.text.toString()=="" || binding.usernameProfilefragment.text.toString()=="")) {
            if (prefs.getIsbeta()) {
                prefs.saveNameBeta(binding.fullNameProfilefragment.text.toString())
                prefs.saveUsernameBeta(binding.usernameProfilefragment.text.toString())
                prefs.saveBioBeta(binding.bioProfilefragment.text.toString())

            } else {

                prefs.saveNameAlfa(binding.fullNameProfilefragment.text.toString())
                prefs.saveUsernameAlfa(binding.usernameProfilefragment.text.toString())
                prefs.saveBioAlfa(binding.bioProfilefragment.text.toString())
            }
        }else{
            Toast.makeText(this, "Username and name are required", Toast.LENGTH_LONG).show()
        }
    }

    fun checkDetails(){
        if(prefs.getIsbeta()){
            binding.fullNameProfilefragment.setText(prefs.getNameBeta())
            binding.usernameProfilefragment.setText(prefs.getUsernameBeta())
            binding.bioProfilefragment.setText(prefs.getBioBeta())
        }else{
            binding.fullNameProfilefragment.setText(prefs.getNameAlfa())
            binding.usernameProfilefragment.setText(prefs.getUsernameAlfa())
            binding.bioProfilefragment.setText(prefs.getBioAlfa())
        }
    }



}