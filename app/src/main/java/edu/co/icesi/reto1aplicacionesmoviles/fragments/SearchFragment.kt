package edu.co.icesi.reto1aplicacionesmoviles.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import edu.co.icesi.reto1aplicacionesmoviles.Adapter.UserAdapter
import edu.co.icesi.reto1aplicacionesmoviles.Model.User
import edu.co.icesi.reto1aplicacionesmoviles.R
import edu.co.icesi.reto1aplicacionesmoviles.databinding.FragmentSearchBinding


class SearchFragment : Fragment() {



    private var _binding: FragmentSearchBinding?=null
    private val binding get() = _binding!!

    //STATE
    private val users = ArrayList<String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentSearchBinding.inflate(inflater,container,false)

        val view = binding.root

        //Recrear el estado


        return view
    }



    companion object {
        @JvmStatic
        fun newInstance() =
            SearchFragment()
    }
}