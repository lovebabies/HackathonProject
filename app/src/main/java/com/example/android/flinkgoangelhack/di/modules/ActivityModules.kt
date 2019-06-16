package com.example.android.flinkgoandroid.di.modules

import android.app.Activity
import com.example.android.flinkgoandroid.di.PerActivity
import dagger.Module
import dagger.Provides

@Module
class ActivityModules(private val mActivity: Activity) {

    @PerActivity
    @Provides
    fun provideAcitivity(): Activity {
        return mActivity
    }
}