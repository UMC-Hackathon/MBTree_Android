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

class ChatRVAdapter(private val context: Context,
                    private val chatList:ArrayList<Chat>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var chatList2 = mutableListOf<Chat>()

    //처음에 화면에 보일 아이템뷰 생성
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
                RightViewHolder(view)
            }
            else -> {
                view = LayoutInflater.from(parent.context).inflate(
                    R.layout.item_chat_you, parent, false
                )
                CenterViewHolder(view)
            }
        }
    }

    //뷰홀더에 데이터를 바인딩할때마다 호출되는 함수
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(chatList[position].viewType){
            1 -> {
                (holder as LeftViewHolder).bind(chatList[position])
                holder.setIsRecyclable(false)
            }
            2 -> {
                (holder as RightViewHolder).bind(chatList[position])
                holder.setIsRecyclable(false)
            }
            else -> {
                (holder as CenterViewHolder).bind(chatList[position])
                holder.setIsRecyclable(false)
            }
        }
    }

    override fun getItemCount(): Int {
        return chatList.size
    }

    //xml을 여러개 사용하려면 오버라이딩 해줘야 함
    override fun getItemViewType(position: Int): Int {
        return chatList[position].viewType
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