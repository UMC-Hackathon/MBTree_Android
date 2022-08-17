package com.umc.project.mbtree.view.chat

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.umc.project.mbtree.databinding.ActivityAnswerBinding

class AnswerActivity: AppCompatActivity() {

    lateinit var binding: ActivityAnswerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnswerBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}