package com.umc.project.mbtree.data

data class ChatResponse (
    val responseResult: String,
    val chatRoodId: Int,
    val userId: Int
)

data class ChatList(
    val isSuccess: Boolean,
    val code: Int,
    val message: String,
    val result: ArrayList<ChatListResult>
)

data class ChatListResult(
    val id: Int,
    val user1: User?,
    val user2: User?,
    val quit: Int
)

data class ChatContent(
    val isSuccess: Boolean,
    val code: Int,
    val message: String
)

data class Quiz(
    val id: Int,
    val quiz: String,
    val keyword: String,
    val answer1: String,
    val answer2: String
)