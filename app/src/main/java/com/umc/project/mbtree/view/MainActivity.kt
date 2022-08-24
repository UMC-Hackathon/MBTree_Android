package com.umc.project.mbtree.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import com.umc.project.mbtree.R
import com.umc.project.mbtree.databinding.ActivityMainBinding
import com.umc.project.mbtree.databinding.FragmentTreeBinding
import kotlinx.android.synthetic.main.fragment_tree.*
import kotlinx.android.synthetic.main.fragment_tree.view.*

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var tab_tree:TreeFragment
    lateinit var tab_friend:FriendFragment
    lateinit var tab_chat:ChattingFragment
    var lx : Float = 0.0f
    var ly : Float = 0.0f
    var lx2 : Float = 0.0f
    var ly2 : Float = 0.0f

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
                    0 -> { replaceView(tab_tree)
                        lx=tab_tree.letterView1.x
                        ly=tab_tree.letterView1.y


                        Log.v("eun test1 ", "loc    " + tab_tree.letterView1.getX() + "  " + lx)

                        lx2= tab_tree.letterView2.x
                        ly2= tab_tree.letterView2.y

                        Log.v("eun test1 ", "loc    " + lx + ly)

                        Log.v("eun test1 ", "loc    " + lx2 + ly2)


                        var letter: ImageView = ImageView(this@MainActivity)
                        Log.v("eun test1 ", "loc    " + letter)

                        letter.setImageResource(R.drawable.ic_letter)
                        letter.maxHeight =20
                        letter.maxWidth = 20

                        letter.x = lx2
                        letter.y = ly2

                        binding.flMain.addView(letter)

                        Log.v("eun test2", "loc    " + letter.x +  letter.y)
                    }
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