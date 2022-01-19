package com.asterisk.shoppingapp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.asterisk.shoppingapp.R
import com.asterisk.shoppingapp.data.entities.ShoppingItem
import com.asterisk.shoppingapp.ui.shoppinglist.ShoppingViewModel
import kotlinx.android.synthetic.main.list_item.view.*

class ShoppingAdapter(
    var items: List<ShoppingItem>,
    private val viewModel: ShoppingViewModel
) : RecyclerView.Adapter<ShoppingAdapter.ShoppingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.list_item, parent, false
        )

        return ShoppingViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShoppingViewHolder, position: Int) {
        val currentItem = items[position]
        holder.bind(currentItem)

        holder.itemView.iv_delete.setOnClickListener {
            viewModel.delete(currentItem)
        }

        holder.itemView.iv_plus.setOnClickListener {
            currentItem.amount++
            viewModel.upsert(currentItem)
        }

        holder.itemView.iv_minus.setOnClickListener {
            if (currentItem.amount > 0) {
                currentItem.amount--
                viewModel.upsert(currentItem)
            }
        }
    }

    override fun getItemCount(): Int = items.size

    inner class ShoppingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name = itemView.findViewById<TextView>(R.id.tv_name)
        val amount = itemView.findViewById<TextView>(R.id.tv_amount)

        fun bind(currentItem: ShoppingItem) {
            name.text = currentItem.name
            amount.text = "${currentItem.amount}"
        }

    }
}