package com.example.android.flinkgoangelhack.ui.laucher

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.android.flinkgoangelhack.R
import com.example.android.flinkgoangelhack.ui.MainActivity
import com.example.android.flinkgoangelhack.util.LAUNCHER_SCREEN_DELAY

class LaucherActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.LauncherTheme)

        //Navigate to Main Screen
        Handler().postDelayed({
            this.startActivity(Intent(this, MainActivity::class.java))
            this.finish()
        }, LAUNCHER_SCREEN_DELAY)
    }
}