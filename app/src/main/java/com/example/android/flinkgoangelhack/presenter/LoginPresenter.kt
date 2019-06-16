package com.example.android.flinkgoangelhack.presenter

import android.annotation.SuppressLint
import android.util.Log
import com.example.android.flinkgoangelhack.data.ApiService
import com.example.android.flinkgoangelhack.data.model.request.LoginRequest
import com.example.android.flinkgoangelhack.util.PreferencesUtil
import com.example.android.flinkgoangelhack.view.LoginView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class LoginPresenter @Inject constructor(val apiService: ApiService, val mPref: PreferencesUtil) {
    var view: LoginView? = null
    fun attachView(loginView: LoginView) {
        view = loginView
    }

    fun detachView() {
        view = null
    }

    @SuppressLint("CheckResult")
    fun login(loginRequest: LoginRequest) {
        apiService.login(loginRequest).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({
                if (it.status == 200) {
                    mPref.token = it.responseData?.accessToken
                    mPref.avatarUrl = it.responseData?.user?.userAvatar
                    mPref.userName = it.responseData?.user?.userName
                    view?.onLoginSuccess()
                } else {
                    view?.onLoginFailed()
                }
            }, {
                Log.e("ERRRRRORRR", " isss $it")
            })
    }
}