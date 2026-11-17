package com.example.yln_lays.project_yolan_3tie.Kopi

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.yln_lays.databinding.ItemCoffeeBinding

class CoffeeAdapter(
    private val items: List<CoffeeModel>,
    private val onAddClick: (CoffeeModel) -> Unit
) : RecyclerView.Adapter<CoffeeAdapter.CoffeeViewHolder>() {
    inner class CoffeeViewHolder(val binding: ItemCoffeeBinding) : RecyclerView.ViewHolder(binding.root)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoffeeViewHolder {
        val binding = ItemCoffeeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CoffeeViewHolder(binding)
    }
    override fun onBindViewHolder(holder: CoffeeViewHolder, position: Int) {
        val coffee = items[position]
        with(holder.binding) {
            tvCoffeeIcon.text = coffee.icon
            tvCoffeeName.text = coffee.name
            tvCoffeeDescription.text = coffee.description
            tvCoffeePrice.text = coffee.price
            btnAddCoffee.setOnClickListener { onAddClick(coffee) }
        }
    }
    override fun getItemCount(): Int = items.size
}
