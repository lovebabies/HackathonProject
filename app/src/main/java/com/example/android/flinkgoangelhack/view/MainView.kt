package com.example.android.flinkgoangelhack.view

import com.example.android.flinkgoangelhack.base.BaseView
import com.example.android.flinkgoangelhack.data.model.response.BotResponseInfo

interface MainView: BaseView {
    fun onMessageResponse(botResponseInfo: BotResponseInfo)
}