package com.example.android.flinkgoangelhack.view

import com.example.android.flinkgoangelhack.base.BaseView

interface LoginView: BaseView {
    fun onLoginSuccess()
    fun onLoginFailed()
}