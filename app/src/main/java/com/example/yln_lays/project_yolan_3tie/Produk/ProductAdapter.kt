package com.example.yln_lays.project_yolan_3tie.Produk

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.yln_lays.databinding.ItemProductBinding

class ProductAdapter(
    private val items: List<ProductModel>,
    private val onOpen: (ProductModel) -> Unit,
    private val onAdd: (ProductModel) -> Unit,
    private val onFavorite: (ProductModel) -> Unit,
    private val showActions: Boolean = true
) : RecyclerView.Adapter<ProductAdapter.ProductHolder>() {
    inner class ProductHolder(val binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHolder {
        return ProductHolder(ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }
    override fun onBindViewHolder(holder: ProductHolder, position: Int) {
        val item = items[position]
        with(holder.binding) {
            ivProductImage.setImageResource(item.imageRes)
            tvProductName.text = item.name
            tvProductDescription.text = item.description
            tvProductPrice.text = item.priceLabel()
            btnProductAdd.visibility = if (showActions) android.view.View.VISIBLE else android.view.View.GONE
            btnProductFavorite.visibility = if (showActions) android.view.View.VISIBLE else android.view.View.GONE
            val isFavorite = ShopRepository.isFavorite(item)
            btnProductFavorite.setImageResource(
                if (isFavorite) com.example.yln_lays.R.drawable.ic_favoritmenu
                else com.example.yln_lays.R.drawable.ic_unfavorit
            )
            btnProductFavorite.contentDescription =
                if (isFavorite) "Hapus dari favorit" else "Tambahkan ke favorit"
            btnProductFavorite.setOnClickListener {
                onFavorite(item)
                val nowFavorite = ShopRepository.isFavorite(item)
                btnProductFavorite.setImageResource(
                    if (nowFavorite) com.example.yln_lays.R.drawable.ic_favoritmenu
                    else com.example.yln_lays.R.drawable.ic_unfavorit
                )
                btnProductFavorite.contentDescription =
                    if (nowFavorite) "Hapus dari favorit" else "Tambahkan ke favorit"
            }
            btnProductAdd.setOnClickListener { onAdd(item) }
            root.setOnClickListener { onOpen(item) }
        }
    }
    override fun getItemCount() = items.size
}
