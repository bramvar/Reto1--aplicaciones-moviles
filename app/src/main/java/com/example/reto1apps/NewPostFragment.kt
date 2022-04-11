package com.example.reto1apps

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.reto1apps.databinding.FragmentNewPostBinding
import java.text.SimpleDateFormat
import java.util.*


class NewPostFragment(val currentUser: String?) : Fragment() {

    private var _binding: FragmentNewPostBinding? = null
    private val binding get() =_binding!!

    var listener: OnNewPostListerner? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentNewPostBinding.inflate(inflater, container, false)
        var view = binding.root

        binding.postBtn.setOnClickListener {

            listener?.let{
                val description = binding.descriptionTextField.text.toString()
                val autor = this.currentUser.toString()
                val location = binding.locationSpinner.selectedItem.toString()
                val date = getCurrentDateTime().toString("yyyy/MM/dd HH:mm:ss")

                if( autor.isEmpty() or location.isEmpty() or date.isEmpty() or description.isEmpty()){
                    Toast.makeText(activity,"missing fields to complete", Toast.LENGTH_LONG).show()
                }else{
                    it.onNewPost(autor,location,date,description,"Image")
                }
            }


        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance(user:String?) = NewPostFragment(user)
    }

    fun Date.toString(format: String, locale: Locale = Locale.getDefault()): String {
        val formatter = SimpleDateFormat(format, locale)
        return formatter.format(this)
    }

    fun getCurrentDateTime(): Date {
        return Calendar.getInstance().time
    }

    interface  OnNewPostListerner{
        fun onNewPost(description:String, autor:String,city:String,date:String,image:String)
    }
}