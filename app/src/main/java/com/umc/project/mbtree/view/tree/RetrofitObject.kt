package com.umc.project.mbtree.view.tree

import android.util.Log
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://mbtree.site/"
object RetrofitObject{
    private var retrofit: Retrofit? = null
    var gson = GsonBuilder().setLenient().create()

    fun getRetrofit() : Retrofit {

        val okHttpClient: OkHttpClient by lazy {
            OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
                .build()
        }
        if(retrofit ==null){
         retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            //.client(okHttpClient)
            .build()}
        Log.v("eun test", "retrofit오브젝트 들어옴" )
        Log.v("eun test", "retrofit " +  retrofit )
        return retrofit!!
    }

}




