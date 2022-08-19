package com.umc.project.mbtree.view

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.appcompat.app.AppCompatActivity
import com.umc.project.mbtree.databinding.ProfileLayoutBinding

class ProfileActivity: AppCompatActivity() {
    lateinit var binding: ProfileLayoutBinding
    private val OPEN_GALLERY = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ProfileLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ibBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

        }

        binding.gallery.setOnClickListener{openGallery()}
    }

    private fun openGallery()
    {
        val intent : Intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.setType("image/*")
        startActivityForResult(intent,OPEN_GALLERY)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(resultCode == Activity.RESULT_OK)
        {
            if(requestCode==OPEN_GALLERY)
            {
                var cuurentImageURL : Uri? =data?.data

                try {
                    val bitmap = MediaStore.Images.Media.getBitmap(contentResolver,cuurentImageURL)
                    binding.profileImageview.setImageBitmap(bitmap)

                }catch (e:Exception){
                    e.printStackTrace()
                }

            }
        }
    }

}