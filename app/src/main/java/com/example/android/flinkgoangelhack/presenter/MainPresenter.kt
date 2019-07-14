package com.example.android.flinkgoangelhack.presenter

import android.annotation.SuppressLint
import android.util.Log
import com.example.android.flinkgoangelhack.FakeResponse
import com.example.android.flinkgoangelhack.base.BasePresenter
import com.example.android.flinkgoangelhack.data.ApiService
import com.example.android.flinkgoangelhack.data.model.request.LoginRequest
import com.example.android.flinkgoangelhack.data.model.request.UserMessageRequest
import com.example.android.flinkgoangelhack.util.PreferencesUtil
import com.example.android.flinkgoangelhack.view.MainView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainPresenter @Inject constructor(val apiService: ApiService, val mPref: PreferencesUtil): BasePresenter<MainView>() {

    private val TAG = MainPresenter::class.simpleName

    @SuppressLint("CheckResult")
    fun chat(userMessageRequest: UserMessageRequest) {
        apiService.chat(userMessageRequest)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                view?.onMessageResponse(it)
            },
                {
                    Log.e(TAG, " error is $it")
                })
    }

    /*fun chatFake(userMessageRequest: UserMessageRequest){
        val info = FakeResponse.handleFakeRequest(userMessageRequest)
        view?.onMessageResponse(info)
    }*/

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
                Log.e(TAG, " error is $it")
            })
    }
}