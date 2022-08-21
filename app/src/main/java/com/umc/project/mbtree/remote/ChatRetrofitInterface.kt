package com.umc.project.mbtree.remote

import com.umc.project.mbtree.data.ChatContent
import com.umc.project.mbtree.data.ChatList
import com.umc.project.mbtree.data.ChatResponse
import com.umc.project.mbtree.data.Quiz
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ChatRetrofitInterface {

    //랜덤채팅 매칭시작
    @POST("chat/join")
    fun matchingChat(@Query("userId")userId: Int): Call<ChatResponse>

    //채팅 리스트 출력
    @GET("chat/list")
    fun getChatListById(@Query("userId")userId: Int): Call<ChatList>

    //채팅방 내용 불러오기
    @GET("chat/list")
    fun getChatContent(
        @Query("userId")userId: Int,
        @Query("roomId")roomId:Int
    ): Call<ChatContent>

    //퀴즈 가져오기
    @GET("chat/quiz")
    fun getQuiz(@Query("roomId")roomId: Int): Call<Quiz>

}