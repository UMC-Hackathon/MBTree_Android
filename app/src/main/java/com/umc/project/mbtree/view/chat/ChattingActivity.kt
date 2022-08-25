package com.umc.project.mbtree.view.chat

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.postDelayed
import androidx.recyclerview.widget.LinearLayoutManager
import com.umc.project.mbtree.R
import com.umc.project.mbtree.data.*
import com.umc.project.mbtree.databinding.ActivityChattingBinding
import com.umc.project.mbtree.remote.ChatRetrofitInterface
import com.umc.project.mbtree.remote.getRetrofit
import kotlinx.android.synthetic.main.item_chat_question.view.*
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ua.naiksoftware.stomp.Stomp
import ua.naiksoftware.stomp.dto.LifecycleEvent
import ua.naiksoftware.stomp.dto.StompHeader

class ChattingActivity: AppCompatActivity() {
    lateinit var binding: ActivityChattingBinding
    lateinit var multiAdapter: ChatRVAdapter
//    private var flag: Boolean = true

    private val url = "wss://mbtree.site/chat-websocket/websocket"
    val stompClient = Stomp.over(Stomp.ConnectionProvider.OKHTTP, url)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChattingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val roomId = intent.getIntExtra("roomId", -1)
        val userId = 3
        Log.d("ChattinActivity", "넘어온 roomId: " + roomId)

        getQuiz(roomId)

        if(roomId != -1){
            try {
                runStomp(roomId, userId)
            } catch (e:Exception){
                Log.d("ERROR", "stomp 자체의 오류")
                Log.d("ERROR", e.message.toString())
            }
        }

        // dummy data
        var chatDatas = mutableListOf<Chat>()
        multiAdapter = ChatRVAdapter(this)
        binding.rvChatList.adapter = multiAdapter
        binding.rvChatList.layoutManager = LinearLayoutManager(this)

        multiAdapter.setMyItemClickListener(object: ChatRVAdapter.MyItemClickListener{
            override fun clickButton1(chat:Chat) {
                Toast.makeText(applicationContext, "1번 선택", Toast.LENGTH_SHORT).show()
                setQuizAnswer(roomId, userId, 1)
                getQuizAnswer(roomId)
//                flag = true
            }
            override fun clickButton2(chat:Chat) {
                Toast.makeText(applicationContext, "2번 선택", Toast.LENGTH_SHORT).show()
                setQuizAnswer(roomId, userId, 2)
                getQuizAnswer(roomId)
//                flag = true
            }
        })
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
                        //action here
//                        Toast.makeText(applicationContext, "누름", Toast.LENGTH_SHORT).show()
                        sendStomp(binding.etChatSend.text.toString(), roomId, userId)
                        binding.etChatSend.text = null
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

    //키워드 질문 가져오기
    fun getQuiz(roomId: Int){
        val retrofitService = getRetrofit().create(ChatRetrofitInterface::class.java)
        retrofitService.getQuiz(roomId)
            .enqueue(object: Callback<Quiz>{
                override fun onResponse(call: Call<Quiz>, response: Response<Quiz>) {
                    var resp = response.body()!!
                    if(resp.isSuccess){
                        val result = resp.result

                        Log.d("ChattingActivity", "Q. ${result.quiz}")
                        Log.d("ChattingActivity", result.answer1)
                        Log.d("ChattingActivity", result.answer2)
                        binding.tvChatKeyword.visibility = View.VISIBLE
                        binding.tvChatKeyword.text = "Q. " + result.keyword

                        Handler(Looper.getMainLooper()).postDelayed({
                            binding.tvChatKeyword.visibility = View.GONE
                            multiAdapter.addItem(Chat(result.quiz, "00:00", 3,
                                result.quiz, result.answer1, result.answer2))

                            runOnUiThread{
                                multiAdapter.notifyDataSetChanged()
                            }
                        }, 50000)
                    }
                }

                override fun onFailure(call: Call<Quiz>, t: Throwable) {
                    Log.d("Chatting Activity", t.message.toString())
                }

            })
    }

    //퀴즈 정답 입력
    fun setQuizAnswer(roomId:Int, userId:Int, answer:Int){
        val retrofitService = getRetrofit().create(ChatRetrofitInterface::class.java)
        retrofitService.setQuizAnswer(roomId, userId, answer)
            .enqueue(object: Callback<QuizAnswer>{
                override fun onResponse(
                    call: Call<QuizAnswer>,
                    response: Response<QuizAnswer>
                ) {
                    var resp = response.body()!!
                    if(resp.isSuccess){
                        Log.d("ChattingActivity", resp.result!!.user1)
                        Log.d("ChattingActivity", resp.result!!.user2)
                        Log.d("ChattingActivity", resp.result!!.quiz)
                    }
                    else{
                        Log.d("Chatting Activity",resp.message)
                    }
                }

                override fun onFailure(call: Call<QuizAnswer>, t: Throwable) {
                    Log.d("ChatRVAdapter", t.message.toString())
                }

            })
    }

    //퀴즈 정답 결과
    fun getQuizAnswer(roomId: Int){
        val retrofitService = getRetrofit().create(ChatRetrofitInterface::class.java)
        retrofitService.getQuizAnswer(roomId)
            .enqueue(object : Callback<QuizAnswerConfirm>{
                override fun onResponse(
                    call: Call<QuizAnswerConfirm>,
                    response: Response<QuizAnswerConfirm>
                ) {
                    var resp = response.body()!!
                    if(resp.isSuccess){
                        if(resp.result == 1)
                            Toast.makeText(applicationContext, "서로 통했습니다!", Toast.LENGTH_SHORT).show()
                        else
                            Toast.makeText(applicationContext, "상대방과의 키워드 매칭에 실패했습니다", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<QuizAnswerConfirm>, t: Throwable) {
                    Log.d("ChatRVAdapter", t.message.toString())
                }

            })
    }

    override fun onDestroy() {
        super.onDestroy()
        stompClient.disconnect()
    }

}