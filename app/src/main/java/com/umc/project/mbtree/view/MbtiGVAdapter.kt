package com.umc.project.mbtree.view

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.umc.project.mbtree.R
import kotlinx.android.synthetic.main.item_mbti.view.*

class MbtiGVAdapter(val context: Context?, val mbti_list: Array<String>) : BaseAdapter() {

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val mbti:String = mbti_list[position]
        val inflater = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val mView : View = inflater.inflate(R.layout.item_mbti, null)
        //mView.imageView.setBackgroundResource(food.image!!)
        //foodView.textView.text = food.name!!
        mView.bt_mbti.text = mbti!!

        mView.bt_mbti.setOnClickListener {
            val intent = Intent(context, NicknameActivity::class.java)
            intent.putExtra("mbti", mbti)
            context!!.startActivity(intent)
        }
        return mView
    }

    override fun getItem(position: Int): Any {
        return position
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return mbti_list.size
    }

}