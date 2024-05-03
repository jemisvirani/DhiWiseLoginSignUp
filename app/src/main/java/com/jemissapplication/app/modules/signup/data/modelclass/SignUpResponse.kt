package com.eclatsol.kotlinaapi.model

import com.google.gson.annotations.SerializedName

data class SignUpResponse(

	@field:SerializedName("data")
	val data: Any? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)
