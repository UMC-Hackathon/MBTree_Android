package com.umc.project.mbtree.view.friend

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.kakao.sdk.talk.TalkApiClient
import com.umc.project.mbtree.R
import com.umc.project.mbtree.data.User
import com.umc.project.mbtree.databinding.FragmentFriendBinding
import com.umc.project.mbtree.view.LetterPaper.FriendTreeFragment
import com.umc.project.mbtree.view.MainActivity
import kotlinx.android.synthetic.main.activity_after_login.*

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

        kakao_friends_button.setOnClickListener {
            TalkApiClient.instance.friends { friends, error ->
                if (error != null) {
                    Log.e("실패", "카카오톡 친구 목록 가져오기 실패", error)
                } else if (friends != null) {
                    Log.i("성공", "카카오톡 친구 목록 가져오기 성공 \n${friends.elements?.joinToString("\n")}")

                    // 친구의 UUID 로 메시지 보내기 가능
                }
            }
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