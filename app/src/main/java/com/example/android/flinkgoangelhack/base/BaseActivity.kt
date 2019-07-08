package com.example.android.flinkgoangelhack.base

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.android.flinkgoandroid.di.components.ActivityComponent
import com.example.android.flinkgoandroid.di.modules.ActivityModules
import com.example.android.flinkgoangelhack.App

abstract class BaseActivity : AppCompatActivity() {
    private lateinit var mActivityComponent: ActivityComponent
    lateinit var largeProgressBar: Dialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getViewId())
        initInjector()
        injectInjector()
        initData()
        initView()
    }

    abstract fun getViewId():Int

    abstract fun initData()

    private fun initInjector(){
        mActivityComponent = (application as App).getApplicationComponent()!!.getActivityComponent(ActivityModules(this))
    }
    abstract fun initView()

    fun getInjector(): ActivityComponent {
        return mActivityComponent
    }
    abstract fun injectInjector()

}