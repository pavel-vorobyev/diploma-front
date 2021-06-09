package com.pavelvorobyev.diploma.businesslogic.models.network

import com.google.gson.annotations.SerializedName
import com.pavelvorobyev.diploma.businesslogic.models.Guard

data class SignInResponse(

    @SerializedName("token")
    val token: String,
    @SerializedName("guard")
    val guard: Guard

)
