package com.example.reto1apps

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import java.io.File

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
        holder.postImageRow.setImageBitmap(setImageBitmap(postn.image))
    }

    fun addPost(post:Post){
        posts.add(post)
    }

    override fun getItemCount(): Int {
        return posts.size
    }
}

private fun setImageBitmap(image: String?): Bitmap? {
    val file = image?.let { File(it) }
    val bitmap = BitmapFactory.decodeFile(file?.path)
    val thumpnail = Bitmap.createScaledBitmap(bitmap, bitmap.width/4, bitmap.height/4, true)

    return thumpnail
}
