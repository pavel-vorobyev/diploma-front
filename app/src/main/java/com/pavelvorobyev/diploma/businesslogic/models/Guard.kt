package com.pavelvorobyev.diploma.businesslogic.models

import com.google.gson.annotations.SerializedName

data class Guard(

    @SerializedName("id")
    val id: String?,
    @SerializedName("name")
    val name: String?

)
