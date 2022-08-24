package com.umc.project.mbtree.view.LetterPaper

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.GridView
import androidx.recyclerview.widget.RecyclerView
import com.umc.project.mbtree.R
import com.umc.project.mbtree.data.LetterPaper
import com.umc.project.mbtree.data.User
import com.umc.project.mbtree.databinding.ItemLetterPaperBinding
import com.umc.project.mbtree.databinding.ItemListBinding
import com.umc.project.mbtree.view.Login.NicknameActivity
import com.umc.project.mbtree.view.friend.FriendRVAdapter
import kotlinx.android.synthetic.main.item_letter_paper.view.*
import kotlinx.android.synthetic.main.item_mbti.view.*

class LetterPaperGVAdapter(val context: Context?, private val letterPaperList:ArrayList<LetterPaper>) : BaseAdapter() {

    @SuppressLint("ViewHolder")

    // 포지션 값을 가지고 있으므로 여기서 onclick이벤트 작성
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
//        val mbti:String = letterPaperList[position]
        val inflater = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        val lpView : View = inflater.inflate(R.layout.item_letter_paper, null)
        lpView.ib_letter_paper.setImageResource(letterPaperList[position].letterPaperImg!!)
        lpView.tv_letter_paper_name.text = letterPaperList[position].paperName
        lpView.tv_letter_price.text = letterPaperList[position].price



        lpView.ib_letter_paper.setOnClickListener {
            val intent = Intent(context, LetterWriteFragment::class.java)
//            intent.putExtra("mbti", mbti)
            context!!.startActivity(intent)
        }
        return lpView
    }


    fun onBindViewHolder(holder:LetterPaperGVAdapter.ViewHolder, position: Int)
    {
        holder.bind(letterPaperList[position])
    }



    override fun getItem(position: Int): Any {
        return position
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return letterPaperList.size
    }



    inner class ViewHolder(val binding: ItemLetterPaperBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(lp: LetterPaper){
            //binding.ivListProfile
            binding.ibLetterPaper.setImageResource(lp.letterPaperImg!!)
            binding.tvLetterPaperName.text = lp.paperName
            binding.tvLetterPrice.text = lp.price
        }
    }

}