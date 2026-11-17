package com.example.yln_lays.project_yolan_3tie.Cart

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.yln_lays.databinding.ItemCartBinding
import com.example.yln_lays.project_yolan_3tie.Produk.CartEntry

class CartAdapter(private val items: List<CartEntry>, private val onChange: (String, Int) -> Unit) : RecyclerView.Adapter<CartAdapter.Holder>() {
    inner class Holder(val b: ItemCartBinding) : RecyclerView.ViewHolder(b.root)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = Holder(ItemCartBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    override fun onBindViewHolder(holder: Holder, position: Int) {
        val entry = items[position]
        with(holder.b) {
            ivCartImage.setImageResource(entry.product.imageRes); tvCartName.text = entry.product.name
            tvCartPrice.text = "${entry.product.priceLabel()}  •  ${entry.quantity} item"
            tvCartQuantity.text = entry.quantity.toString()
            btnCartMinus.setOnClickListener { onChange(entry.product.id, -1) }
            btnCartPlus.setOnClickListener { onChange(entry.product.id, 1) }
        }
    }
    override fun getItemCount() = items.size
}
