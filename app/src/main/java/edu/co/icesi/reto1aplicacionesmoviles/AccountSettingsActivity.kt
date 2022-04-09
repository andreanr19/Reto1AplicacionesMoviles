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
import edu.co.icesi.reto1aplicacionesmoviles.Model.User
import edu.co.icesi.reto1aplicacionesmoviles.Reto1Application.Companion.prefs
import edu.co.icesi.reto1aplicacionesmoviles.databinding.ActivityAccountSettingsBinding
import edu.co.icesi.reto1aplicacionesmoviles.databinding.FragmentProfileBinding
import edu.co.icesi.reto1aplicacionesmoviles.fragments.ProfileFragment
import edu.co.icesi.reto1aplicacionesmoviles.fragments.SecondActivity

class AccountSettingsActivity : AppCompatActivity() {

    private lateinit var binding : ActivityAccountSettingsBinding

    private lateinit var logout_btn : Button
    private lateinit var close_profile_btn : ImageView
    var user = prefs.getLoggedUser()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityAccountSettingsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        close_profile_btn = findViewById(R.id.close_profile_btn)
        logout_btn = findViewById(R.id.logout_btn)


        checkDetails()

        logout_btn.setOnClickListener {


            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
            prefs.saveRemember(false)
            prefs.logOut()
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


            user!!.name = binding.fullNameProfilefragment.text.toString()
            user!!.username = binding.usernameProfilefragment.text.toString()
            user!!.bio = binding.bioProfilefragment.text.toString()

            prefs.updateUser(user!!)

        }else{
            Toast.makeText(this, "Username and name are required", Toast.LENGTH_LONG).show()
        }
    }

    fun checkDetails(){
            binding.fullNameProfilefragment.setText(user?.name)
            binding.usernameProfilefragment.setText(user?.username)
            binding.bioProfilefragment.setText(user?.bio)


        }




}