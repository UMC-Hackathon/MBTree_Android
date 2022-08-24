package com.umc.project.mbtree.view.tree

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.umc.project.mbtree.data.User
import com.umc.project.mbtree.databinding.ItemListBinding
import com.umc.project.mbtree.view.ProfileActivity

class LockerRVAdapter (private val lokerList:ArrayList<User>): RecyclerView.Adapter<LockerRVAdapter.ViewHolder>() {
    interface OnItemClickListener{
        fun onItemClick(v: View, data: User, pos : Int)
    }
    private var listener : OnItemClickListener? = null
    fun setOnItemClickListener(listener : OnItemClickListener) {
        this.listener = listener
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemListBinding = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(lokerList[position])
    }

    override fun getItemCount(): Int {
        return lokerList.size
    }

    inner class ViewHolder(val binding: ItemListBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(u: User){
            //binding.ivListProfile
            binding.tvListName.text = u.name
            binding.tvListMbti.text = u.mbti
            val pos = adapterPosition
            if(pos!= RecyclerView.NO_POSITION)
            {
                binding.cvListImage.setOnClickListener {

                }
            }
        }
    }

}