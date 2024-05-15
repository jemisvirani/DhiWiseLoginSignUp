package com.jemissapplication.app.modules.signup.ui

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.View
import android.widget.Toast
import com.jemissapplication.app.R
import com.jemissapplication.app.appcomponents.base.BaseActivity
import com.jemissapplication.app.databinding.ActivityLoginBinding
import com.jemissapplication.app.modules.signup.data.modelclass.LoginRequest
import com.jemissapplication.app.modules.signup.data.modelclass.LoginResponse
import com.jemissapplication.app.modules.signup.retrofitApi.ApiServiceData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : BaseActivity<ActivityLoginBinding>(R.layout.activity_login), View.OnClickListener {
    val emailRegex = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
    var isValid : Boolean = false
    var isValidPassword : Boolean = false
    var isPasswordVisible = false


    override fun onInitialized() : Unit {
        binding.btnLogin.setOnClickListener(this)
        binding.ivLoginHideShow.setOnClickListener(this)
        binding.txtSignUp.setOnClickListener(this)
    }

    override fun setUpClicks() {

    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.btnLogin ->{
               if(validLogin() && validPassword()){
                   loginApi(binding.etLoginEmail.text.toString().trim(),binding.etLoginPassword.text.toString().trim())
               }
            }
            R.id.ivLoginHideShow ->{
                togglePasswordVisibility()
            }
            R.id.txtSignUp ->{
                startActivity(Intent(this@LoginActivity,SignUpActivity::class.java))
            }
        }
    }

    private fun loginApi(username: String, password: String) {
        val loginRequest = LoginRequest(username,password)
        ApiServiceData.apiService.loginApi(loginRequest).enqueue(object : Callback<LoginResponse>{
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if(response.isSuccessful){
                    Log.e("fdgdgd", "Login Success")
                    startActivity(Intent(this@LoginActivity,HomeActivity::class.java))
                }else{
                    Log.e("fdgdgd", "Failed To")
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Log.e("fdgdgd", "onFailure: ")
            }

        })
    }

    private fun validLogin() : Boolean {
        return when{
            binding.etLoginEmail.text.toString().trim().isEmpty() ->{
                Toast.makeText(this@LoginActivity,"Please enter your email", Toast.LENGTH_SHORT).show()
                false
            }
            binding.etLoginEmail.text.toString().trim().isNotEmpty() ->{
                isValid = isValidEmail(binding.etLoginEmail.text.toString().trim())
                if (!isValid){
                    Toast.makeText(this@LoginActivity,"Please enter your valid email", Toast.LENGTH_SHORT).show()
                }
                return isValid
            }
            else ->{
                true
            }
        }
    }

    fun isValidEmail(email: String): Boolean {
        return email.matches(emailRegex.toRegex())
    }

    private fun validPassword(): Boolean {
        return when{
            binding.etLoginPassword.text.toString().trim().isEmpty() ->{
                Toast.makeText(this@LoginActivity,"Please enter your password",Toast.LENGTH_SHORT).show()
                false
            }
            binding.etLoginPassword.text.toString().trim().isNotEmpty() ->{
                isValidPassword = isValidPassword(binding.etLoginPassword.text.toString().trim())
                return isValidPassword
            }
            else ->{
                true
            }
        }

    }

    private fun isValidPassword(password: String): Boolean {
        if (password.filter { it.isLetter() }.filter { it.isUpperCase() }.firstOrNull() == null) {
            Toast.makeText(this@LoginActivity,"Please enter your uppercase character",Toast.LENGTH_SHORT).show()
            return false
        }

        if (password.filter { it.isLetter() }.filter { it.isLowerCase() }.firstOrNull() == null){
            Toast.makeText(this@LoginActivity,"Please enter your lowercase character",Toast.LENGTH_SHORT).show()
            return false
        }

        if (password.filter { it.isDigit() }.firstOrNull() == null) {
            Toast.makeText(this@LoginActivity,"Please enter your digit",Toast.LENGTH_SHORT).show()
            return false
        }

        if (password.filter { !it.isLetterOrDigit() }.firstOrNull() == null){
            Toast.makeText(this@LoginActivity,"Please enter your symbol",Toast.LENGTH_SHORT).show()
            return false
        }
        if (password.length < 8) {
            Toast.makeText(this@LoginActivity,"Please enter your minimum 8 character ",Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    private fun togglePasswordVisibility() {
        if (isPasswordVisible) {
            binding.etLoginPassword.inputType =
                InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            isPasswordVisible = false
            binding.ivLoginHideShow.setImageResource(R.drawable.ic_eye_hide)
        } else {
            binding.etLoginPassword.inputType =
                InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            isPasswordVisible = true
            binding.ivLoginHideShow.setImageResource(R.drawable.ic_eye_show)
        }
        binding.etLoginPassword.setSelection(binding.etLoginPassword.text.length)
    }
}