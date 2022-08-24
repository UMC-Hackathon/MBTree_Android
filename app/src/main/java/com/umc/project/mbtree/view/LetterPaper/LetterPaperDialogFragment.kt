package com.umc.project.mbtree.view.LetterPaper

import android.app.Application
import android.content.Context
import android.graphics.Insets.add
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.fragment.app.DialogFragment
import com.umc.project.mbtree.R
import com.umc.project.mbtree.data.LetterPaper
import com.umc.project.mbtree.databinding.FragmentFriendTreeBinding
import com.umc.project.mbtree.databinding.FragmentLetterPaperDialogBinding

class LetterPaperDialogFragment: DialogFragment() {

    lateinit var binding: FragmentLetterPaperDialogBinding
    lateinit var fContext : Context

    override fun onAttach(context: Context) {
        super.onAttach(context)
        fContext = context
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLetterPaperDialogBinding.inflate(inflater, container, false)



        //dummy data
        var datas = ArrayList<LetterPaper>()
        datas.apply {
            add(LetterPaper(R.drawable.letter_morning,"그날의 아침", "Free",0))
            add(LetterPaper(R.drawable.letter_cliff,"밤 언덕", "50p" ,50))
            add(LetterPaper(R.drawable.letter_night,"사막의 밤", "Free",0))
            add(LetterPaper(R.drawable.letter_night,"꽃밭", "50P",50))
        }

//        //그리드뷰에 어댑터 붙이기
//        val fAdaper = FriendRVAdapter(datas)
//        binding.rvFriendList.adapter = fAdaper
//        binding.rvFriendList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
//
//        fAdaper.setMyItemClickListener(object: FriendRVAdapter.MyItemClickListener {
//            // 클릭되었을 때, FriendFragment로 이동하기
//            override fun onItemClick() {
//                (context as MainActivity). supportFragmentManager.beginTransaction()
//                    .replace(R.id.fl_main , FriendTreeFragment())
//                    .commitAllowingStateLoss()
//            }
//        })





        // Adapter 추가
        val letterPaperGVAdapter = LetterPaperGVAdapter(context, datas)
        binding.gvLetterPaper.adapter = letterPaperGVAdapter



        return binding.root
    }

}