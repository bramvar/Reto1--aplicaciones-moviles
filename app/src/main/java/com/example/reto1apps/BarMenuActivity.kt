package com.example.reto1apps

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.reto1apps.databinding.ActivityBarMenuBinding
import com.example.reto1apps.databinding.ActivityMainBinding

class BarMenuActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBarMenuBinding

    private lateinit var homeFragment: HomeFragment
    private lateinit var postFragment: NewPostFragment
    private lateinit var profileFragment: ProfileFragment

    override fun onCreate(savedInstanceState: Bundle?) {

        val currentUser = intent.getStringExtra("nameUser")

        super.onCreate(savedInstanceState)
        binding = ActivityBarMenuBinding.inflate(layoutInflater)
        val view =binding.root
        setContentView(view)

        homeFragment = HomeFragment.newInstance()
        postFragment = NewPostFragment.newInstance(currentUser)
        profileFragment = ProfileFragment.newInstance()

        postFragment.listener = homeFragment
        showFragment(homeFragment)

        binding.mainNavBar.setOnItemSelectedListener { menuItem->
            if(menuItem.itemId == R.id.homeItem){
                showFragment(homeFragment)
            }else if(menuItem.itemId == R.id.postItem){
                showFragment(postFragment)
            }else if (menuItem.itemId == R.id.profileItem){
                showFragment(profileFragment)
            }
            true
        }
    }

    fun showFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainer,fragment)
        transaction.commit()
    }
}