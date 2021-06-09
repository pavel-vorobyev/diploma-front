package com.pavelvorobyev.diploma.businesslogic.models.network

import com.google.gson.annotations.SerializedName

data class SignInBody(

    @SerializedName("login")
    val login: String,
    @SerializedName("password")
    val password: String

)
