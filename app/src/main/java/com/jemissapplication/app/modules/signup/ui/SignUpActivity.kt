package com.jemissapplication.app.modules.signup.ui

import android.content.Intent
import android.text.InputType
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
import com.jemissapplication.app.modules.signup.retrofitApi.ApiServiceData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SignUpActivity : BaseActivity<ActivitySignUpBinding>(R.layout.activity_sign_up),
    View.OnClickListener {
    private val viewModel: SignUpVM by viewModels<SignUpVM>()
    val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\$"
    var isValid : Boolean = false
    var isValidPassword : Boolean = false
    var isPasswordVisible = false
    var isCheck : Boolean = false


    override fun onInitialized(): Unit {
        viewModel.navArguments = intent.extras?.getBundle("bundle")
        binding.signUpVM = viewModel

        binding.btnSignUp.setOnClickListener(this)
        binding.ivHideShow.setOnClickListener(this)
        binding.checkBox.setOnClickListener(this)

    }

    override fun setUpClicks(): Unit {
    }

    companion object {
        const val TAG: String = "SIGN_UP_ACTIVITY"

    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.btnSignUp -> {
                if (validSignUp() && validPassword() && isCheckValid()){
                    Log.e("fsdfdfdfdf", "onClick: " + isValidEmail(binding.etEmail.text.toString()))
                    signUpApi(
                        binding.etName.text.toString().trim(),
                        binding.etPassword.text.toString().trim(),
                        binding.etEmail.text.toString().trim()
                    )
                }
            }
            R.id.ivHideShow ->{
                togglePasswordVisibility()
            }
            R.id.checkBox ->{
                isCheck = binding.checkBox.isChecked
            }
        }
    }

    private fun isCheckValid(): Boolean {
        if (isCheck){
            return true
        }else{
            Toast.makeText(this@SignUpActivity,"Please select checkbox",Toast.LENGTH_SHORT).show()
        }
        return isCheck
    }

    private fun validPassword(): Boolean {
        return when{
            binding.etPassword.text.toString().trim().isEmpty() ->{
                Toast.makeText(this@SignUpActivity,"Please enter your password",Toast.LENGTH_SHORT).show()
                false
            }
            binding.etPassword.text.toString().trim().isNotEmpty() ->{
                isValidPassword = isValidPassword(binding.etPassword.text.toString().trim())
                return isValidPassword
            }
            else ->{
               true
            }
        }

    }

    private fun validSignUp() : Boolean {
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
                        Toast.makeText(this@SignUpActivity,"User already exit",Toast.LENGTH_SHORT).show()
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

    private fun isValidPassword(password: String): Boolean {
        if (password.filter { it.isLetter() }.filter { it.isUpperCase() }.firstOrNull() == null) {
            Toast.makeText(this@SignUpActivity,"Please enter your uppercase character",Toast.LENGTH_SHORT).show()
            return false
        }

        if (password.filter { it.isLetter() }.filter { it.isLowerCase() }.firstOrNull() == null){
            Toast.makeText(this@SignUpActivity,"Please enter your lowercase character",Toast.LENGTH_SHORT).show()
            return false
        }

        if (password.filter { it.isDigit() }.firstOrNull() == null) {
            Toast.makeText(this@SignUpActivity,"Please enter your digit",Toast.LENGTH_SHORT).show()
            return false
        }

        if (password.filter { !it.isLetterOrDigit() }.firstOrNull() == null){
            Toast.makeText(this@SignUpActivity,"Please enter your symbol",Toast.LENGTH_SHORT).show()
            return false
        }
        if (password.length < 8) {
            Toast.makeText(this@SignUpActivity,"Please minimum enter your 8 character ",Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    private fun togglePasswordVisibility() {
        if (isPasswordVisible) {
            binding.etPassword.inputType =
                InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            isPasswordVisible = false
            binding.ivHideShow.setImageResource(R.drawable.ic_eye_hide)
        } else {
            binding.etPassword.inputType =
                InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            isPasswordVisible = true
            binding.ivHideShow.setImageResource(R.drawable.ic_eye_show)
        }
        binding.etPassword.setSelection(binding.etPassword.text.length)
    }
}
