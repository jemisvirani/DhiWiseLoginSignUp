package com.jemissapplication.app.modules.signup.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.jemissapplication.app.R
import com.jemissapplication.app.appcomponents.base.BaseActivity
import com.jemissapplication.app.databinding.ActivityHomeBinding
import com.jemissapplication.app.databinding.ActivitySplashBinding
import com.jemissapplication.app.modules.signup.utils.Const
import com.jemissapplication.app.modules.signup.utils.Pref
import java.util.Timer
import java.util.TimerTask

class SplashActivity : BaseActivity<ActivitySplashBinding>(R.layout.activity_splash) {

    val timer = Timer()

    override fun onInitialized() {
        super.onInitialized()
        Pref.setInit(this)
        timer.schedule(object : TimerTask() {
            override fun run() {
                inNext()
            }
        }, 3000)
    }

    override fun setUpClicks() {

    }

    private fun inNext() {
        if (Pref.getString(Const.SIGNUP, "") == "signup") {
            startActivity(Intent(this@SplashActivity, HomeActivity::class.java))
        } else if (Pref.getString(Const.SIGNUP, "") == "login") {
            startActivity(Intent(this@SplashActivity, HomeActivity::class.java))
        } else {
            startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
        }
        finish()
    }
}