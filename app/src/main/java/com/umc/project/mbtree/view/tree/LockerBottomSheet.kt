package com.umc.project.mbtree.view.tree

import android.app.Activity
import android.app.Dialog
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.umc.project.mbtree.data.User
import com.umc.project.mbtree.databinding.LockerLayoutBinding

class LockerBottomSheet: BottomSheetDialogFragment() {

    lateinit var binding:LockerLayoutBinding
    var data = listOf("안읽음","안읽음","읽음")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?
    {
        //super.onCreateView(inflater, container, savedInstanceState)
        binding = LockerLayoutBinding.inflate(inflater, container, false)
        var datas = ArrayList<User>()
        datas.apply {
            add(User(1, "user1", "123", "ISFP"))
            add(User(2, "user2", "234", "ISFP"))
            add(User(3, "user3", "345", "ISFP"))
        }

        val adaper = LockerRVAdapter(datas)
        binding.lockerList.adapter = adaper
        binding.lockerList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.spinnerRead

        return binding.root


    }


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog: Dialog = super.onCreateDialog(savedInstanceState)
        dialog.setOnShowListener {
            val bottomSheetDialog = it as BottomSheetDialog
            // 다이얼로그 크기 설정 (인자값 : DialogInterface)
            setupRatio(bottomSheetDialog)
        }
        return dialog
    }

    private fun setupRatio(bottomSheetDialog: BottomSheetDialog) {

        //id = com.google.android.material.R.id.design_bottom_sheet for Material Components
        //id = android.support.design.R.id.design_bottom_sheet for support librares
        val bottomSheet = bottomSheetDialog.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet) as View
        val behavior = BottomSheetBehavior.from<View>(bottomSheet)
        val layoutParams = bottomSheet!!.layoutParams
        layoutParams.height = getBottomSheetDialogDefaultHeight()
        bottomSheet.layoutParams = layoutParams
        behavior.state = BottomSheetBehavior.STATE_EXPANDED
    }

    private fun getBottomSheetDialogDefaultHeight(): Int {
        return getWindowHeight() * 85 / 100
    }

    private fun getWindowHeight(): Int {
        // Calculate window height for fullscreen use
        val displayMetrics = DisplayMetrics()
        (context as Activity?)!!.windowManager.defaultDisplay.getMetrics(displayMetrics)
        return displayMetrics.heightPixels
    }
}