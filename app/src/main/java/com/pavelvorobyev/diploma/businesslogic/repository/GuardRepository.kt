package com.pavelvorobyev.diploma.businesslogic.repository

import android.content.Context
import com.pavelvorobyev.diploma.businesslogic.api.ApiHelper
import com.pavelvorobyev.diploma.businesslogic.models.network.SignInBody
import com.pavelvorobyev.diploma.businesslogic.models.network.SignInResponse
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers

class GuardRepository(context: Context) {

    private val apiHelper = ApiHelper(context)

    fun login(login: String, password: String): Observable<SignInResponse> {
        return apiHelper.apiService.login(SignInBody(login, password))
            .subscribeOn(Schedulers.io())
    }
}
