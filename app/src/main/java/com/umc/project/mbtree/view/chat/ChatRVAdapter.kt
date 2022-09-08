package com.umc.project.mbtree.view.chat

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.umc.project.mbtree.R
import com.umc.project.mbtree.data.Chat
import com.umc.project.mbtree.data.QuizAnswer
import com.umc.project.mbtree.data.QuizAnswerConfirm
import com.umc.project.mbtree.remote.ChatRetrofitInterface
import com.umc.project.mbtree.remote.getRetrofit
import org.w3c.dom.Text
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

//,private val chatList:ArrayList<Chat>
class ChatRVAdapter(private val context: Context): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var chatList = mutableListOf<Chat>()

    interface MyItemClickListener{
        fun clickButton1(chat: Chat)
        fun clickButton2(chat: Chat)
//        fun setQuizAnswer(roomId:Int, userId:Int, answer:Int)
//        fun getQuizAnswer(roomId: Int)
    }
    private lateinit var mItemClickListener: MyItemClickListener
    fun setMyItemClickListener(itemClickListener: MyItemClickListener){
        mItemClickListener = itemClickListener
    }

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
            3 -> {
                view = LayoutInflater.from(parent.context).inflate(
                    R.layout.item_chat_question, parent, false
                )
                CenterViewHolder(view)
            }
            else -> {
                throw RuntimeException("Error")
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

    fun addItem(chat: Chat){
        chatList.add(chat)
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
        private val question: TextView = view.findViewById(R.id.tv_chat_question)
        private val a1: Button = view.findViewById(R.id.btn_chat_a)
        private val a2: Button = view.findViewById(R.id.btn_chat_b)

        fun bind(chat: Chat){
            question.text = chat.question
            a1.text = chat.answer1
            a2.text = chat.answer2

            a1.setOnClickListener{
                mItemClickListener.clickButton1(chatList[position])
            }
            a2.setOnClickListener{
                mItemClickListener.clickButton2(chatList[position])
            }
        }
    }

}
