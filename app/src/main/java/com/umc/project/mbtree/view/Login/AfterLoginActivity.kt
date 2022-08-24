package com.umc.project.mbtree.view.Login

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.kakao.sdk.talk.TalkApiClient
import com.kakao.sdk.user.UserApiClient
import com.umc.project.mbtree.R
import com.umc.project.mbtree.databinding.ActivityAfterLoginBinding
import com.umc.project.mbtree.view.MainActivity

class AfterLoginActivity : AppCompatActivity() {

    lateinit var binding: ActivityAfterLoginBinding
//    private var kakaoFriendsDatas = ArrayList<KaKaoFriends>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAfterLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // ** 카카오톡 닉네임, 유저 프로필 사진 가져오기
        val profileNickname = findViewById<TextView>(R.id.kakao_me_profile_nickname_tv) // 닉네임
        val profileImage = findViewById<ImageView>(R.id.kakao_me_profile_image_iv) // 프로필 이미지뷰

        UserApiClient.instance.me { user, error ->
            profileNickname.text = "닉네임: ${user?.kakaoAccount?.profile?.nickname}"

            // 유저 프로필 사진 url 받아오기
            val profileImageUrl = user?.kakaoAccount?.profile?.thumbnailImageUrl

            Glide.with(this)
                .load(profileImageUrl) // 불러올 이미지 url
                .fallback(R.drawable.ic_my) // 로드할 url 이 비어있을(null 등) 경우 표시할 이미지
                .into(profileImage) // 이미지를 넣을 뷰
        }


        // ** 카카오톡 친구 목록 가져오기 (기본)

//        // 데이터 test
//        kakaoFriendsDatas.apply{
//            add(kakaoFriends())
//        }

//        val FriendsAdapter = KaKaoFriendsRVAdapter(this)
//        binding.kakaoFriendsRv.adapter = FriendsAdapter

        val kakao_friends_button = findViewById<Button>(R.id.kakao_friends_button) // 로그인 버튼

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





        val kakao_logout_button = findViewById<Button>(R.id.kakao_logout_button) // 로그인 버튼

        kakao_logout_button.setOnClickListener {
            UserApiClient.instance.logout { error ->
                if (error != null) {
                    Toast.makeText(this, "로그아웃 실패 $error", Toast.LENGTH_SHORT).show()
                }else {
                    Toast.makeText(this, "로그아웃 성공", Toast.LENGTH_SHORT).show()
                }
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent.addFlags(FLAG_ACTIVITY_CLEAR_TOP))
                finish()
            }
        }

        val kakao_unlink_button = findViewById<Button>(R.id.kakao_unlink_button) // 회원탈퇴 버튼

        kakao_unlink_button.setOnClickListener {
            UserApiClient.instance.unlink { error ->
                if (error != null) {
                    Toast.makeText(this, "회원 탈퇴 실패 $error", Toast.LENGTH_SHORT).show()
                }else {
                    Toast.makeText(this, "회원 탈퇴 성공", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent.addFlags(FLAG_ACTIVITY_CLEAR_TOP))
                    finish()
                }
            }
        }
    }

}
