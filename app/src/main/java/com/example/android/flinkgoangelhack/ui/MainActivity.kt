package com.example.android.flinkgoangelhack.ui

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Handler
import android.speech.RecognizerIntent
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.flinkgoangelhack.base.BaseActivity
import com.example.android.flinkgoangelhack.R
import com.example.android.flinkgoangelhack.data.model.request.UserMessageRequest
import com.example.android.flinkgoangelhack.data.model.response.BotResponseInfo
import com.example.android.flinkgoangelhack.data.model.response.Message
import com.example.android.flinkgoangelhack.presenter.MainPresenter
import com.example.android.flinkgoangelhack.util.PreferencesUtil
import com.example.android.flinkgoangelhack.util.Role
import com.example.android.flinkgoangelhack.view.MainView
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import javax.inject.Inject

class MainActivity : BaseActivity(), MainView {

    lateinit var mMessageAdapter: MessageAdapter

    @Inject
    lateinit var mPresenter: MainPresenter

    @Inject
    lateinit var mPref: PreferencesUtil

    private val REQ_CODE_SPEECH_INPUT = 100

    private var mMessageList = ArrayList<Message>()

    override fun getViewId(): Int {
        return R.layout.activity_main
    }

    override fun initData() {

    }

    override fun initView() {
        mPresenter.attachView(this)
        btnSend.setOnClickListener(onClickListener)
        setupRecyclerView()
    }

    override fun injectInjector() {
        getInjector().inject(this)
    }

    private fun setupRecyclerView() {
        mMessageAdapter = MessageAdapter(mPref)
        mMessageAdapter.setMessageList(mMessageList)
        messageRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = mMessageAdapter
        }
    }

    private val onClickListener = View.OnClickListener {
        when(it.id) {
            R.id.btnSend -> {
                mMessageAdapter.addMessage(
                    Message(
                        System.currentTimeMillis(),
                        edtUserInput.text.toString(),
                        Role.USER
                    )
                )
                mPresenter.chatFake(UserMessageRequest(edtUserInput.text.toString()))
                messageRecyclerView.smoothScrollToPosition(mMessageList.size)
            }
        }
    }

    override fun onMessageResponse(botresponseInfo: BotResponseInfo) {
       mMessageAdapter.addMessage(Message(System.currentTimeMillis(), botresponseInfo.message, Role.BOT))
        messageRecyclerView.smoothScrollToPosition(mMessageAdapter.itemCount)
    }

    override fun onLoginSuccess() {
    }

    override fun onLoginFailed() {
    }
}
