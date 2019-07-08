package com.example.android.flinkgoangelhack.presenter

import android.annotation.SuppressLint
import android.util.Log
import com.example.android.flinkgoangelhack.base.BasePresenter
import com.example.android.flinkgoangelhack.data.ApiService
import com.example.android.flinkgoangelhack.data.model.request.UserMessageRequest
import com.example.android.flinkgoangelhack.view.LoginView
import com.example.android.flinkgoangelhack.view.MainView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainPresenter @Inject constructor(val apiService: ApiService): BasePresenter<MainView>() {

    @SuppressLint("CheckResult")
    fun chat(userMessageRequest: UserMessageRequest) {
        apiService.chat(userMessageRequest)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                view?.onMessageResponse(it.responseData!!)
            },
                {
                    Log.e("ERRRRRORRR", " isss $it")
                })
    }
}