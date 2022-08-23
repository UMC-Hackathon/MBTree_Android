package com.umc.project.mbtree.view.tree


import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime


data class TreeResponse (
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String,
    @SerializedName("result") val result: TreeResult
)
data class TreeResult(
    @SerializedName("tree") val tree: List<Result>
    )
data class Result
    (
    @SerializedName("id") val id:Int,
    @SerializedName("content") val content: String,
    @SerializedName("createDate")val createDate: LocalDateTime,
    @SerializedName("isRead")val isRead: Int,
    @SerializedName("paperStyle") val paperStyle: Int,
    @SerializedName("writerId")val writerId: WriterIdResult,
    @SerializedName("treeId")val treeId: TreeIdResult,
)

data class TreeIdResult(
    @SerializedName("treeIdResult") val treeIdresult: List<treeId>
)
data class treeId(
    @SerializedName("id") val id:Int,
    @SerializedName("uuid") val uuid: String,
    @SerializedName("name")val name: String,
    @SerializedName("email")val email: String,
    @SerializedName("mbti")val mbti: String,
    @SerializedName("location")val location: String,
    @SerializedName("token")val token: String,
    @SerializedName("point")val point: Int,
    @SerializedName("createDate")val createDate: LocalDateTime,

    )
data class WriterIdResult(
    @SerializedName("writerIdResult") val writerIdresult: List<WriterId>
)
data class WriterId (
    @SerializedName("id") val id:Int,
    @SerializedName("uuid") val uuid: String,
    @SerializedName("name")val name: String,
    @SerializedName("email")val email: String,
    @SerializedName("mbti")val mbti: String,
    @SerializedName("location")val location: String,
    @SerializedName("token")val token: String,
    @SerializedName("point")val point: Int,
    @SerializedName("createDate")val createDate: LocalDateTime,
)


