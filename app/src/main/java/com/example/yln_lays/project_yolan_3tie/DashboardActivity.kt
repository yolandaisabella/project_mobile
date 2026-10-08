package com.example.yln_lays.project_yolan_3tie

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.PopupMenu
import androidx.appcompat.app.AppCompatActivity
import com.example.yln_lays.R
import com.example.yln_lays.databinding.ActivityDashboardBinding

class DashboardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Warna status bar disamakan dengan toolbar
        window.statusBarColor = Color.parseColor("#4B2C20")

        // Icon status bar menjadi putih
        window.decorView.systemUiVisibility = 0

        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Toolbar
        setSupportActionBar(binding.toolbar)

        supportActionBar?.apply {
            title = "Cafe Jambang"
        }

        // Tombol Web View
        binding.btnWebView.setOnClickListener {

            val intent = Intent(
                this,
                WebViewActivity::class.java
            )

            startActivity(intent)
        }

        // Profile Developer
        binding.btnDeveloperProfile.setOnClickListener {

            val popupMenu = PopupMenu(
                this,
                binding.btnDeveloperProfile
            )

            popupMenu.menuInflater.inflate(
                R.menu.developer_menu,
                popupMenu.menu
            )

            popupMenu.setOnMenuItemClickListener { item ->

                when (item.itemId) {

                    R.id.menu_profile -> {

                        val intent = Intent(
                            this,
                            ProfileActivity::class.java
                        )

                        startActivity(intent)

                        true
                    }

                    R.id.menu_settings -> {

                        // Nanti kita isi
                        true
                    }

                    R.id.menu_logout -> {

                        val builder = androidx.appcompat.app.AlertDialog.Builder(this)

                        builder.setTitle("Konfirmasi Logout")
                        builder.setMessage("Apakah kamu yakin ingin logout dari akun ini?")

                        builder.setNegativeButton("Batal") { dialog, _ ->
                            dialog.dismiss()
                        }

                        builder.setPositiveButton("Logout") { _, _ ->

                            val sharedPref = getSharedPreferences(
                                "user_pref",
                                MODE_PRIVATE
                            )

                            sharedPref.edit().clear().apply()

                            val intent = Intent(
                                this,
                                SignInActivity::class.java
                            )

                            intent.flags =
                                Intent.FLAG_ACTIVITY_NEW_TASK or
                                        Intent.FLAG_ACTIVITY_CLEAR_TASK

                            startActivity(intent)
                        }

                        builder.show()

                        true
                    }

                    else -> false
                }
            }

            popupMenu.show()
        }
    }
}