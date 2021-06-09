package com.pavelvorobyev.diploma.businesslogic.repository

import android.content.Context
import com.pavelvorobyev.diploma.businesslogic.api.ApiHelper
import com.pavelvorobyev.diploma.businesslogic.models.Visit
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import okhttp3.MultipartBody

class VisitorRepository(context: Context) {

    private val apiHelper = ApiHelper(context)

    fun verifyVisitor(file: MultipartBody.Part): Observable<Visit> {
        return apiHelper.apiService.visitorVerify(file)
            .subscribeOn(Schedulers.io())
    }

    fun getVisits(): Observable<List<Visit>> {
        return apiHelper.apiService.getVisits()
            .subscribeOn(Schedulers.io())
    }
}
