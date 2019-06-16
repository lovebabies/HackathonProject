package com.example.android.flinkgoangelhack

import android.app.Application
import android.content.Context
import com.example.android.flinkgoandroid.di.components.ApplicationComponent
import com.example.android.flinkgoandroid.di.components.DaggerApplicationComponent
import com.example.android.flinkgoandroid.di.modules.ApplicationModules

class App : Application() {
    private var mApplicationComponent: ApplicationComponent? = null
    override fun onCreate() {
        super.onCreate()
        initApplicationComponent()
    }

    private fun initApplicationComponent() {
        mApplicationComponent = DaggerApplicationComponent.builder().applicationModules(ApplicationModules(this)).build()
    }

    fun getApplicationComponent(): ApplicationComponent? {
        return mApplicationComponent
    }
}