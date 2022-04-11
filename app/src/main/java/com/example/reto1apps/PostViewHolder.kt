package com.example.reto1apps

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PostViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    //UI Controllers
    var postAutorRow: TextView = itemView.findViewById(R.id.postAuthorTextView)
    var postLocationRow: TextView = itemView.findViewById(R.id.postLocationTextView)
    var postDateRow: TextView = itemView.findViewById(R.id.postDateTextView)
    var postDescriptionRow: TextView = itemView.findViewById(R.id.postDescriptionTextView)

    init {

    }

}


