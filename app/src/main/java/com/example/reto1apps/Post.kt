package com.example.reto1apps

import java.util.*

class Post {

    var id:String
    var description:String
    var autor: String
    var location:String
    var image:String?
    var date: String

    constructor(
        description: String,
        autor: String,
        city: String,
        image: String?,
        date: String
    ) {
        this.id = UUID.randomUUID().toString()
        this.description = description
        this.autor = autor
        this.location = city
        this.image = image
        this.date = date
    }



}