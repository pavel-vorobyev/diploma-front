package com.pavelvorobyev.diploma.view.visits.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.pavelvorobyev.diploma.R
import com.pavelvorobyev.diploma.businesslogic.models.Visit
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
            holder.itemView.licenseView.text = item.data.visitor?.license.orEmpty()
            holder.itemView.dateView.text = item.data.date.orEmpty()

            holder.itemView.photoView.load("http://192.168.0.102:9173/${item.data.scanFile}") {
                transformations(RoundedCornersTransformation(18f))
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
