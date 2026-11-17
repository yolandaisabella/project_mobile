package com.example.yln_lays.project_yolan_3tie.Kopi

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.yln_lays.R
import com.example.yln_lays.databinding.FragmentKopiBinding
import com.example.yln_lays.project_yolan_3tie.Home.DashboardActivity
import com.example.yln_lays.project_yolan_3tie.Produk.ProductAdapter
import com.example.yln_lays.project_yolan_3tie.Produk.ProductDetailActivity
import com.example.yln_lays.project_yolan_3tie.Produk.ShopRepository

class KopiFragment : Fragment() {
    private var _binding: FragmentKopiBinding? = null
    private val binding get() = _binding!!
    private var selectedCategory: String = "Makanan"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentKopiBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        selectedCategory = when (arguments?.getString(ARG_CATEGORY)) {
            "Minuman" -> "Minuman"
            "Snack" -> "Snack"
            else -> "Makanan"
        }

        binding.rvCoffee.layoutManager = GridLayoutManager(requireContext(), 2)

        binding.tvTabFood.setOnClickListener { showCategory("Makanan") }
        binding.tvTabDrink.setOnClickListener { showCategory("Minuman") }
        binding.tvTabSnack.setOnClickListener { showCategory("Snack") }

        showCategory(selectedCategory)
    }

    private fun showCategory(category: String) {
        selectedCategory = category
        updateTabAppearance()
        binding.tvMenuHeading.text = category
        binding.tvMenuSubheading.text = when (category) {
            "Makanan" -> "Pilihan makanan favoritmu."
            "Minuman" -> "Pilihan minuman segar dan kopi favoritmu."
            "Snack" -> "Camilan ringan untuk menemani waktumu."
            else -> "Pilih menu favoritmu."
        }

        val items = ShopRepository.products.filter { it.category == category }
        val isEmpty = items.isEmpty()

        binding.rvCoffee.visibility = if (isEmpty) View.GONE else View.VISIBLE
        binding.tvEmptyCategory.visibility = if (isEmpty) View.VISIBLE else View.GONE

        binding.tvEmptyCategory.text = when (category) {
            "Snack" -> "Menu snack akan segera hadir.\nTunggu pilihan snack berikutnya, ya!"
            else -> "Belum ada menu pada kategori ini."
        }

        binding.rvCoffee.adapter = ProductAdapter(
            items,
            onOpen = {
                startActivity(
                    Intent(requireContext(), ProductDetailActivity::class.java)
                        .putExtra(ProductDetailActivity.EXTRA_ID, it.id)
                )
            },
            onAdd = {
                ShopRepository.addToCart(it)
                (activity as? DashboardActivity)?.refreshCartBadge()
                Toast.makeText(requireContext(), "${it.name} masuk keranjang", Toast.LENGTH_SHORT).show()
            },
            onFavorite = {
                val added = ShopRepository.toggleFavorite(it)
                Toast.makeText(
                    requireContext(),
                    if (added) "Masuk Favorit" else "Dihapus dari Favorit",
                    Toast.LENGTH_SHORT
                ).show()
            }
        )
    }

    private fun updateTabAppearance() {
        val tabs = listOf(
            binding.tvTabFood to "Makanan",
            binding.tvTabDrink to "Minuman",
            binding.tvTabSnack to "Snack"
        )

        tabs.forEach { (tab, category) ->
            val selected = selectedCategory == category
            tab.setBackgroundResource(
                if (selected) R.drawable.bg_menu_tab_selected
                else R.drawable.bg_menu_tab_unselected
            )
            tab.setTextColor(
                requireContext().getColor(
                    if (selected) R.color.white else R.color.espresso_brown
                )
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val ARG_CATEGORY = "category"

        fun newInstance(category: String) = KopiFragment().apply {
            arguments = Bundle().apply { putString(ARG_CATEGORY, category) }
        }
    }
}
