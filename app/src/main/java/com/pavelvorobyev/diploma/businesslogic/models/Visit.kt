package com.pavelvorobyev.diploma.businesslogic.models

import com.google.gson.annotations.SerializedName

data class Visit(

    @SerializedName("id")
    val id: String?,
    @SerializedName("guard_id")
    val guardId: String?,
    @SerializedName("visitor")
    val visitor: Visitor?,
    @SerializedName("date")
    val date: String?,
    @SerializedName("scan_file")
    val scanFile: String

)
