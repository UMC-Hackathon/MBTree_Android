package com.umc.project.mbtree.view.user


import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime
import java.util.UUID


data class TreeResponse (
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String,
    @SerializedName("result") val result: User
)
data class User
    (
    @SerializedName("id") val id:Int,
    @SerializedName("content") val uuid: String,
)