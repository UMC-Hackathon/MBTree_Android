package com.umc.project.mbtree.view.chat

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.umc.project.mbtree.R
import com.umc.project.mbtree.data.Chat
import com.umc.project.mbtree.databinding.ItemChatMeBinding

class ChatRVAdapter(private val context: Context): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var chatList = mutableListOf<Chat>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View?
        return when(viewType){
            1 -> {
                view = LayoutInflater.from(parent.context).inflate(
                    R.layout.item_chat_you, parent, false
                )
                LeftViewHolder(view)
            }
            2 -> {
                view = LayoutInflater.from(parent.context).inflate(
                    R.layout.item_chat_me, parent, false
                )
                LeftViewHolder(view)
            }
            else -> {
                view = LayoutInflater.from(parent.context).inflate(
                    R.layout.item_chat_you, parent, false
                )
                LeftViewHolder(view)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return chatList.size
    }

    //xml을 여러개 사용하려면 오버라이딩 해줘야 함
    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

    inner class LeftViewHolder(view: View): RecyclerView.ViewHolder(view){
        private val content: TextView = view.findViewById(R.id.tv_chat_content)

        fun bind(chat: Chat){
            content.text = chat.content
        }
    }

    inner class RightViewHolder(view: View): RecyclerView.ViewHolder(view){
        private val content: TextView = view.findViewById(R.id.tv_chat_content)

        fun bind(chat: Chat){
            content.text = chat.content
        }
    }

    inner class CenterViewHolder(view: View): RecyclerView.ViewHolder(view){
        private val content: TextView = view.findViewById(R.id.tv_chat_content)

        fun bind(chat: Chat){
            content.text = chat.content
        }
    }
}