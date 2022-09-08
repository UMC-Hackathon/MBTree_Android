package com.umc.project.mbtree.view.friend

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.umc.project.mbtree.data.User
import com.umc.project.mbtree.databinding.ItemListBinding
import com.umc.project.mbtree.view.chat.ChatListRVAdapter

class FriendRVAdapter (private val friendList:ArrayList<User>): RecyclerView.Adapter<FriendRVAdapter.ViewHolder>() {

    interface MyItemClickListener{
        // 아이템이 클릭되었을 때
        fun onItemClick()
    }

    // 외부에서 클릭 이벤트를 사용하기 위해서 외부에서 리스너 객체를 넘겨줘야 함
    // 외부에서 전달받은 함수와 전달받은 리스너 객체를 어댑터에서 사용할 수 있도록 따로 저장할 변수
    private lateinit var mItemClickListener: MyItemClickListener
    fun setMyItemClickListener(itemClickListener: MyItemClickListener)
    {
        mItemClickListener = itemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemListBinding = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    // 포지션 값을 가지고 있으므로 여기서 onclick이벤트 작성
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(friendList[position])
        holder.itemView.setOnClickListener{ mItemClickListener.onItemClick() }

    }

    override fun getItemCount(): Int {
        return friendList.size
    }

    inner class ViewHolder(val binding: ItemListBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(u: User){
            //binding.ivListProfile
            binding.tvListName.text = u.name
            binding.tvListMbti.text = u.mbti
        }
    }

}