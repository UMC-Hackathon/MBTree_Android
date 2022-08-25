package com.umc.project.mbtree.view.Login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.room.ext.ROOM_PACKAGE
import com.umc.project.mbtree.databinding.ActivitySelectMbtiBinding
import kotlinx.android.synthetic.main.activity_select_mbti.*

class SelectMBTIActivity : AppCompatActivity() {
    lateinit var binding: ActivitySelectMbtiBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelectMbtiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val text = arrayOf(
            "ISTJ",
            "ESTJ",
            "ISTP",
            "ESTP",
            "ISFJ",
            "ESFJ",
            "ISFP",
            "ESFP",
            "INTJ",
            "ENTJ",
            "INTP",
            "ENTP",
            "INFJ",
            "ENFJ",
            "INFP",
            "ENFP"
        )

        // Adapter 추가
        val mbtiGVAdapter = MbtiGVAdapter(this, text)
        gv_mbti.adapter = mbtiGVAdapter


    }



}