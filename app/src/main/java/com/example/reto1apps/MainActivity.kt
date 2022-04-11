package com.example.reto1apps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.reto1apps.databinding.ActivityMainBinding
import com.example.reto1apps.databinding.FragmentHomeBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var users = HashMap<String, User>()

    override fun onCreate(savedInstanceState: Bundle?) {

        val user1 =  User("Hernando123","Hernando Guerrero", "alfa@gmail.com","aplicacionesmoviles")
        val user2 =  User("Lapsuy06","Laura Pasuy Pinilla", "beta@gmail.com","aplicacionesmoviles")

        users.put("alfa@gmail.com",user1)
        users.put("beta@gmail.com",user2)

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.logInBtn.setOnClickListener {
            val i = Intent(this, BarMenuActivity::class.java)
            val email = binding.emailTextField.text.toString()
            val password = binding.pwdTextField.text.toString()
            var currentUser = users.get(email)
            var nameCurrentUser = ""

            if(currentUser != null){
                if(email.equals(currentUser.email) and password.equals(currentUser.password)){
                    nameCurrentUser = currentUser.name
                    i.putExtra("nameUser",nameCurrentUser)
                    startActivity(i)
                }
                else{
                    Toast.makeText(this.baseContext,"Bad credentials", Toast.LENGTH_LONG).show()
                }
            } else{
                Toast.makeText(this.baseContext,"Bad credentials", Toast.LENGTH_LONG).show()
            }
        }
    }
}