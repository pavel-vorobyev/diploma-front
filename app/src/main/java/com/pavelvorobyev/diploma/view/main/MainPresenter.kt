package com.pavelvorobyev.diploma.view.main

import android.content.Context
import com.pavelvorobyev.diploma.businesslogic.repository.VisitorRepository
import com.pavelvorobyev.diploma.businesslogic.store.Store
import com.pavelvorobyev.diploma.util.extensions.Empty
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody.Part
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import pl.aprilapps.easyphotopicker.MediaFile


class MainPresenterImpl(
    private val view: MainView,
    context: Context
) : MainPresenter {

    private val store = Store(context)
    private val visitorRepository = VisitorRepository(context)

    override fun checkSession() {
        if (store.token == String.Empty) {
            view.loggedOut()
        }
    }

    override fun verifyVisitor(files: Array<MediaFile>) {
        if (files.isNotEmpty()) {
            val file = files[0].file
            val requestFile = file.asRequestBody("multipart/form-data".toMediaTypeOrNull())
            val body = Part.createFormData("file", file.name, requestFile)

            visitorRepository.verifyVisitor(body)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { view.showProgress(true) }
                .doFinally { view.showProgress(false) }
                .subscribe(
                    {

                    },
                    {

                    }
                )
        }
    }
}

interface MainPresenter {
    fun checkSession()
    fun verifyVisitor(files: Array<MediaFile>)
}
