package com.umc.project.mbtree.view.chat

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.umc.project.mbtree.data.Chat
import com.umc.project.mbtree.databinding.ActivityChattingBinding

class ChattingActivity: AppCompatActivity() {
    lateinit var binding: ActivityChattingBinding
    lateinit var multiAdapter: ChatRVAdapter
    val chatList = mutableListOf<Chat>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChattingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //dummy data
        var chatDatas = ArrayList<Chat>()
        chatDatas.apply{
            add(Chat("안녕하세요", "12:00", 1))
            add(Chat("네 안녕하세요", "12:00", 2))
            add(Chat("지금 뭐하세요?", "12:00", 1))
            add(Chat("저 코딩하는데요", "12:00", 2))
            add(Chat("시간어택", "12:00", 3))
        }

        multiAdapter = ChatRVAdapter(this, chatDatas)
        binding.rvChatList.adapter = multiAdapter
        multiAdapter.chatList2 = chatDatas
        multiAdapter.notifyDataSetChanged()
    }

}