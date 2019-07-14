package com.example.android.flinkgoandroid.di.modules

import android.content.Context
import com.example.android.flinkgoangelhack.data.ApiService
import com.example.android.flinkgoangelhack.util.PreferencesUtil
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class ApplicationModules(private val mContext: Context) {

    @Singleton
    @Provides
    fun provideOkHttpClient(mPref: PreferencesUtil): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor{
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
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .baseUrl("http://34.87.35.158:5005")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
    }

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideContext(): Context {
        return mContext
    }

    @Singleton
    @Provides
    fun providePreferences(): PreferencesUtil {
        return PreferencesUtil(mContext)
    }
}