
package com.umc.project.mbtree.view.Login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.umc.project.mbtree.databinding.ActivityNicknameBinding
import kotlinx.android.synthetic.main.activity_nickname.*


class NicknameActivity : AppCompatActivity() {

    lateinit var binding: ActivityNicknameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNicknameBinding.inflate(layoutInflater)
        setContentView(binding.root)


        bt_start.setOnClickListener({
            val intent = Intent(this, LoadingActivity::class.java)
            startActivity(intent)
        })
    }
}


