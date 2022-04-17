package com.example.reto1apps

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.reto1apps.R.id.postImage

class PostViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    //UI Controllers
    var postAutorRow: TextView = itemView.findViewById(R.id.postAuthorTextView)
    var postLocationRow: TextView = itemView.findViewById(R.id.postLocationTextView)
    var postDateRow: TextView = itemView.findViewById(R.id.postDateTextView)
    var postDescriptionRow: TextView = itemView.findViewById(R.id.postDescriptionTextView)
    var postImageRow: ImageView = itemView.findViewById(postImage)

    init {

    }

}


