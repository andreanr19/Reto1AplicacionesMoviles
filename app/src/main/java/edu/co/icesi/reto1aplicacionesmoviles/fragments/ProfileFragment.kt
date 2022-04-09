package edu.co.icesi.reto1aplicacionesmoviles.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import edu.co.icesi.reto1aplicacionesmoviles.AccountSettingsActivity
import edu.co.icesi.reto1aplicacionesmoviles.R
import edu.co.icesi.reto1aplicacionesmoviles.Reto1Application.Companion.prefs
import edu.co.icesi.reto1aplicacionesmoviles.databinding.FragmentProfileBinding


class ProfileFragment : Fragment() {

    private var _binding:FragmentProfileBinding? =null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentProfileBinding.inflate(inflater,container,false)

        val view = binding.root

        binding.editAccountBtn.setOnClickListener{
            startActivity(Intent(context, AccountSettingsActivity::class.java))
        }

        checkDetails()

        return view
    }

    fun checkDetails(){
        if(prefs.getIsbeta()){
            binding.fullNameProfilefragment.setText(prefs.getNameBeta())
            binding.bioProfilefragment.setText(prefs.getBioBeta())

        }else{
            binding.fullNameProfilefragment.setText(prefs.getNameAlfa())
            binding.bioProfilefragment.setText(prefs.getBioAlfa())

        }
    }

    override fun onPause() {
        super.onPause()
        Log.e(">>>", "on pause" + prefs.getPosts())
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            ProfileFragment()
    }
}