package com.jemissapplication.app.modules.signup.ui

import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.eclatsol.kotlinaapi.model.SignUpRequest
import com.eclatsol.kotlinaapi.model.SignUpResponse
import com.jemissapplication.app.R
import com.jemissapplication.app.appcomponents.base.BaseActivity
import com.jemissapplication.app.databinding.ActivitySignUpBinding
import com.jemissapplication.app.modules.signup.data.viewmodel.SignUpVM
import com.jemissapplication.app.modules.signup.retrofitApi.ApiService
import com.jemissapplication.app.modules.signup.retrofitApi.ApiServiceData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpActivity : BaseActivity<ActivitySignUpBinding>(R.layout.activity_sign_up),
    View.OnClickListener {
    private val viewModel: SignUpVM by viewModels<SignUpVM>()
    val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\$"


    override fun onInitialized(): Unit {
        viewModel.navArguments = intent.extras?.getBundle("bundle")
        binding.signUpVM = viewModel

        binding.btnSignUp.setOnClickListener(this);
    }

    override fun setUpClicks(): Unit {
    }

    companion object {
        const val TAG: String = "SIGN_UP_ACTIVITY"

    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.btnSignUp -> {
                if (validSignUp()){
                    Log.e("fsdfdfdfdf", "onClick: " + isValidEmail(binding.etEmail.text.toString()))
                    signUpApi(
                        binding.etName.text.toString().trim(),
                        binding.etPassword.text.toString().trim(),
                        binding.etEmail.text.toString().trim()
                    )
                }
            }
        }
    }

    private fun validSignUp() : Boolean {
        var isValid : Boolean = false
        return when{
            binding.etName.text.toString().trim().isEmpty() ->{
                Toast.makeText(this@SignUpActivity,"Please enter username",Toast.LENGTH_SHORT).show()
                false
            }
            binding.etEmail.text.toString().trim().isEmpty() ->{
                Toast.makeText(this@SignUpActivity,"Please enter email",Toast.LENGTH_SHORT).show()
                false
            }
            binding.etEmail.text.toString().trim().isNotEmpty() ->{
                isValid = isValidEmail(binding.etEmail.text.toString().trim())
                if (!isValid){
                    Toast.makeText(this@SignUpActivity,"Please enter your valid email",Toast.LENGTH_SHORT).show()
                }
                return isValid
            }
            else ->{
                true
            }
        }
    }

    private fun signUpApi(name: String, password: String, email: String) {
        val signUpRequest = SignUpRequest(
            name,
            password,
            email,
            name,
            "https://www.computerhope.com/jargon/g/guest-user.jpg",
            1
        )
        ApiServiceData.apiService.signUpApi(signUpRequest)
            .enqueue(object : Callback<SignUpResponse> {
                override fun onResponse(
                    call: Call<SignUpResponse>,
                    response: Response<SignUpResponse>
                ) {
                    if (response.isSuccessful) {
                        Log.e("asfdsf", "Success")
                        startActivity(Intent(this@SignUpActivity,HomeActivity::class.java))
                        finish()
                        Toast.makeText(this@SignUpActivity,"Successfully SignUp",Toast.LENGTH_SHORT).show()
                    } else {
                        Log.e("asfdsf", "Failed to")
//                        Toast.makeText(this@SignUpActivity,"User already exit",Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<SignUpResponse>, t: Throwable) {
                    Log.e("asfdsf", "onFailure:")
                }

            })
    }

    fun isValidEmail(email: String): Boolean {
        return email.matches(emailRegex.toRegex())
    }

}
