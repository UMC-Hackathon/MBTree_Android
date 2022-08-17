package com.umc.project.mbtree.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.umc.project.mbtree.data.User
import com.umc.project.mbtree.databinding.FragmentFriendBinding
import com.umc.project.mbtree.view.friend.FriendRVAdapter

class FriendFragment: Fragment() {

    lateinit var binding: FragmentFriendBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFriendBinding.inflate(inflater, container,false)

        //dummy data
        var datas = ArrayList<User>()
        datas.apply {
            add(User(1, "user1", "123", "ISFP"))
            add(User(2, "user2", "234", "ISFJ"))
            add(User(3, "user3", "345", "ESFP"))
        }

        //채팅리스트 어댑터 붙이기
        val fAdaper = FriendRVAdapter(datas)
        binding.rvFriendList.adapter = fAdaper
        binding.rvFriendList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        return binding.root
    }
}