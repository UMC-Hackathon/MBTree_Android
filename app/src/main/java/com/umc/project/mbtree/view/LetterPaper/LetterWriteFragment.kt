package com.umc.project.mbtree.view.LetterPaper

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.umc.project.mbtree.databinding.FragmentLetterWriteBinding

class LetterWriteFragment : Fragment(){

    lateinit var binding: FragmentLetterWriteBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?
    {

        binding = FragmentLetterWriteBinding.inflate(inflater, container,false)


        return binding.root
    }

}

