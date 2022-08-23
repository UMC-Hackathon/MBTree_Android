package com.umc.project.mbtree.view.tree


import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime


data class contentResponse(
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String,
    @SerializedName("result") val messageResult: Message
)

data class Message
    (
    @SerializedName("id") val id:Int,
    @SerializedName("content") val content: String,
    @SerializedName("createDate")val createDate: String,
    @SerializedName("isRead")val isRead: Int,
    @SerializedName("paperStyle") val paperStyle: Int,
    @SerializedName("writerId")val writerId: WriterId,
    @SerializedName("treeId")val treeId: treeId,
)



