package com.example.yln_lays.project_yolan_3tie

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.PopupMenu
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.yln_lays.R
import com.example.yln_lays.databinding.ActivityDashboardBinding

class DashboardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.statusBarColor = getColor(R.color.espresso_brown)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = "Cafe Jambang"

        if (savedInstanceState == null) {
            replaceFragment(HomeFragment())
        }

        binding.bottomNavView.setOnItemSelectedListener { item ->
            when (item.itemId) {

                R.id.nav_home -> {
                    replaceFragment(HomeFragment())
                    supportActionBar?.title = "Cafe Jambang"
                    true
                }

                R.id.nav_cart -> {
                    replaceFragment(CartFragment())
                    supportActionBar?.title = "Cart"
                    true
                }

                R.id.nav_wishlist -> {
                    replaceFragment(WishlistFragment())
                    supportActionBar?.title = "Wishlist"
                    true
                }

                R.id.nav_user -> {
                    replaceFragment(UserFragment())
                    supportActionBar?.title = "Developer Profile"
                    true
                }

                else -> false
            }
        }

        binding.bottomNavView.selectedItemId = R.id.nav_home
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()

        invalidateOptionsMenu()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
        menu.findItem(R.id.action_search)?.isVisible =
            binding.bottomNavView.selectedItemId == R.id.nav_home

        return super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_search -> {
                Toast.makeText(
                    this,
                    "Search dipilih",
                    Toast.LENGTH_SHORT
                ).show()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}