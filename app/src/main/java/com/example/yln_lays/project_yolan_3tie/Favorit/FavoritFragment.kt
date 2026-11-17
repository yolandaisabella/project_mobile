package com.example.yln_lays.project_yolan_3tie.Favorit

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.yln_lays.R
import com.example.yln_lays.databinding.FragmentFavoritBinding
import com.example.yln_lays.project_yolan_3tie.Produk.ProductAdapter
import com.example.yln_lays.project_yolan_3tie.Produk.ProductDetailActivity
import com.example.yln_lays.project_yolan_3tie.Produk.ShopRepository

class FavoritFragment : Fragment() {
    private var _binding: FragmentFavoritBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View { _binding = FragmentFavoritBinding.inflate(inflater, container, false); return binding.root }
    override fun onResume() { super.onResume(); renderFavorites() }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) { super.onViewCreated(view, savedInstanceState); binding.rvFavorites.layoutManager = GridLayoutManager(requireContext(), 2); renderFavorites() }
    private fun renderFavorites() {
        val items = ShopRepository.favorites.values.toList()
        binding.tvEmptyWishlist.visibility = if (items.isEmpty()) View.VISIBLE else View.GONE
        binding.rvFavorites.visibility = if (items.isEmpty()) View.GONE else View.VISIBLE
        binding.rvFavorites.adapter = ProductAdapter(items,
            onOpen = { startActivity(Intent(requireContext(), ProductDetailActivity::class.java).putExtra(ProductDetailActivity.EXTRA_ID, it.id)) },
            onAdd = {}, onFavorite = { ShopRepository.toggleFavorite(it); renderFavorites() }, showActions = false)
    }
    override fun onDestroyView() { super.onDestroyView(); _binding = null }
}