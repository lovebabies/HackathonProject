package com.example.android.flinkgoangelhack.data

import com.example.android.flinkgoangelhack.data.model.request.LoginRequest
import com.example.android.flinkgoangelhack.data.model.request.UserMessageRequest
import com.example.android.flinkgoangelhack.data.model.response.BotResponse
import com.example.android.flinkgoangelhack.data.model.response.BotResponseInfo
import com.example.android.flinkgoangelhack.data.model.response.LoginResponse
import com.example.android.flinkgoangelhack.util.Api.CHAT
import com.example.android.flinkgoangelhack.util.Api.LOGIN
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST(LOGIN)
    fun login(@Body loginRequest: LoginRequest): Observable<LoginResponse>

    @POST(CHAT)
    fun chat(@Body userMessageRequest: UserMessageRequest): Observable<BotResponse>
}