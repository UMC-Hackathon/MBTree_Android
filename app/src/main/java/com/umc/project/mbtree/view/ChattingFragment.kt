package com.umc.project.mbtree.view

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.umc.project.mbtree.R
import com.umc.project.mbtree.data.Chat
import com.umc.project.mbtree.data.User
import com.umc.project.mbtree.databinding.FragmentChattingBinding
import com.umc.project.mbtree.view.chat.ChatListRVAdapter
import com.umc.project.mbtree.view.chat.ChattingActivity

class ChattingFragment: Fragment() {

    lateinit var binding: FragmentChattingBinding
    private val serverUrl = "https://mbtree.site"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChattingBinding.inflate(inflater, container, false)

        //dummy data
        var datas = ArrayList<User>()
        datas.apply {
            add(User(1, "user1", "123", "ISFP"))
            add(User(2, "user2", "234", "ISFJ"))
            add(User(3, "user3", "345", "ESFP"))
        }

        //채팅리스트 어댑터 붙이기
        val adaper = ChatListRVAdapter(datas)
        binding.rvChatList.adapter = adaper
        binding.rvChatList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        binding.btnChatListStart.setOnClickListener{
            binding.btnChatListStart.setBackgroundColor(resources.getColor(R.color.white))
            binding.btnChatListStart.setTextColor(resources.getColor(R.color.main_color))
            binding.btnChatListStart.text = "매칭 중입니다..."

            val intent = Intent(activity, ChattingActivity::class.java)
            startActivity(intent)
        }

        return binding.root
    }
}