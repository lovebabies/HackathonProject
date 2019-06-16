package com.example.android.flinkgoangelhack.view

import com.example.android.flinkgoangelhack.data.model.response.BotResponseInfo

interface MainView{
    fun onMessageResponse(botResponseInfo: BotResponseInfo)
}