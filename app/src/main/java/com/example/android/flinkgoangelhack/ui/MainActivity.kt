package com.example.android.flinkgoangelhack.ui

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.flinkgoangelhack.R
import com.example.android.flinkgoangelhack.base.BaseActivity
import com.example.android.flinkgoangelhack.data.model.request.UserMessageRequest
import com.example.android.flinkgoangelhack.data.model.response.BotResponseInfo
import com.example.android.flinkgoangelhack.data.model.response.Message
import com.example.android.flinkgoangelhack.presenter.MainPresenter
import com.example.android.flinkgoangelhack.util.*
import com.example.android.flinkgoangelhack.util.AuthenticationStatus.DONE_REGISTER
import com.example.android.flinkgoangelhack.util.AuthenticationStatus.GET_ADDRESS
import com.example.android.flinkgoangelhack.util.AuthenticationStatus.GET_AGE
import com.example.android.flinkgoangelhack.util.AuthenticationStatus.GET_GENDER
import com.example.android.flinkgoangelhack.util.AuthenticationStatus.GET_NAME
import com.example.android.flinkgoangelhack.util.AuthenticationStatus.GET_PASSWORD
import com.example.android.flinkgoangelhack.util.AuthenticationStatus.GET_USER_NAME
import com.example.android.flinkgoangelhack.util.AuthenticationStatus.LET_DONE_REGISTER
import com.example.android.flinkgoangelhack.util.AuthenticationStatus.NOT_YET
import com.example.android.flinkgoangelhack.util.GENDER.FEMALE
import com.example.android.flinkgoangelhack.util.GENDER.MALE
import com.example.android.flinkgoangelhack.util.GENDER.OTHER
import com.example.android.flinkgoangelhack.view.MainView
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.NumberFormatException
import java.util.*
import javax.inject.Inject

class MainActivity : BaseActivity(), MainView {

    lateinit var mMessageAdapter: MessageAdapter

    private var mDataStatus = AuthenticationStatus.NOT_YET

    @Inject
    lateinit var mPresenter: MainPresenter

    @Inject
    lateinit var mPref: PreferencesUtil

    private var mMessageList = ArrayList<Message>()

    override fun getViewId(): Int {
        return R.layout.activity_main
    }

    override fun initData() {
        mDataStatus = mPref.dataStatus!!
        if (mDataStatus == -1) {
            mDataStatus = NOT_YET
        }
    }

    override fun initView() {
        mPresenter.attachView(this)
        btnSend.setOnClickListener(onClickListener)
        setupRecyclerView()
        edtUserInput.requestFocus()
        if (mPref.userName == null) {
            startRegisterProcess()
        }

        imgArrowBack.setOnClickListener {
            finishAffinity()
            System.exit(0)
        }
    }

    override fun injectInjector() {
        getInjector().inject(this)
    }

    private fun setupRecyclerView() {
        mMessageAdapter = MessageAdapter(mPref)
        mMessageAdapter.setMessageList(mMessageList)
        messageRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity) as RecyclerView.LayoutManager?
            adapter = mMessageAdapter
        }
    }

    private val onClickListener = View.OnClickListener {
        when(it.id) {
            R.id.btnSend -> {
               when (mDataStatus) {
                   NOT_YET -> {
                       val result = edtUserInput.text.toString()
                       if (!result.isNullOrEmpty()) {
                           mMessageAdapter.addMessage(
                               Message(
                                   System.currentTimeMillis(),
                                   result,
                                   Role.USER
                               )
                           )
                       }
                       messageRecyclerView.smoothScrollToPosition(mMessageAdapter.itemCount)
                       if (result.toLowerCase() == "ok" || result.toLowerCase() == "yes" || result.toLowerCase() == "y") {
                           askName()
                           mDataStatus = GET_NAME
                       } else {
                           mMessageAdapter.addMessage(Message(System.currentTimeMillis(), MISS.miss_yes_no_question, Role.BOT))
                           messageRecyclerView.smoothScrollToPosition(mMessageAdapter.itemCount)
                       }
                   }

                   GET_NAME -> {
                       val result = edtUserInput.text.toString()
                       if (!result.isNullOrEmpty()) {
                           mMessageAdapter.addMessage(
                               Message(
                                   System.currentTimeMillis(),
                                   result,
                                   Role.USER
                               )
                           )
                       }
                       messageRecyclerView.smoothScrollToPosition(mMessageAdapter.itemCount)
                       mPref.name = result
                       askGender()
                       mDataStatus = GET_GENDER
                   }

                   GET_GENDER -> {
                       val result = edtUserInput.text.toString()
                       if (!result.isNullOrEmpty()) {
                           mMessageAdapter.addMessage(
                               Message(
                                   System.currentTimeMillis(),
                                   result,
                                   Role.USER
                               )
                           )
                       }
                       messageRecyclerView.smoothScrollToPosition(mMessageAdapter.itemCount)
                       if (result.toLowerCase() == "nam") {
                           mPref.gender = MALE
                       } else if (result.toLowerCase() == "nu") {
                           mPref.gender = FEMALE
                       } else {
                           mPref.gender = OTHER
                           mMessageAdapter.addMessage(Message(System.currentTimeMillis(), MISS.miss_gender, Role.BOT))
                           messageRecyclerView.smoothScrollToPosition(mMessageAdapter.itemCount)
                       }
                       askUserName()
                       mDataStatus = GET_USER_NAME
                   }

                   GET_USER_NAME -> {
                       val result = edtUserInput.text.toString()
                       if (!result.isNullOrEmpty()) {
                           mMessageAdapter.addMessage(
                               Message(
                                   System.currentTimeMillis(),
                                   result,
                                   Role.USER
                               )
                           )
                       }
                       messageRecyclerView.smoothScrollToPosition(mMessageAdapter.itemCount)
                       mPref.userName = result
                       askPassword()
                       mDataStatus = GET_PASSWORD
                   }

                   GET_PASSWORD -> {
                       val result = edtUserInput.text.toString()
                       if (!result.isNullOrEmpty()) {
                           mMessageAdapter.addMessage(
                               Message(
                                   System.currentTimeMillis(),
                                   result,
                                   Role.USER
                               )
                           )
                       }
                       messageRecyclerView.smoothScrollToPosition(mMessageAdapter.itemCount)
                       askAge()
                       mDataStatus = GET_AGE
                   }

                   GET_AGE -> {
                       val result = edtUserInput.text.toString()
                       if (!result.isNullOrEmpty()) {
                           mMessageAdapter.addMessage(
                               Message(
                                   System.currentTimeMillis(),
                                   result,
                                   Role.USER
                               )
                           )
                       }
                       messageRecyclerView.smoothScrollToPosition(mMessageAdapter.itemCount)
                       try {
                           mPref.age = result.toInt()
                           askAddress()
                           mDataStatus = GET_ADDRESS
                       } catch (e: NumberFormatException) {
                           mMessageAdapter.addMessage(Message(System.currentTimeMillis(), MISS.miss_age, Role.BOT))
                           messageRecyclerView.smoothScrollToPosition(mMessageAdapter.itemCount)
                       }
                   }

                   GET_ADDRESS -> {
                       val result = edtUserInput.text.toString()
                       if (!result.isNullOrEmpty()) {
                           mMessageAdapter.addMessage(
                               Message(
                                   System.currentTimeMillis(),
                                   result,
                                   Role.USER
                               )
                           )
                       }
                       messageRecyclerView.smoothScrollToPosition(mMessageAdapter.itemCount)
                       mPref.address = result
                       endRegisterProcess()
                       mDataStatus = LET_DONE_REGISTER
                   }

                   LET_DONE_REGISTER -> {
                       val result = edtUserInput.text.toString()
                       if (!result.isNullOrEmpty()) {
                           mMessageAdapter.addMessage(
                               Message(
                                   System.currentTimeMillis(),
                                   result,
                                   Role.USER
                               )
                           )
                       }
                       messageRecyclerView.smoothScrollToPosition(mMessageAdapter.itemCount)
                       if (result.toLowerCase() == "yes" || result.toLowerCase() == "y") {
                   //        Toast.makeText(this, "You're done now you can use app", Toast.LENGTH_LONG).show()
                           showRegisterSuccessDialog()
                           mDataStatus = DONE_REGISTER
                       } else {
                           mMessageAdapter.addMessage(Message(System.currentTimeMillis(), MISS.miss_yes_no_question, Role.BOT))
                           messageRecyclerView.smoothScrollToPosition(mMessageAdapter.itemCount)
                       }
                   }

                    DONE_REGISTER -> {
                        val result = edtUserInput.text.toString()

                        if (!result.isNullOrEmpty()) {
                            mMessageAdapter.addMessage(
                                Message(
                                    System.currentTimeMillis(),
                                    result,
                                    Role.USER
                                )
                            )
                        }
                       mPresenter.chat(UserMessageRequest(edtUserInput.text.toString()))
                       messageRecyclerView.smoothScrollToPosition(mMessageAdapter.itemCount)
                   }
               }

                mPref.dataStatus = mDataStatus
                edtUserInput.setText("")
            }
        }
    }

    //-----------------------------------------Register Asking Part ----------------------------------------------//
    private fun startRegisterProcess() {
        mMessageAdapter.addMessage(Message(System.currentTimeMillis(), BotAsking.GREETING, Role.BOT))
        messageRecyclerView.smoothScrollToPosition(mMessageAdapter.itemCount)
    }

    private fun askName() {
        mMessageAdapter.addMessage(Message(System.currentTimeMillis(), BotAsking.NAME_QUESTION, Role.BOT))
        messageRecyclerView.smoothScrollToPosition(mMessageAdapter.itemCount)
    }

    private fun askGender() {
        mMessageAdapter.addMessage(Message(System.currentTimeMillis(), BotAsking.GENDER_QUESTION, Role.BOT))
        messageRecyclerView.smoothScrollToPosition(mMessageAdapter.itemCount)
    }

    private fun askPassword() {
        mMessageAdapter.addMessage(Message(System.currentTimeMillis(), BotAsking.PASSWORD_QUESTION, Role.BOT))
        messageRecyclerView.smoothScrollToPosition(mMessageAdapter.itemCount)
    }

    private fun askUserName() {
        mMessageAdapter.addMessage(Message(System.currentTimeMillis(), BotAsking.USER_NAME_QUESTION, Role.BOT))
        messageRecyclerView.smoothScrollToPosition(mMessageAdapter.itemCount)
    }

    private fun askAge() {
        mMessageAdapter.addMessage(Message(System.currentTimeMillis(), BotAsking.AGE_QUESTION, Role.BOT))
        messageRecyclerView.smoothScrollToPosition(mMessageAdapter.itemCount)
    }

    private fun askAddress() {
        mMessageAdapter.addMessage(Message(System.currentTimeMillis(), BotAsking.ADDRESS_QUESTION, Role.BOT))
        messageRecyclerView.smoothScrollToPosition(mMessageAdapter.itemCount)
    }

    private fun endRegisterProcess() {
        mMessageAdapter.addMessage(Message(System.currentTimeMillis(), BotAsking.SUBMIT, Role.BOT))
        messageRecyclerView.smoothScrollToPosition(mMessageAdapter.itemCount)
    }

    override fun onMessageResponse(botresponseInfoList: ArrayList<BotResponseInfo>?) {
        if (botresponseInfoList != null) {
            for (i in botresponseInfoList) {
                if (i.text != "Xin lỗi, mình chưa hiểu được ý bạn ") {
                    mMessageAdapter.addMessage(Message(System.currentTimeMillis(), i.text, Role.BOT))
                    messageRecyclerView.smoothScrollToPosition(mMessageAdapter.itemCount)
                } else {
                    funnyResponse()
                }
            }
        } else {
            funnyResponse()
        }
    }

    private fun funnyResponse() {
        val random = Random()
        val n = random.nextInt(4)
        when(n) {
            0 -> {
                mMessageAdapter.addMessage(Message(System.currentTimeMillis(), MISS.type1, Role.BOT))
                messageRecyclerView.smoothScrollToPosition(mMessageAdapter.itemCount)
            }
            1 -> {
                mMessageAdapter.addMessage(Message(System.currentTimeMillis(), MISS.type2, Role.BOT))
                messageRecyclerView.smoothScrollToPosition(mMessageAdapter.itemCount)
            }
            2 -> {
                mMessageAdapter.addMessage(Message(System.currentTimeMillis(), MISS.type3, Role.BOT))
                messageRecyclerView.smoothScrollToPosition(mMessageAdapter.itemCount)
            }
            3 -> {
                mMessageAdapter.addMessage(Message(System.currentTimeMillis(), MISS.type4, Role.BOT))
                messageRecyclerView.smoothScrollToPosition(mMessageAdapter.itemCount)
            }
        }
    }

    private fun showRegisterSuccessDialog() {
        var builder = androidx.appcompat.app.AlertDialog.Builder(this)
        builder.setTitle("Bạn đã đăng ký thành công")
        builder.setMessage("Từ bây giờ bạn đã có thể sử dụng app ")
        builder.setPositiveButton("OK", object: DialogInterface.OnClickListener{
            override fun onClick(dialog: DialogInterface?, which: Int) {
                dialog?.dismiss()
            }
        })
        var dialog = builder.create()
        dialog.window.attributes.windowAnimations = R.style.RegisterSuccessDialog
        dialog.show()

    }

    override fun onLoginSuccess() {
    }

    override fun onLoginFailed() {
    }
}
