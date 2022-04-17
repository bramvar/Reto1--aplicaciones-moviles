package com.example.reto1apps

import android.Manifest
import android.app.Activity.RESULT_CANCELED
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import com.example.reto1apps.databinding.FragmentNewPostBinding
import edu.co.icesi.semana4kotlina.UtilDomi
import java.io.File
import java.text.SimpleDateFormat
import java.util.*


class NewPostFragment(val currentUser: String?) : Fragment() {

    private var _binding: FragmentNewPostBinding? = null
    private val binding get() =_binding!!

    private var file: File? = null

    var listener: OnNewPostListerner? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentNewPostBinding.inflate(inflater, container, false)
        //_binding = FragmentNewPostBinding.inflate(layoutInflater)
        var view = binding.root

        val camLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult(),::onCameraResult)
        val galleryLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult(),::onGalleryResult)

        requestPermissions(arrayOf(
            Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE
        ),1)

        binding.cameraBtn.setOnClickListener {
            val i = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            file = File("${activity?.getExternalFilesDir(null)}/photo.png")
            val uri = FileProvider.getUriForFile(activity!!, "com.example.reto1apps", file!!)            //i.putExtra(MediaStore.EXTRA_OUTPUT,uri)
            i.putExtra(MediaStore.EXTRA_OUTPUT,uri)
            Log.e(">>>",file?.path.toString())
            camLauncher.launch(i)
        }

        binding.galleryBtn.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"
            galleryLauncher.launch(intent)
        }

        binding.postBtn.setOnClickListener {

            listener?.let{
                val description = binding.descriptionTextField.text.toString()
                val autor = this.currentUser.toString()
                val location = binding.locationSpinner.selectedItem.toString()
                val date = getCurrentDateTime().toString("yyyy/MM/dd HH:mm:ss")
                val image = file?.path

                if( autor.isEmpty() or location.isEmpty() or date.isEmpty() or description.isEmpty()){
                    Toast.makeText(activity,"missing fields to complete", Toast.LENGTH_LONG).show()
                }else{
                    it.onNewPost(description,autor,location,image,date)
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

    private fun onCameraResult(activityResult: ActivityResult){
        if(activityResult.resultCode == RESULT_OK){
            val bitmap = BitmapFactory.decodeFile(file?.path)
            val thumpnail = Bitmap.createScaledBitmap(bitmap, bitmap.width/4, bitmap.height/4, true)
            binding.selectedImage.setImageBitmap(thumpnail)

        }
    }

    private fun onGalleryResult(activityResult: ActivityResult){
        val uri = activityResult.data?.data
        binding.selectedImage.setImageURI(uri)
        val path = UtilDomi.getPath(activity!!, uri!!)
        Log.e(">>>", uri.toString())
        Log.e(">>>", path!!)

        file = File(path)
    }

    interface  OnNewPostListerner{
        fun onNewPost(description:String, autor:String,city:String,image:String?,date:String)
    }
}