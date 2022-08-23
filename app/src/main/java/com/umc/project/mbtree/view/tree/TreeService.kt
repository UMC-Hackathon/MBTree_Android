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

}