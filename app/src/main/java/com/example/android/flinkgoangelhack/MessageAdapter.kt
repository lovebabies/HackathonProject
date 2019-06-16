package com.example.android.flinkgoangelhack

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android.flinkgoangelhack.data.model.response.Message
import com.example.android.flinkgoangelhack.util.PreferencesUtil

class MessageAdapter(val mPref: PreferencesUtil): RecyclerView.Adapter<MessageViewHolder>(){
    private var mMessageList = ArrayList<Message>()
    private var mPreviousType = 2
    private var mCurrentType = 2
    lateinit var mContext: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        mContext = parent.context
        var view: View? = null
        if (viewType == USER_MESSAGE) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_user_message, parent, false)
            mCurrentType = USER_MESSAGE
        } else if (viewType == BOT_MESSAGE) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_bot_message, parent, false)
            mCurrentType = BOT_MESSAGE
        }
        return MessageViewHolder(view!!)
    }

    override fun getItemViewType(position: Int): Int {
        return mMessageList[position].userType
    }
    override fun getItemCount(): Int {
        return mMessageList.size
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        holder.bind(mMessageList[position], mContext, position, mMessageList.size - 1, mPreviousType, mPref)
        mPreviousType = mCurrentType
    }

    fun setMessageList(messageList: ArrayList<Message>) {
        mMessageList = messageList
        notifyDataSetChanged()
    }

    fun addMessage(message: Message) {
        mMessageList.add(message)
        notifyDataSetChanged()
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