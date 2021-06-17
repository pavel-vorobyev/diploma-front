package com.pavelvorobyev.diploma.view.visits.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.pavelvorobyev.diploma.R
import com.pavelvorobyev.diploma.businesslogic.models.Visit
import com.pavelvorobyev.diploma.util.DateUtils
import com.pavelvorobyev.diploma.util.extensions.gone
import kotlinx.android.synthetic.main.view_item_visit.view.badgeView
import kotlinx.android.synthetic.main.view_item_visit.view.dateView
import kotlinx.android.synthetic.main.view_item_visit.view.licenseView
import kotlinx.android.synthetic.main.view_item_visit.view.photoView

class VisitsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: List<Item> = emptyList()

    class HeaderViewHolder(view: View) : RecyclerView.ViewHolder(view)
    class DataViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            0 -> HeaderViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.view_item_visit_header, parent, false)
            )
            else -> DataViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.view_item_visit, parent, false)
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]

        if (item is Item.Data && holder is DataViewHolder) {
            holder.itemView.let {
                it.dateView.text = DateUtils.formatVisitDate(item.data.date.orEmpty())
                it.photoView.load("http://192.168.0.102:9173/${item.data.scanFile}") {
                    transformations(RoundedCornersTransformation(18f))
                }

                when (item.data.isAllowed) {
                    true -> {
                        it.licenseView.text = item.data.visitor?.license.orEmpty()
                        it.licenseView.setTextColor(it.context.getColor(R.color.color_text_primary))
                        it.badgeView.background =
                            AppCompatResources.getDrawable(
                                holder.itemView.context,
                                R.drawable.bg_badge_green
                            )
                        it.badgeView.text = holder.itemView.context.getString(R.string.allowed)
                    }
                    false -> {
                        it.licenseView.text = it.context.getString(R.string.license_unknown)
                        it.licenseView.setTextColor(it.context.getColor(R.color.color_danger))
                        it.badgeView.background =
                            AppCompatResources.getDrawable(it.context, R.drawable.bg_badge_red)
                        it.badgeView.text = it.context.getString(R.string.not_allowed)
                    }
                    else -> {
                        holder.itemView.badgeView.gone()
                    }
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            Item.Header -> 0
            is Item.Data -> 1
        }
    }

    fun updateItems(items: List<Item>) {
        this.items = items
        notifyDataSetChanged()
    }

    sealed class Item {
        object Header : Item()
        data class Data(val data: Visit) : Item()
    }
}
