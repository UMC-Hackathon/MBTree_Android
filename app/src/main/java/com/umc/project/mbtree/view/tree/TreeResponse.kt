package com.umc.project.mbtree.view.tree


import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime


data class TreeResponse (
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String,
    @SerializedName("result") val result: List<Result>
)
data class Result
    (
    @SerializedName("id") val id:Int,
    @SerializedName("content") val content: String,
    @SerializedName("createDate")val createDate: String,
    @SerializedName("isRead")val isRead: Int,
    @SerializedName("paperStyle") val paperStyle: Int,
    @SerializedName("writerId")val writerId: WriterId,
    @SerializedName("treeId")val treeId: TreeId,
)
/*
data class TreeIdResult(
    @SerializedName("treeIdResult") val treeIdresult: List<treeId>
)*/
data class TreeId(
    @SerializedName("id") val id:Int,
    @SerializedName("uuid") val uuid: String,
    @SerializedName("name")val name: String,
    @SerializedName("email")val email: String,
    @SerializedName("mbti")val mbti: String,
    @SerializedName("location")val location: String,
    @SerializedName("token")val token: String,
    @SerializedName("point")val point: Int,
   // @SerializedName("createDate")val createDate: String,

    )
/*
data class WriterIdResult(
    @SerializedName("writerIdResult") val writerIdresult: List<WriterId>
)*/
data class WriterId (
    @SerializedName("id") val id:Int,
    @SerializedName("uuid") val uuid: String,
    @SerializedName("name")val name: String,
    @SerializedName("email")val email: String,
    @SerializedName("mbti")val mbti: String,
    @SerializedName("location")val location: String,
    @SerializedName("token")val token: String,
    @SerializedName("point")val point: Int,
    //@SerializedName("createDate")val createDate: String,
)


