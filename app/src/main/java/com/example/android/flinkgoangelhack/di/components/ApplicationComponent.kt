package com.example.android.flinkgoandroid.di.components

import com.example.android.flinkgoandroid.di.modules.ActivityModules
import com.example.android.flinkgoandroid.di.modules.ApplicationModules
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModules::class])
interface ApplicationComponent {
    fun getActivityComponent(activityModules: ActivityModules): ActivityComponent
}