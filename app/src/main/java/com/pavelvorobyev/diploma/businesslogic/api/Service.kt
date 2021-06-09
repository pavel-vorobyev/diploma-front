package com.pavelvorobyev.diploma.businesslogic.api

import com.pavelvorobyev.diploma.businesslogic.models.Visit
import com.pavelvorobyev.diploma.businesslogic.models.network.SignInBody
import com.pavelvorobyev.diploma.businesslogic.models.network.SignInResponse
import io.reactivex.rxjava3.core.Observable
import okhttp3.MultipartBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Part

interface Service {

    @POST("/subsystem/guard/login")
    fun login(@Body body: SignInBody): Observable<SignInResponse>

    @POST("/subsystem/visitor/verify")
    fun visitorVerify(@Part file: MultipartBody.Part): Observable<Visit>

    @GET("/subsystem/guard/visits")
    fun getVisits(): Observable<List<Visit>>
}
