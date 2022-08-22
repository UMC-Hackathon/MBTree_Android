package com.umc.project.mbtree.view

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface contentService {
    @GET("message")
    fun getMessageId(

        //@Query("isSuccess") isSuccess: Boolean,
        //@Query("code") code: Int,
        //@Query("message") message: String,
        @Query("messageId") messageId: Int,

        ) : Call<contentResponse>

}