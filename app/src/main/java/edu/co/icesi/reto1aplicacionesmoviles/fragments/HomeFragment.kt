package edu.co.icesi.reto1aplicacionesmoviles.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import edu.co.icesi.reto1aplicacionesmoviles.Adapter.PostAdapter
import edu.co.icesi.reto1aplicacionesmoviles.HomeViewModel
import edu.co.icesi.reto1aplicacionesmoviles.Model.Post
import edu.co.icesi.reto1aplicacionesmoviles.R
import edu.co.icesi.reto1aplicacionesmoviles.databinding.FragmentHomeBinding

class HomeFragment : Fragment(), PostFragment.OnNewPostListener {

    private var _binding:FragmentHomeBinding?=null
    private val binding get() = _binding!!

    private lateinit var viewModel : HomeViewModel

    //STATE
    private val adapter = PostAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        val view = binding.root

        //recrear el estado
        val postRecycler = binding.recyclerViewHome
        postRecycler.setHasFixedSize(true)
        postRecycler.layoutManager = LinearLayoutManager(activity)
        postRecycler.adapter = adapter

        viewModel= ViewModelProvider(this).get(HomeViewModel::class.java)

       viewModel.posts.observe(viewLifecycleOwner) {

            adapter.clear()
            for (post in it) {

                adapter.addPost(post)
            }

        }


        return view
    }


    override fun onResume() {
        super.onResume()
        adapter.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
          @JvmStatic
        fun newInstance() =
            HomeFragment()
    }

    //Método que lo ejecuta el PostFragment
    override fun onNewPost(post:Post) {
        //modificamos el estado

        adapter.addPost(post)
    }
}