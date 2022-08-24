package com.umc.project.mbtree.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.umc.project.mbtree.R
import com.umc.project.mbtree.databinding.FragmentTreeBinding
import com.umc.project.mbtree.view.tree.LockerBottomSheet
import com.umc.project.mbtree.view.tree.MyletterFragment
import com.umc.project.mbtree.view.tree.RetrofitObject.getRetrofit
import com.umc.project.mbtree.view.tree.TreeResponse
import com.umc.project.mbtree.view.tree.TreeService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


//import java.util.Arrays.toString
//import java.util.Objects.toString


class TreeFragment: Fragment() {

    lateinit var binding: FragmentTreeBinding
    private lateinit var fContext: Context


    override fun onAttach(context: Context) {
        super.onAttach(context)
        fContext=context
    }

    override fun onCreateView(

        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.v("eun test", "getTreeId() 실행  전" )
        getTreeId()
        Log.v("eun test", "getTreeId() 실행 후" )
        binding = FragmentTreeBinding.inflate(inflater, container, false)
        val transaction = parentFragmentManager.beginTransaction()
        binding.icLetter.setOnClickListener {

            transaction.replace(R.id.fl_main, MyletterFragment())
            transaction.addToBackStack(null)
            transaction.commit()

        }
        return binding.root
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.btnlocker.setOnClickListener {
            val bottomSheet = LockerBottomSheet()
            bottomSheet.show(parentFragmentManager, bottomSheet.tag)
        }

    }
    private fun getTreeId(){
        Log.v("eun test", "TreeFragment들어옴" )
        val retrofitService = getRetrofit().create(TreeService::class.java)

        Log.v("eun test", "retrofitService" +retrofitService.getTreeId(2))
        Log.v("eun test", "TreeFragment들어옴 1" )
        retrofitService.getTreeId(2).enqueue(object : Callback<TreeResponse>{

            override fun onResponse(call: Call<TreeResponse>, response: Response<TreeResponse>) {
                Log.e("eun test", "TreeFragment들어옴2" )
                Toast.makeText(fContext, "success", Toast.LENGTH_SHORT).show()
                var rLists = response.body()!!
                Log.e("eun test", "TreeFragment들어옴 3" )
                Log.d("mbtree test", "isSuccess: " + rLists.isSuccess)
                Log.e("mbtree test", "code: " + rLists.code)
                Log.v("mbtree test", "message: " + rLists.message)
                Log.v( "eun test", "은영"+ rLists.result)

                 for(r in rLists.result){
                       Log.d("mbtree test", "message id: " + r.id)
                       Log.d("mbtree test", "content: " + r.content)

                       var writer = r.writerId
                       Log.d("mbtree test", "writerid: " + writer.id)
                       Log.d("mbtree test", "name: " + writer.name)
                       Log.d("mbtree test", "email: " + writer.email)

                       var user = r.treeId
                       Log.d("mbtree test", "tree id: " + user.id)
                       Log.d("mbtree test", "name: " + user.name)
                       Log.d("mbtree test", "email: " + user.email)

                   }
            }
            override fun onFailure(call: Call<TreeResponse>, t: Throwable) {

                Log.e("eun test", "TreeFragment오류남@@@@@@@@@@" )
                Log.d("mbtree test", t.message.toString())
            }

        })
    }




}

