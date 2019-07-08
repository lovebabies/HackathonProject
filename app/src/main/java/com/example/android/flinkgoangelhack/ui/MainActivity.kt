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
import com.example.android.flinkgoangelhack.MessageAdapter
import com.example.android.flinkgoangelhack.R
import com.example.android.flinkgoangelhack.data.model.request.UserMessageRequest
import com.example.android.flinkgoangelhack.data.model.response.BotResponseInfo
import com.example.android.flinkgoangelhack.data.model.response.Message
import com.example.android.flinkgoangelhack.presenter.MainPresenter
import com.example.android.flinkgoangelhack.util.PreferencesUtil
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
        microphone.setOnClickListener(onClickListener)
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
                mPresenter.chat(UserMessageRequest(edtUserInput.text.toString()))
                mMessageAdapter.addMessage(
                    Message(
                        System.currentTimeMillis(),
                        edtUserInput.text.toString(),
                        0
                    )
                )
                messageRecyclerView.smoothScrollToPosition(mMessageList.size)
            }

            R.id.microphone -> {
                promptSpeechInput()
            }
        }
    }

    /**
     * Showing google speech input dialog
     */
    private fun promptSpeechInput() {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intent.putExtra(
            RecognizerIntent.EXTRA_LANGUAGE_MODEL,
            RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
        )
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
        intent.putExtra(
            RecognizerIntent.EXTRA_PROMPT,
            getString(R.string.speech_prompt)
        )
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT)
        } catch (a: ActivityNotFoundException) {
            Toast.makeText(
                applicationContext,
                getString(R.string.speech_not_supported),
                Toast.LENGTH_SHORT
            ).show()
        }

    }

    /**
     * Receiving speech input
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            REQ_CODE_SPEECH_INPUT -> {
                if (resultCode == Activity.RESULT_OK && null != data) {

                    val result = data
                        .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
                    mPresenter.chat(UserMessageRequest(result[0]))
                    mMessageAdapter.addMessage(
                        Message(
                            System.currentTimeMillis(),
                            result[0],
                            0
                        )
                    )
                }
            }
        }
    }

    override fun onMessageResponse(botresponseInfo: BotResponseInfo) {
        val botReply = botresponseInfo.message
        val botMentors = botresponseInfo.mentors
        val botUsers = botresponseInfo.users
        mMessageAdapter.addMessage(Message(System.currentTimeMillis(),botReply,1))
        messageRecyclerView.smoothScrollToPosition(mMessageList.size)
        if (botMentors != null) {
            for (i in botMentors) {
                Handler().postDelayed({
                    val result = "${i.name} (${i.organization})"
                    mMessageAdapter.addMessage(Message(System.currentTimeMillis(),result,1))
                    messageRecyclerView.smoothScrollToPosition(mMessageList.size)
                },2000)
            }
        }

        if (botUsers != null) {
            for (i in botUsers) {
                Handler().postDelayed({
                    val result = "${i.name} address: (${i.address.address})"
                    mMessageAdapter.addMessage(Message(System.currentTimeMillis(),result,1))
                    messageRecyclerView.smoothScrollToPosition(mMessageList.size)
                },2000)
            }
        }
    }
}
