package com.example.reto1apps

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class PostsAdapter: RecyclerView.Adapter<PostViewHolder>() {

    private val posts = ArrayList<Post>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        //inflate: XML -> View
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.post_row, parent, false)
        val postViewHolder = PostViewHolder(view)

        return postViewHolder
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val postn = posts[position]
        holder.postAutorRow.text = postn.autor
        holder.postLocationRow.text = postn.location
        holder.postDateRow.text = postn.date
        holder.postDescriptionRow.text = postn.description
    }

    fun addPost(post:Post){
        posts.add(post)
    }

    override fun getItemCount(): Int {
        return posts.size
    }
}