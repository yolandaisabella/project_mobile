package com.example.yln_lays.project_yolan_3tie.Produk

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.yln_lays.R
import com.example.yln_lays.databinding.ActivityProductDetailBinding
import com.example.yln_lays.project_yolan_3tie.Home.DashboardActivity

class ProductDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProductDetailBinding
    private lateinit var product: ProductModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val id = intent.getStringExtra(EXTRA_ID)
        product = ShopRepository.products.firstOrNull { it.id == id } ?: run { finish(); return }
        binding.btnDetailBack.setOnClickListener { finish() }
        binding.ivDetailImage.setImageResource(product.imageRes)
        binding.tvDetailName.text = product.name
        binding.tvDetailCategory.text = product.category
        binding.tvDetailDescription.text = product.description
        binding.tvDetailPrice.text = product.priceLabel()
        updateFavoriteButton()
        binding.btnDetailFavorite.setOnClickListener {
            val added = ShopRepository.toggleFavorite(product)
            updateFavoriteButton()
            Toast.makeText(this, if (added) "Ditambahkan ke Favorit" else "Dihapus dari Favorit", Toast.LENGTH_SHORT).show()
        }
        binding.btnDetailAddCart.setOnClickListener {
            ShopRepository.addToCart(product)
            Toast.makeText(this, "${product.name} ditambahkan ke keranjang", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
    private fun updateFavoriteButton() {
        val isFavorite = ShopRepository.isFavorite(product)
        binding.btnDetailFavorite.text = "Favorit"
        val icon = getDrawable(
            if (isFavorite) R.drawable.ic_favoritmenu else R.drawable.ic_unfavorit
        )
        icon?.setBounds(0, 0, 48, 48)
        binding.btnDetailFavorite.setCompoundDrawables(icon, null, null, null)
        binding.btnDetailFavorite.compoundDrawablePadding = 10
        binding.btnDetailFavorite.contentDescription =
            if (isFavorite) "Hapus dari favorit" else "Tambahkan ke favorit"
    }
    companion object { const val EXTRA_ID = "product_id" }
}
