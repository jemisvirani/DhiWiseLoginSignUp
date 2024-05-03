package com.eclatsol.kotlinaapi.model

import com.google.gson.annotations.SerializedName

data class SignUpRequest(

	@field:SerializedName("username")
	val username: String? = null,

	@field:SerializedName("password")
	val password: String? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("profile")
	val profile: String? = null,

	@field:SerializedName("role")
	val role: Int? = null

)
