package com.example.yln_lays.project_yolan_3tie.Home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.GridView
import android.widget.SimpleAdapter
import com.example.yln_lays.R
class HomeFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(R.layout.fragment_home, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.btnWebView).setOnClickListener {
            startActivity(Intent(requireContext(), WebViewActivity::class.java))
        }

        // SimpleAdapter menampilkan kategori dalam bentuk grid.
        val categories = listOf(
            mapOf("categoryIcon" to R.drawable.ic_food, "categoryName" to "Makanan"),
            mapOf("categoryIcon" to R.drawable.ic_drink, "categoryName" to "Minuman"),
            mapOf("categoryIcon" to R.drawable.ic_cart, "categoryName" to "Cart"),
            mapOf("categoryIcon" to R.drawable.ic_wishlist, "categoryName" to "Favorit")
        )
        val adapter = SimpleAdapter(
            requireContext(), categories, R.layout.item_home_category,
            arrayOf("categoryIcon", "categoryName"),
            intArrayOf(R.id.tvCategoryIcon, R.id.tvCategoryName)
        )
        view.findViewById<GridView>(R.id.gvCategories).adapter = adapter
        view.findViewById<GridView>(R.id.gvCategories).setOnItemClickListener { _, _, position, _ ->
            when (position) {
                0 -> (activity as? DashboardActivity)?.openMenu("Makanan")
                1 -> (activity as? DashboardActivity)?.openMenu("Minuman")
                2 -> (activity as? DashboardActivity)?.openCart()
                3 -> (activity as? DashboardActivity)?.openFavorites()
            }
        }
    }
}