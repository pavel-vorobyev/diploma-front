package com.pavelvorobyev.diploma.view.visits

import android.content.Context
import com.pavelvorobyev.diploma.businesslogic.repository.VisitorRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers

class VisitsPresenterImpl(
    private val view: VisitsView,
    context: Context
) : VisitsPresenter {

    private val visitorRepository = VisitorRepository(context)

    override fun loadVisitors() {
        visitorRepository.getVisits()
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { view.loadingVisitors() }
            .subscribe(
                { response ->
                    view.setItems(response.orEmpty())
                },
                {
                    view.loadVisitorsFailed()
                }
            )
    }
}

interface VisitsPresenter {
    fun loadVisitors()
}
