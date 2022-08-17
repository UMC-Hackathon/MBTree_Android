package com.umc.project.mbtree.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.GridView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.umc.project.mbtree.R
import com.umc.project.mbtree.databinding.ActivitySelectMbtiBinding
import kotlinx.android.synthetic.main.activity_select_mbti.*
import kotlinx.android.synthetic.main.item_mbti.*

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

//        binding.gvMbti.setOnItemClickListener(object : AdapterView.OnItemClickListener{
//            override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
//                Toast.makeText(applicationContext, "test", Toast.LENGTH_SHORT).show()
//            }
//
//        })


//


//        bt_mbti.setOnClickListener{
//            val intent = Intent(this, NicknameActivity::class.java)
//            startActivity(intent)
//        }

//        mbtiGVAdapter.setMyItemClickListener(object: MbtiGVAdapter.MyItemClickListener{
//            override fun onItemClick() {
//            }
//
//        })
//
//        mbtiGVAdapter.setMyItemClickListener(object: MbtiGVAdapter.MyItemClickListener{
//            override fun onItemClick() {
//                // AlbumFragment로 전환
//                (context as SelectMBTIActivity).supportFragmentManager.beginTransaction()
//                    .replace(R.id.act,NicknameActivity())
//                    .commitAllowingStateLoss()
//            }
//
//        })

    }



}