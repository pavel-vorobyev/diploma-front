package com.pavelvorobyev.diploma.businesslogic.models

import com.google.gson.annotations.SerializedName

data class Visitor(

    @SerializedName("id")
    val id: String?,
    @SerializedName("license")
    val license: String?

)
