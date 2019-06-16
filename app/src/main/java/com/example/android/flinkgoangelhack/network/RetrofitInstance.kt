package com.example.android.flinkgoangelhack.network

import com.example.android.flinkgoangelhack.util.PreferencesUtil
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitInstance() {
    companion object {
        var retrofit: Retrofit? = null
        var okHttpClient: OkHttpClient? = null
        val BASE_URL = "http://34.87.30.67:8080"

        fun createRetrofitInstance(mPref: PreferencesUtil): Retrofit{
            okHttpClient = OkHttpClient.Builder()
                .addInterceptor {
                    var request: Request? = null
                    if (mPref.token == null) {
                        request = it.request().newBuilder().build()
                    } else {
                        request = it.request().newBuilder()
                            .addHeader("Authorization","Bearer ${mPref.token}")
                            .build()
                    }
                    it.proceed(request)
                }
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .build()

            val retrofitInstance: Retrofit = retrofit2.Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofitInstance
        }
    }
}
