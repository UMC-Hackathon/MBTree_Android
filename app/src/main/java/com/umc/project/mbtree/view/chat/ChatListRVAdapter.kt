package com.umc.project.mbtree.view.chat

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.umc.project.mbtree.data.User
import com.umc.project.mbtree.databinding.ItemListBinding

class ChatListRVAdapter(private val chatList:ArrayList<User>):RecyclerView.Adapter<ChatListRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding:ItemListBinding = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(chatList[position])
    }

    override fun getItemCount(): Int {
        return chatList.size
    }

    inner class ViewHolder(val binding: ItemListBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(u: User){
            //binding.ivListProfile
            binding.tvListName.text = u.name
            binding.tvListMbti.text = u.mbti

//            itemView.setOnLongClickListener{
//                //롱클릭 시 삭제
//            }
        }
    }
}