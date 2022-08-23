package com.umc.project.mbtree.view.tree

//import android.telecom.Call
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TreeService {

        @GET("tree")
        fun getTreeId(

            //@Query("isSuccess") isSuccess: Boolean,
            //@Query("code") code: Int,
            //@Query("message") message: String,
            @Query("treeId") id: Int,

        ) : Call<TreeResponse>

    @GET("tree/storage")
    fun getUnreadMsg(

        @Query("treeId") id: Int,
        @Query("isRead") isRead: Int, //안읽은거라 0을 보내줘야함

        ) : Call<TreeResponse>

    @GET("tree/storage")
    fun getReadMsg(

        @Query("treeId") id: Int,
        @Query("isRead") isRead: Int, //읽은거라 1을 보내줘야함

    ) : Call<TreeResponse>

}