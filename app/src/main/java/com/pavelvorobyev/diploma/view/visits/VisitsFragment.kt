package com.pavelvorobyev.diploma.view.visits

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.pavelvorobyev.diploma.R
import com.pavelvorobyev.diploma.businesslogic.models.Visit
import com.pavelvorobyev.diploma.extensions.gone
import com.pavelvorobyev.diploma.extensions.visible
import com.pavelvorobyev.diploma.view.visits.adapters.VisitsAdapter
import kotlinx.android.synthetic.main.fragment_visits.messageView
import kotlinx.android.synthetic.main.fragment_visits.progressView
import kotlinx.android.synthetic.main.fragment_visits.visitsContainerView

class VisitsFragment : Fragment(), VisitsView {

    private lateinit var presenter: VisitsPresenter
    private val adapter = VisitsAdapter()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        presenter = VisitsPresenterImpl(this, context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_visits, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        visitsContainerView.adapter = adapter
        presenter.loadVisitors()
    }

    override fun setItems(visits: List<Visit>) {
        if (visits.isEmpty()) {
            progressView.gone()
            visitsContainerView.gone()

            messageView.text = getString(R.string.no_visits_found)
            messageView.visible()
        } else {
            progressView.gone()
            messageView.gone()
            visitsContainerView.visible()

            val items = visits.map { visit -> VisitsAdapter.Item.Data(visit) }
            adapter.updateItems(listOf<VisitsAdapter.Item>(VisitsAdapter.Item.Header) + items)
        }
    }

    override fun loadingVisitors() {
        visitsContainerView.gone()
        messageView.gone()
        progressView.visible()
    }

    override fun loadVisitorsFailed() {
        progressView.gone()
        visitsContainerView.gone()

        messageView.text = getString(R.string.unable_to_load_visits)
        messageView.visible()
    }
}
