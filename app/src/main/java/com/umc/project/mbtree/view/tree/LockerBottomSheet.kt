package com.umc.project.mbtree.view

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils.replace
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.umc.project.mbtree.R
import com.umc.project.mbtree.data.User
import com.umc.project.mbtree.databinding.FragmentMyletterBinding
import com.umc.project.mbtree.databinding.LockerLayoutBinding
import com.umc.project.mbtree.view.tree.LockerRVAdapter
import com.umc.project.mbtree.view.tree.MyletterFragment

class Lockerbottomsheet : BottomSheetDialogFragment()
{
    lateinit var letter:MyletterFragment

    lateinit var binding:LockerLayoutBinding
    private lateinit var fContext: Context


    override fun onAttach(context: Context) {
        super.onAttach(context)
        fContext=context
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?
    {
        letter = MyletterFragment()

        //super.onCreateView(inflater, container, savedInstanceState)
        binding = LockerLayoutBinding.inflate(inflater, container, false)
        var datas = ArrayList<User>()
        datas.apply {
            add(User(1, "user1", "123","istp"))
            add(User(2, "user2", "234","istp"))
            add(User(3, "user3", "345","istp"))
        }
        var datas2 = ArrayList<User>()
        datas2.apply {
            add(User(1, "user3", "111" , "istp"))
            add(User(2, "user2", "22","istp"))
            add(User(3, "user1", "345","istp"))
        }



        var data = listOf("안읽음", "읽음")

        val adpt = ArrayAdapter( fContext , android.R.layout.simple_list_item_1, data)
        with(binding)
        {
            binding.spinnerRead.adapter = adpt
            binding.spinnerRead.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onNothingSelected(parent: AdapterView<*>?) {}
                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                        when(position)
                        {

                            0-> {
                                val transaction = parentFragmentManager.beginTransaction()
                                val adaper = LockerRVAdapter(datas)
                                binding.lockerList.adapter = adaper
                                binding.lockerList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                                adaper.setOnItemClickListener(object :LockerRVAdapter .OnItemClickListener{
                                    override fun onItemClick(v: View, data: User, pos : Int) {
                                        transaction.replace(R.id.fl_main, MyletterFragment())
                                        transaction.addToBackStack(null)
                                        transaction.commit()
                                    }

                                })

                            }
                            1-> {
                                val adaper2 = LockerRVAdapter(datas2)
                                binding.lockerList.adapter = adaper2
                                binding.lockerList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

                            }
                        }

                    }
                }
        }



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
