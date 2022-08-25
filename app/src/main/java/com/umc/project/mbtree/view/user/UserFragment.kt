package com.umc.project.mbtree.view.user

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.umc.project.mbtree.databinding.ActivityMainBinding.inflate
import com.umc.project.mbtree.databinding.FragmentFriendBinding
import com.umc.project.mbtree.databinding.FragmentMyletterBinding
import com.umc.project.mbtree.databinding.LockerLayoutBinding
import com.umc.project.mbtree.view.tree.RetrofitObject
import com.umc.project.mbtree.view.tree.contentResponse
import com.umc.project.mbtree.view.tree.contentService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserFragment : Fragment() {

    lateinit var binding:FragmentMyletterBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?
    {

        binding = FragmentMyletterBinding.inflate(inflater, container,false)
        getMessageId()
       // binding.letterContent.setText(.content)

        return binding.root
    }
    private fun getMessageId() {
        Log.v("eun test", "TreeFragment들어옴")
        val retrofitService = RetrofitObject.getRetrofit().create(contentService::class.java)

        Log.v("eun test", "retrofitService" + retrofitService.getMessageId(1))
        Log.v("eun test", "TreeFragment들어옴 1")
        retrofitService.getMessageId(1).enqueue(object : Callback<contentResponse> {

            override fun onResponse(call: Call<contentResponse>, response: Response<contentResponse>) {
                Log.e("eun test", "TreeFragment들어옴2")
               var rLists = response.body()!!
                Log.e("eun test", "TreeFragment들어옴 3"+rLists)
                Log.d("mbtree test", "isSuccess: " + rLists.isSuccess)
                Log.e("mbtree test", "code: " + rLists.code)
                Log.v("mbtree test", "message: " + rLists.message)

                //Expected BEGIN_OBJECT but was STRING
                Log.v("eun test", "은영" + rLists.messageResult)


            }

            override fun onFailure(call: Call<contentResponse>, t: Throwable) {

                Log.e("eun test", "TreeFragment오류남@@@@@@@@@@")
                Log.d("mbtree test", t.message.toString())
            }

        })

    }
}