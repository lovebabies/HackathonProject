package com.example.android.flinkgoangelhack.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.example.android.flinkgoangelhack.MessageViewHolder
import com.example.android.flinkgoangelhack.R
import com.example.android.flinkgoangelhack.data.model.response.Message
import com.example.android.flinkgoangelhack.util.PreferencesUtil
import com.example.android.flinkgoangelhack.util.Role

class MessageAdapter(val mPref: PreferencesUtil): RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    private var mMessageList = ArrayList<Message>()
    lateinit var mContext: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder{
        mContext = parent.context
        var view: View? = null
        if (viewType == USER_MESSAGE) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_user_message, parent, false)
            return UserMessageViewHolder(view)
        } else {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_bot_message, parent, false)
            return BotMessageViewHolder(view)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return mMessageList[position].userType
    }
    override fun getItemCount(): Int {
        return mMessageList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is UserMessageViewHolder -> {
                holder.bind(mMessageList[position])
            }
            is BotMessageViewHolder -> {
                holder.bind(mMessageList[position])
            }
        }
    }

    fun setMessageList(messageList: ArrayList<Message>) {
        mMessageList = messageList
        notifyDataSetChanged()
    }

    fun addMessage(message: Message) {
        mMessageList.add(message)
        notifyItemInserted(mMessageList.size)
    }

    fun removeMessage(position: Int) {
        mMessageList.removeAt(position)
        notifyDataSetChanged()
    }

    fun clearMessage() {
        mMessageList.clear()
    }


    companion object {
        val TAG = MessageAdapter::class.simpleName
        const val USER_MESSAGE = 0
        const val BOT_MESSAGE = 1
    }
}