package com.example.reto1apps

import java.util.*

class User {
    var id:String
    var user:String
    var name:String
    var email: String
    var password:String

    constructor(user:String,name:String,email:String,password:String){
        this.id = UUID.randomUUID().toString()
        this.user = user
        this.name = name
        this.email = email
        this.password = password
    }
}