package com.umc.project.mbtree.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import com.umc.project.mbtree.R
import com.umc.project.mbtree.databinding.ActivityMainBinding
import com.umc.project.mbtree.view.chat.ChattingFragment
import com.umc.project.mbtree.view.friend.FriendFragment

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var tab_tree:TreeFragment
    lateinit var tab_friend: FriendFragment
    lateinit var tab_chat: ChattingFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //프래그먼트 객체화
        tab_tree = TreeFragment()
        tab_friend = FriendFragment()
        tab_chat = ChattingFragment()

        supportFragmentManager.beginTransaction().add(R.id.fl_main, tab_tree).commit()

        binding.tabMain.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when(tab?.position){
                    0 -> { replaceView(tab_tree) }
                    1 -> { replaceView(tab_friend) }
                    2 -> { replaceView(tab_chat) }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                //tab의 상태가 선택 상태에서 선택되지 않음으로 변경됨
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                //이미 선택된 상태의 tab이 사용자에 의해 다시 선택됨
            }

        })

        //마이페이지 이동 클릭리스너너
       binding.ibMainMypage.setOnClickListener{
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }
    }

    private fun replaceView(tab: Fragment){
        var selectedFragment:Fragment? = null
        selectedFragment = tab
        selectedFragment?.let{
            supportFragmentManager.beginTransaction()
                .replace(R.id.fl_main, it).commit()
        }
    }
}