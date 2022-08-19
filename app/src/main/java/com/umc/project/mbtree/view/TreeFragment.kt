package com.umc.project.mbtree.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.umc.project.mbtree.databinding.FragmentTreeBinding
import com.umc.project.mbtree.view.tree.LockerBottomSheet

class TreeFragment: Fragment() {

    lateinit var binding: FragmentTreeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTreeBinding.inflate(inflater, container, false)

        //로직 작성

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.btnlocker.setOnClickListener{
            val bottomSheet = LockerBottomSheet()
            bottomSheet.show(parentFragmentManager, bottomSheet.tag)
        }
    }
}