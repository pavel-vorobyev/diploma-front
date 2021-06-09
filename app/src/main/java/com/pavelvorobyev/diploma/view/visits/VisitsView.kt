package com.pavelvorobyev.diploma.view.visits

import com.pavelvorobyev.diploma.businesslogic.models.Visit

interface VisitsView {
    fun setItems(visits: List<Visit>)
    fun loadingVisitors()
    fun loadVisitorsFailed()
}
