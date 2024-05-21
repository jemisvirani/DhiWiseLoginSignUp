package com.jemissapplication.app.modules.signup.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.jemissapplication.app.R
import com.jemissapplication.app.databinding.ActivityHomeBinding
import com.jemissapplication.app.modules.signup.ui.SignUpActivity.Companion.sharePref
import com.jemissapplication.app.modules.signup.utils.Const
import com.jemissapplication.app.modules.signup.utils.Pref

class HomeActivity : AppCompatActivity() ,View.OnClickListener {
    lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)


//        val highScore = sharePref.getInt(getString(R.string.saved_high_score_key),0)
//        Log.e("sfdsgdsgdg", "onCreate: $highScore")
        binding.imgLogout.setOnClickListener(this)
    }

    override fun onClick(view : View?) {
        when(view!!.id){
            R.id.imgLogout ->{
                Pref.setString(Const.SIGNUP,"logout")
                startActivity(Intent(this@HomeActivity,LoginActivity::class.java))
                finish()
            }
        }
    }
}