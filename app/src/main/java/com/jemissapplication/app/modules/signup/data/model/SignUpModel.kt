package com.jemissapplication.app.modules.signup.`data`.model

import com.jemissapplication.app.R
import com.jemissapplication.app.appcomponents.di.MyApp
import kotlin.String

data class SignUpModel(

  var txtSignup: String? = MyApp.getInstance().resources.getString(R.string.lbl_sign_up)
  ,

  var txtConfirmation: String? =
      MyApp.getInstance().resources.getString(R.string.msg_already_have_an)
  ,

  var txtLogin: String? = MyApp.getInstance().resources.getString(R.string.lbl_log_in)
  ,

  var etNameValue: String? = null,

  var etEmailValue: String? = null,

  var etPasswordValue: String? = null
)
