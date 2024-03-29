package com.umc.project.mbtree.view.Login

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.umc.project.mbtree.R
import com.umc.project.mbtree.view.MainActivity

class LoadingActivity : AppCompatActivity() {


    // This is the loading time of the splash screen
    private val SPLASH_TIME_OUT:Long = 2000 // 1 sec
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loading)


        Handler().postDelayed({
            // This method will be executed once the timer is over
            // Start your app main activity

            startActivity(Intent(this, MainActivity::class.java))

            // close this activity
            finish()
        }, SPLASH_TIME_OUT)
    }

}