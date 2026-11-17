package com.example.yln_lays

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.yln_lays.databinding.ActivitySplashScreenBinding
import com.example.yln_lays.project_yolan_3tie.Home.DashboardActivity
import com.example.yln_lays.project_yolan_3tie.Home.SignInActivity
import com.example.yln_lays.project_yolan_3tie.Onboarding.OnboardingActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Periksa status login. Jika belum login, onboarding selalu ditampilkan
        // sebelum pengguna masuk ke halaman Sign In.
        val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)
        val isLogin = sharedPref.getBoolean("isLogin", false)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        lifecycleScope.launch {
            delay(2000)

            val destination = if (isLogin) {
                DashboardActivity::class.java
            } else {
                // Pengguna yang belum login melihat 3 halaman Onboarding
                // setiap kali aplikasi dibuka.
                OnboardingActivity::class.java
            }

            startActivity(Intent(this@SplashScreenActivity, destination))

            finish()
        }
    }
}