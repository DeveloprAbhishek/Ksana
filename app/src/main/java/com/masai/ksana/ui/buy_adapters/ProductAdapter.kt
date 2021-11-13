package com.masai.ksana.ui.buy_adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.masai.ksana.R
import com.masai.ksana.ui.fragment.sell.SellProductList
import kotlinx.android.synthetic.main.product_item_view.view.*

class ProductAdapter(var list: ArrayList<SellProductList>) :
    RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var productName = itemView.tvProductName
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.product_item_view, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.productName.text = list[position].productName
    }

    override fun getItemCount(): Int {
        return list.size
    }

}