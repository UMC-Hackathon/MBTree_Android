package com.umc.project.mbtree.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.umc.project.mbtree.databinding.FragmentChattingBinding

class ChattingFragment: Fragment() {

    lateinit var binding: FragmentChattingBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChattingBinding.inflate(inflater, container, false)

        //로직 작성

        return binding.root
    }
}