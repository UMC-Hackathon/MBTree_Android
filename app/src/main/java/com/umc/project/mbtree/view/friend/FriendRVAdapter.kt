package com.umc.project.mbtree.view.friend

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.umc.project.mbtree.data.User
import com.umc.project.mbtree.databinding.ItemListBinding
import com.umc.project.mbtree.view.chat.ChatListRVAdapter

class FriendRVAdapter (private val chatList:ArrayList<User>): RecyclerView.Adapter<FriendRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemListBinding = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(chatList[position])
    }

    override fun getItemCount(): Int {
        return chatList.size
    }

    inner class ViewHolder(val binding: ItemListBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(u: User){
            //binding.ivListProfile
            binding.tvListName.text = u.name
            binding.tvListMbti.text = u.mbti
        }
    }

}