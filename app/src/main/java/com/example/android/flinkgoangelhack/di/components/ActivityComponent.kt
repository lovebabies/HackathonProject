package com.example.android.flinkgoandroid.di.components

import com.example.android.flinkgoandroid.di.PerActivity
import com.example.android.flinkgoandroid.di.modules.ActivityModules
import com.example.android.flinkgoangelhack.ui.MainActivity
import dagger.Subcomponent

@PerActivity
@Subcomponent(modules = [ActivityModules::class])
interface ActivityComponent {
    fun inject(mainActivity: MainActivity)
}