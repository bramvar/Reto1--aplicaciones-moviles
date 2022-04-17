package com.example.reto1apps

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.reto1apps.databinding.FragmentHomeBinding


class HomeFragment : Fragment(), NewPostFragment.OnNewPostListerner {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    //State
    private val adapter = PostsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view  = binding.root

        //recreate the state
        val postRecycler = binding.postsRecycler
        postRecycler.setHasFixedSize(true)
        postRecycler.layoutManager = LinearLayoutManager(activity)
        postRecycler.adapter = adapter


        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
    }

    override fun onNewPost(
        description: String,
        autor: String,
        location: String,
        image: String?,
        date: String
    ) {
        val newPost =Post(description,autor,location,image,date)
        adapter.addPost(newPost)
    }
}