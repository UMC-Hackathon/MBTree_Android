package com.umc.project.mbtree.view.chat

import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.umc.project.mbtree.data.Chat
import com.umc.project.mbtree.databinding.ActivityChattingBinding
import org.json.JSONObject
import ua.naiksoftware.stomp.Stomp
import ua.naiksoftware.stomp.dto.LifecycleEvent
import ua.naiksoftware.stomp.dto.StompHeader
import kotlin.concurrent.thread

class ChattingActivity: AppCompatActivity() {
    lateinit var binding: ActivityChattingBinding
    lateinit var multiAdapter: ChatRVAdapter
    val chatList = mutableListOf<Chat>()
    private val url = "wss://mbtree.site"
    val stompClient = Stomp.over(Stomp.ConnectionProvider.OKHTTP, url)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChattingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        try {
            runStomp()
        } catch (e:Exception){
            Log.d("ERROR", "stomp자체의 오류")
            Log.d("ERROR", e.message.toString())
        }

//        WebSocketManager.init(serverUrl, this)
//        thread{
//            kotlin.run{
//                WebSocketManager.connect()
//            }
//        }

        binding.etChatSend.setOnTouchListener(object : View.OnTouchListener{
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                if(event?.action == MotionEvent.ACTION_UP){
                    if(event.rawX >= (binding.etChatSend.right - binding.etChatSend.compoundDrawables[2].bounds.width())){
                        Toast.makeText(applicationContext, "누름", Toast.LENGTH_SHORT).show()
                        //action here
//                        if(WebSocketManager.sendMessage("클라이언트에서 보낸다")){
//                            addText("Send")
//                        }
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

    //stomp구현
    fun runStomp(){
        stompClient.topic("/topic/chat/1").subscribe{ topicMessage ->
            Log.d("message Receive", topicMessage.payload)
        }

        val headerList = arrayListOf<StompHeader>()
        headerList.add(StompHeader("messageType", "CHAT"))
        headerList.add(StompHeader("userId", "1"))
        headerList.add(StompHeader("message", "1"))
        stompClient.connect()

        stompClient.lifecycle().subscribe{ lifecycleEvent ->
            when(lifecycleEvent.type){
                LifecycleEvent.Type.OPENED -> {
                    Log.d("OPENED", "opened")
                }
                LifecycleEvent.Type.CLOSED -> {
                    Log.d("CLOSED", "closed")
                }
                LifecycleEvent.Type.ERROR -> {
                    Log.d("ERROR", "error")
                    Log.i("CONNECT ERROR", lifecycleEvent.exception.toString())
                }
                else -> {
                    Log.d("else", lifecycleEvent.message)
                }
            }

            val data = JSONObject()
            data.put("messageType", "CHAT")
            data.put("userId", "1")
            data.put("message", "nayeon test")

            stompClient.send("/app/chat.message/1", data.toString()).subscribe()
        }
    }

}