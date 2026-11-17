package com.example.yln_lays.project_yolan_3tie.Cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.yln_lays.R
import com.example.yln_lays.databinding.FragmentCartBinding
import com.example.yln_lays.project_yolan_3tie.Home.DashboardActivity

class CartFragment : Fragment() {
    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View { _binding = FragmentCartBinding.inflate(inflater, container, false); return binding.root }
    override fun onResume() { super.onResume(); renderCart() }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) { super.onViewCreated(view, savedInstanceState); binding.rvCart.layoutManager = LinearLayoutManager(requireContext()); renderCart() }
    private fun renderCart() {
        val entries = ShopRepository.cart.values.toList()
        binding.tvEmptyCart.visibility = if (entries.isEmpty()) View.VISIBLE else View.GONE
        binding.rvCart.visibility = if (entries.isEmpty()) View.GONE else View.VISIBLE
        binding.rvCart.adapter = CartAdapter(entries) { id, delta -> ShopRepository.changeQuantity(id, delta); renderCart(); (activity as? DashboardActivity)?.refreshCartBadge() }
        binding.tvCartTotal.text = "Total: Rp" + String.format("%,d", ShopRepository.totalPrice()).replace(',', '.')
        (activity as? DashboardActivity)?.refreshCartBadge()
    }
    override fun onDestroyView() { super.onDestroyView(); _binding = null }
}