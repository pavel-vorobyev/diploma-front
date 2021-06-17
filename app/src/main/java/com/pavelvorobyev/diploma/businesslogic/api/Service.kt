package com.pavelvorobyev.diploma.businesslogic.api

import com.pavelvorobyev.diploma.businesslogic.models.Visit
import com.pavelvorobyev.diploma.businesslogic.models.network.SignInBody
import com.pavelvorobyev.diploma.businesslogic.models.network.SignInResponse
import io.reactivex.rxjava3.core.Observable
import okhttp3.MultipartBody
import retrofit2.http.*

interface Service {

    @POST("/subsystem/guard/login")
    fun login(@Body body: SignInBody): Observable<SignInResponse>

    @Multipart
    @POST("/subsystem/visitor/verify")
    fun visitorVerify(@Part file: MultipartBody.Part): Observable<Visit>

    @GET("/subsystem/guard/visits")
    fun getVisits(): Observable<List<Visit>>
}
