package com.example.yln_lays.project_yolan_3tie.Onboarding

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.yln_lays.databinding.ActivityOnboardingBinding
import com.example.yln_lays.project_yolan_3tie.Home.DashboardActivity
import com.example.yln_lays.project_yolan_3tie.Home.SignInActivity

class OnboardingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOnboardingBinding
    private val pages = listOf(
        OnboardingPage("🍽️", "Selamat Datang di Cafe Jambang", "Nikmati suasana nyaman dengan pilihan makanan dan minuman yang dibuat untuk menemani harimu."),
        OnboardingPage("☕", "Temukan Menu Favorit", "Jelajahi hidangan lezat dan racikan kopi favoritmu dalam satu tempat."),
        OnboardingPage("🛍️", "Pesan dengan Lebih Mudah", "Pilih menu, tambahkan ke keranjang, dan simpan pilihan kesukaanmu kapan saja.")
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.vpOnboarding.adapter = OnboardingAdapter(pages)
        updatePageLabel(0)
        binding.vpOnboarding.registerOnPageChangeCallback(object : androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) { updatePageLabel(position) }
        })
        binding.btnSkipOnboarding.setOnClickListener { finishOnboarding() }
        binding.btnNextOnboarding.setOnClickListener {
            val current = binding.vpOnboarding.currentItem
            if (current < pages.lastIndex) binding.vpOnboarding.currentItem = current + 1
            else finishOnboarding()
        }
    }
    private fun updatePageLabel(position: Int) {
        binding.tvPageIndicator.text = "${position + 1} / ${pages.size}"
        binding.btnNextOnboarding.text = if (position == pages.lastIndex) "Mulai" else "Lanjut"
    }
    private fun finishOnboarding() {
        val isLogin = getSharedPreferences("user_pref", MODE_PRIVATE).getBoolean("isLogin", false)
        startActivity(Intent(this, if (isLogin) DashboardActivity::class.java else SignInActivity::class.java))
        finish()
    }
}
