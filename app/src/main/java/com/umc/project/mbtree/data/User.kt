package com.umc.project.mbtree.data

data class User(
    val id: Int,
    var uuid: String?,
    var name: String,
    var email: String,
    var mbti: String,
    var location: String,
    var token: String,
    var point: Int,
    var createDate: String?
)
