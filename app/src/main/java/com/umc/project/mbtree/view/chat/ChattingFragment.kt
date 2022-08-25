package com.umc.project.mbtree.view

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.umc.project.mbtree.R
import com.umc.project.mbtree.data.Chat
import com.umc.project.mbtree.data.ChatList
import com.umc.project.mbtree.data.MatchResponse
import com.umc.project.mbtree.data.User
import com.umc.project.mbtree.databinding.FragmentChattingBinding
import com.umc.project.mbtree.remote.ChatRetrofitInterface
import com.umc.project.mbtree.remote.getRetrofit
import com.umc.project.mbtree.view.chat.ChatListRVAdapter
import com.umc.project.mbtree.view.chat.ChattingActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChattingFragment: Fragment() {

    lateinit var binding: FragmentChattingBinding
    private val serverUrl = "https://mbtree.site"
    private val userId = 3
    var datas = ArrayList<User>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChattingBinding.inflate(inflater, container, false)

        getChatList(userId)

        //매칭시작 버튼클릭
        binding.btnChatListStart.setOnClickListener{
//            Toast.makeText(context, "매칭시작", Toast.LENGTH_SHORT).show()
            binding.btnChatListStart.setBackgroundColor(resources.getColor(R.color.white))
            binding.btnChatListStart.setTextColor(resources.getColor(R.color.main_color))
            binding.btnChatListStart.text = "매칭 중입니다..."

            matchingStart(userId)
        }

        return binding.root
    }

    private fun getChatList(userId: Int){
        val retrofitService = getRetrofit().create(ChatRetrofitInterface::class.java)
        retrofitService.getChatListById(userId)
            .enqueue(object : Callback<ChatList>{
                override fun onResponse(call: Call<ChatList>, response: Response<ChatList>) {
                    var cList = response.body()!!

                    if(cList.isSuccess){
                        Log.d("CHATLIST", "isSuccess:" + cList.isSuccess)
                        Log.d("CHATLIST", "code:" + cList.code)
                        Log.d("CHATLIST", "message:" + cList.message)

                        for(c in cList.result){
                            var u1 = c.user1
                            var u2 = c.user2

                            if(u1 != null && u2 != null) {
                                Log.d("CHATLIST", "uuid: " + u1?.uuid)
                                datas.add(User(1, u1.uuid, u1.name, u1.email, u1.mbti, u1.location, u1.token, u1.point, u1.createDate))
//                                datas.add(User(2, u2.uuid, u2.name, u2.email, u2.mbti, u2.location, u2.token, u2.point, u2.createDate))

                                //채팅리스트 어댑터 붙이기
                                val adaper = ChatListRVAdapter(datas)
                                binding.rvChatList.adapter = adaper
                                binding.rvChatList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                                Log.d("CHATLIST", "Adatper붙이기 완료")
                            }
                        }
                    }

                }

                override fun onFailure(call: Call<ChatList>, t: Throwable) {
                    Log.d("CHATLIST", t.message.toString())
                }

            })
    }

    private fun matchingStart(userId: Int){
        val retrofitService = getRetrofit().create(ChatRetrofitInterface::class.java)
        retrofitService.matchingChat(userId)
            .enqueue(object : Callback<MatchResponse> {
                override fun onResponse(call: Call<MatchResponse>, response: Response<MatchResponse>) {
                    var resp = response.body()!!

                    if(resp.responseResult == "SUCCESS"){
                        Log.d("CHAT MATCH", "responseResult: " + resp.responseResult)
                        Log.d("CHAT MATCH", "chatRoomId: " + resp.chatRoomId)

                        changeButton()

                        val intent = Intent(activity, ChattingActivity::class.java)
                        intent.putExtra("roomId", resp.chatRoomId)
                        Log.d("CHAT LIST", "매칭된 roomId: " + resp.chatRoomId)
                        startActivity(intent)
                    }
                    else{
                        Log.d("CHAT MATCH", "responseResult: " + resp.responseResult)
                        changeButton()
                    }
                }

                override fun onFailure(call: Call<MatchResponse>, t: Throwable) {
                    Log.d("CHAT MATCH", t.message.toString())
                }

            })
    }

    private fun changeButton()  {
        binding.btnChatListStart.setBackgroundResource((R.drawable.bg_textview))
        binding.btnChatListStart.setTextColor(resources.getColor(R.color.white))
        binding.btnChatListStart.text = "매칭 시작"
    }
}