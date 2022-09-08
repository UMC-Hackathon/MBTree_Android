package com.umc.project.mbtree.data

data class MatchResponse (
    val responseResult: String,
    val chatRoomId: Int,
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
    val isSuccess: Boolean,
    val code: Int,
    val message: String,
    val result: QuizResult
)

data class QuizResult(
    val id: Int,
    val quiz: String,
    val keyword: String,
    val answer1: String,
    val answer2: String
)

data class QuizAnswer(
    val isSuccess: Boolean,
    val code: Int,
    val message: String,
    val result: QuizAnswerResult?
){
    data class QuizAnswerResult(
        val id:Int,
        val user1: String,
        val user2: String,
        val quiz: String,
        val quilt: String,
        val answer1: Int,
        val answer2: Int
    )
}

data class QuizAnswerConfirm(
    val isSuccess: Boolean,
    val code: Int,
    val message: String,
    val result: Int
)