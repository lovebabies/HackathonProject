package com.example.android.flinkgoangelhack

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.example.android.flinkgoandroid.di.components.ActivityComponent
import com.example.android.flinkgoandroid.di.modules.ActivityModules

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