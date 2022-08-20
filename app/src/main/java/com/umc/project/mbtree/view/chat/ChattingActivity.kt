package com.umc.project.mbtree.view.chat

import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.umc.project.mbtree.data.Chat
import com.umc.project.mbtree.databinding.ActivityChattingBinding
import kotlin.concurrent.thread

class ChattingActivity: AppCompatActivity(), MessageListener {
    lateinit var binding: ActivityChattingBinding
    lateinit var multiAdapter: ChatRVAdapter
    val chatList = mutableListOf<Chat>()
    private val serverUrl = "https://mbtree.site"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChattingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        WebSocketManager.init(serverUrl, this)
        thread{
            kotlin.run{
                WebSocketManager.connect()
            }
        }

        binding.etChatSend.setOnTouchListener(object : View.OnTouchListener{
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                if(event?.action == MotionEvent.ACTION_UP){
                    if(event.rawX >= (binding.etChatSend.right - binding.etChatSend.compoundDrawables[2].bounds.width())){
                        //action here
                        if(WebSocketManager.sendMessage("클라이언트에서 보낸다")){
                            addText("Send")
                        }
                        return true
                    }
                }
                return false
            }
        })

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
        binding.rvChatList.layoutManager = LinearLayoutManager(this)
        multiAdapter.chatList2 = chatDatas
        multiAdapter.notifyDataSetChanged()
    }

    //웹소켓 관련 로직
    override fun onConnectSuccess() {
        addText("Connected successfully\n")
    }

    override fun onConnectFailed() {
        addText("Connection failed \n")
    }

    override fun onClose() {
        addText("Closed successfully\n")
    }

    override fun onMessage(text: String?) {
        addText("Receive message: $text \n")
    }

    private fun addText(text: String?){
        runOnUiThread{
            binding.etChatSend.text.append(text)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        WebSocketManager.close()
    }

}