package edu.co.icesi.reto1aplicacionesmoviles.fragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import edu.co.icesi.reto1aplicacionesmoviles.R
import edu.co.icesi.reto1aplicacionesmoviles.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    private lateinit var homeFragment : HomeFragment
    private lateinit var postFragment : PostFragment
    private lateinit var profileFragment: ProfileFragment
    private lateinit var likeFragment: LikeFragment
    private lateinit var searchFragment: SearchFragment

    private lateinit var binding: ActivitySecondBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        homeFragment = HomeFragment.newInstance()
        postFragment = PostFragment.newInstance()
        profileFragment = ProfileFragment.newInstance()
        likeFragment = LikeFragment.newInstance()
        searchFragment = SearchFragment.newInstance()

        //suscripción
        //postFragment.listener = homeFragment

        showFragment(homeFragment)

        //Evento del navigator
       binding.navigator.setOnNavigationItemSelectedListener { menuItem->

            if(menuItem.itemId == R.id.homeitem){
                showFragment(homeFragment)
            }else if(menuItem.itemId == R.id.searchitem){
                showFragment(searchFragment)
            }else if(menuItem.itemId == R.id.postitem){
                showFragment(postFragment)
            }else if(menuItem.itemId == R.id.likeitem){
                showFragment(likeFragment)
            }else if(menuItem.itemId == R.id.profileitem){
                showFragment(profileFragment)
            }

            true
        }
    }

    fun showFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainer, fragment)
        transaction.commit()

    }
}