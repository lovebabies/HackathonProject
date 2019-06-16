package com.example.android.flinkgoangelhack.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.android.flinkgoangelhack.BaseActivity
import com.example.android.flinkgoangelhack.R
import com.example.android.flinkgoangelhack.data.ApiService
import com.example.android.flinkgoangelhack.data.model.request.LoginRequest
import com.example.android.flinkgoangelhack.network.RetrofitInstance
import com.example.android.flinkgoangelhack.presenter.LoginPresenter
import com.example.android.flinkgoangelhack.util.PreferencesUtil
import com.example.android.flinkgoangelhack.view.LoginView
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject

class LoginActivity: BaseActivity(), LoginView {

    private val TAG = LoginActivity::class.simpleName
    lateinit var mFirebaseDatabase: FirebaseDatabase
    lateinit var mUserReference: DatabaseReference

    @Inject
    lateinit var mPresenter: LoginPresenter

    lateinit var mPref: PreferencesUtil

    override fun getViewId(): Int {
        return R.layout.activity_login
    }

    override fun initData() {

    }

    override fun initView() {
        mPresenter.attachView(this)
        btnLogin.setOnClickListener {
            mPresenter.login(LoginRequest(edtUserName.text.toString(), edtPassword.text.toString()))
        }
    }

    override fun injectInjector() {
        getInjector().inject(this)
    }

    override fun onLoginSuccess() {
        Toast.makeText(this, "SUCCESS", Toast.LENGTH_LONG).show()
        startActivity(Intent(this, MainActivity::class.java))
    }

    override fun onLoginFailed() {
        Toast.makeText(this, "FAILED", Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        mPresenter.detachView()
        super.onDestroy()
    }


}