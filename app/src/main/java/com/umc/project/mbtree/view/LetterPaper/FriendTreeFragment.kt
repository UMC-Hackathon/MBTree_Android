package com.umc.project.mbtree.view.LetterPaper

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.umc.project.mbtree.R
import com.umc.project.mbtree.databinding.FragmentFriendTreeBinding
import kotlinx.android.synthetic.main.activity_select_mbti.*

class FriendTreeFragment: Fragment() {

    lateinit var binding: FragmentFriendTreeBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFriendTreeBinding.inflate(inflater, container, false)

        val transaction = parentFragmentManager.beginTransaction()
        binding.btWrite.setOnClickListener {
            transaction.replace(R.id.fl_main, LetterPaperDialogFragment())
            transaction.addToBackStack(null)
            transaction.commit()

            // 시도1
//            val intent = Intent(this, LetterPaperActivity::class.java)
//            startActivity(intent)

            // 시도2
//            val intent  = Intent(getActivity(),LetterPaperActivity::class.java); //fragment라서 activity intent와는 다른 방식
//            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
//            startActivity(intent);


            // 시도3
//            val intent = Intent(context, LetterPaperActivity::class.java)
//            startActivity(intent)


        }

//        binding.btWrite.setOnClickListener {
//        //Dialog 만들기
//        val mDialogView = LayoutInflater.from(this).inflate(R.layout.fragment_letter_paper_dialog,null)
//            val mBuilder = AlertDialog.Builder(this)
//                .setView(mDialogView)
//                .setTitle("letterPaper")
//
//            val  mAlertDialog = mBuilder.show()
//        }



            return binding.root
        }
    }