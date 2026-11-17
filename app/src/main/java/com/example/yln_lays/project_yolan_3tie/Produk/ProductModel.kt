package com.example.yln_lays.project_yolan_3tie.Produk

import androidx.annotation.DrawableRes
import com.example.yln_lays.R

data class ProductModel(
    val id: String,
    val name: String,
    val description: String,
    val price: Int,
    val icon: String,
    val category: String,
    @DrawableRes val imageRes: Int
) {
    fun priceLabel(): String = "Rp" + String.format("%,d", price).replace(',', '.')
}

data class CartEntry(val product: ProductModel, var quantity: Int = 1)

object ShopRepository {
    val products = listOf(
        ProductModel("espresso", "Espresso", "Kopi espresso dengan crema lembut, aroma biji kopi yang kuat, dan rasa khas.", 18000, "☕", "Minuman", R.drawable.drink_espresso),
        ProductModel("americano", "Americano", "Espresso yang dipadukan dengan air, menghasilkan rasa kopi yang ringan dan menyegarkan.", 20000, "🧊", "Minuman", R.drawable.drink_americano),
        ProductModel("cappuccino", "Cappuccino", "Perpaduan espresso, susu, dan foam lembut yang seimbang.", 25000, "🥛", "Minuman", R.drawable.drink_cappuccino),
        ProductModel("latte", "Cafe Latte", "Espresso dan susu creamy dengan tekstur lembut serta latte art.", 25000, "🤎", "Minuman", R.drawable.drink_cafe_latte),
        ProductModel("caramel", "Caramel Macchiato", "Kopi susu dengan sentuhan saus karamel yang manis.", 29000, "🍮", "Minuman", R.drawable.drink_caramel_macchiato),
        ProductModel("mocha", "Mocha", "Kombinasi espresso dan cokelat dengan rasa manis yang nikmat.", 27000, "🍫", "Minuman", R.drawable.drink_mocha),

        ProductModel("kwetiau-udang", "Kwetiau Goreng Udang", "Kwetiau goreng dengan udang, sayuran, dan bumbu gurih khas Jambang Space.", 28000, "🍤", "Makanan", R.drawable.menu_kwetiau_udang),
        ProductModel("nasi-goreng-seafood", "Nasi Goreng Seafood", "Nasi goreng dengan cita rasa smoky dan gurih, dilengkapi udang serta telur orak-arik.", 29000, "🍚", "Makanan", R.drawable.menu_nasi_goreng_seafood),
        ProductModel("nasi-ayam-bumbu", "Nasi Ayam Bumbu", "Nasi hangat dengan ayam berbumbu, sambal, dan pelengkap sayuran.", 31000, "🍗", "Makanan", R.drawable.menu_nasi_ayam_bumbu),
        ProductModel("nasi-goreng-ayam", "Nasi Goreng Ayam", "Nasi goreng ayam dengan sayuran segar, kerupuk, dan sambal.", 29000, "🍛", "Makanan", R.drawable.menu_nasi_goreng_ayam),
        ProductModel("mie-pangsit-udang", "Mie Pangsit Goreng Udang", "Mie goreng dengan udang dan bumbu gurih yang menggugah selera.", 29000, "🍜", "Makanan", R.drawable.menu_mie_pangsit_goreng_udang),
        ProductModel("mie-pangsit-ayam", "Mie Pangsit Ayam Kari", "Mie kuah dengan ayam, pangsit, telur, dan kuah kari gurih.", 32000, "🍜", "Makanan", R.drawable.menu_mie_pangsit_ayam_kari)
    )
    val cart = linkedMapOf<String, CartEntry>()
    val favorites = linkedMapOf<String, ProductModel>()

    fun addToCart(product: ProductModel) {
        val entry = cart[product.id]
        if (entry == null) cart[product.id] = CartEntry(product) else entry.quantity++
    }
    fun cartCount(): Int = cart.values.sumOf { it.quantity }
    fun toggleFavorite(product: ProductModel): Boolean {
        return if (favorites.containsKey(product.id)) { favorites.remove(product.id); false }
        else { favorites[product.id] = product; true }
    }
    fun isFavorite(product: ProductModel) = favorites.containsKey(product.id)
    fun changeQuantity(id: String, delta: Int) {
        val entry = cart[id] ?: return
        entry.quantity += delta
        if (entry.quantity <= 0) cart.remove(id)
    }
    fun totalPrice(): Int = cart.values.sumOf { it.product.price * it.quantity }
}
