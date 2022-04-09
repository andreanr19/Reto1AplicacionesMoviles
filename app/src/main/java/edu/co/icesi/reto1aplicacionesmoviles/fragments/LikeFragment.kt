package edu.co.icesi.reto1aplicacionesmoviles.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import edu.co.icesi.reto1aplicacionesmoviles.R

class LikeFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_like, container, false)
    }

    companion object {
    @JvmStatic
        fun newInstance() =
            LikeFragment()
    }
}