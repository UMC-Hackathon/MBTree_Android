package com.umc.project.mbtree.view.friend

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.umc.project.mbtree.R
import com.umc.project.mbtree.data.User
import com.umc.project.mbtree.databinding.FragmentFriendBinding
import com.umc.project.mbtree.view.LetterPaper.FriendTreeFragment
import com.umc.project.mbtree.view.MainActivity

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
            add(User(1, "22d31", "user1", "test@naver.com", "isfp", "temp", "ase2", 0, null))
            add(User(2, "22d31", "user2", "test@naver.com", "isfp", "temp", "ase2", 0, null))
            add(User(3, "22d31", "user3", "test@naver.com", "isfp", "temp", "ase2", 0, null))
        }


        //채팅리스트 어댑터 붙이기
        val fAdaper = FriendRVAdapter(datas)
        binding.rvFriendList.adapter = fAdaper
        binding.rvFriendList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        fAdaper.setMyItemClickListener(object: FriendRVAdapter.MyItemClickListener{
            // 클릭되었을 때, FriendFragment로 이동하기
            override fun onItemClick() {
                (context as MainActivity). supportFragmentManager.beginTransaction()
                    .replace(R.id.fl_main , FriendTreeFragment())
                    .commitAllowingStateLoss()
            }
        })


        return binding.root
    }
}