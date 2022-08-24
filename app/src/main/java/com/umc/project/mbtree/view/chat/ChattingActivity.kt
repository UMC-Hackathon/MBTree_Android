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

class ChattingActivity: AppCompatActivity() {
    lateinit var binding: ActivityChattingBinding
    lateinit var multiAdapter: ChatRVAdapter

    private val url = "wss://mbtree.site/chat-websocket/websocket"
    val stompClient = Stomp.over(Stomp.ConnectionProvider.OKHTTP, url)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChattingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val roomId = intent.getIntExtra("roomId", -1)
        val userId = 1
        Log.d("ChattinActivity", "넘어온 roomId: " + roomId)

        if(roomId != -1){
            try {
                runStomp(roomId, userId)
            } catch (e:Exception){
                Log.d("ERROR", "stomp 자체의 오류")
                Log.d("ERROR", e.message.toString())
            }
        }

//        binding.etChatSend.setOnTouchListener(object : View.OnTouchListener{
//            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
//                if(event?.action == MotionEvent.ACTION_UP){
//                    if(event.rawX >= (binding.etChatSend.right - binding.etChatSend.compoundDrawables[2].bounds.width())){
//                        Toast.makeText(applicationContext, "누름", Toast.LENGTH_SHORT).show()
//                        //action here
//                        sendStomp(binding.etChatSend.text.toString(), roomId, userId)
//                        return true
//                    }
//                }
//                return false
//            }
//        })

        // dummy data
        var chatDatas = mutableListOf<Chat>()
        multiAdapter = ChatRVAdapter(this)
        binding.rvChatList.adapter = multiAdapter
        binding.rvChatList.layoutManager = LinearLayoutManager(this)
        multiAdapter.chatList = chatDatas
        multiAdapter.notifyDataSetChanged()
    }

    //stomp구현
    fun runStomp(roomId: Int, userId: Int){
        stompClient.connect()

        stompClient.topic("/topic/chat/${roomId}").subscribe{ topicMessage ->
            Log.d("message Receive", topicMessage.payload)
            val sender = JSONObject(topicMessage.payload).getString("userId").toInt()
            if(sender != userId){
                val content = JSONObject(topicMessage.payload).getString("message")
                multiAdapter.addItem(Chat(content, "12:00", 1))

                runOnUiThread{
                    multiAdapter.notifyDataSetChanged()
                }
            }
        }

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
        }

        binding.etChatSend.setOnTouchListener(object : View.OnTouchListener{
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                if(event?.action == MotionEvent.ACTION_UP){
                    if(event.rawX >= (binding.etChatSend.right - binding.etChatSend.compoundDrawables[2].bounds.width())){
                        Toast.makeText(applicationContext, "누름", Toast.LENGTH_SHORT).show()
                        //action here
                        sendStomp(binding.etChatSend.text.toString(), roomId, userId)
                        return true
                    }
                }
                return false
            }
        })
    }

    fun sendStomp(msg: String, roomId: Int, userId: Int){
        val data = JSONObject()
        data.put("messageType", "CHAT")
        data.put("userId", userId.toString())
        data.put("message", msg)

        stompClient.send("/app/chat.message/${roomId}", data.toString()).subscribe()
        Log.d("Message Send", "내가 보낸거: " + msg)

        multiAdapter.addItem(Chat(msg, "12:00", 2))
        runOnUiThread{
            multiAdapter.notifyDataSetChanged()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        stompClient.disconnect()
    }

}