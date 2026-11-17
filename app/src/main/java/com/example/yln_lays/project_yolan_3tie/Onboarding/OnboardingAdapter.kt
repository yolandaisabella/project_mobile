package com.example.yln_lays.project_yolan_3tie.Onboarding

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.yln_lays.R
import com.example.yln_lays.databinding.ItemOnboardingBinding

class OnboardingAdapter(private val pages: List<OnboardingPage>) :
    RecyclerView.Adapter<OnboardingAdapter.PageViewHolder>() {

    inner class PageViewHolder(val binding: ItemOnboardingBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PageViewHolder {
        val binding = ItemOnboardingBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return PageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PageViewHolder, position: Int) {
        val page = pages[position]
        with(holder.binding) {
            tvOnboardingEmoji.text = page.emoji
            tvOnboardingTitle.text = page.title
            tvOnboardingDescription.text = page.description

            val artBackground = when (position) {
                1 -> R.drawable.bg_onboarding_art_2
                2 -> R.drawable.bg_onboarding_art_3
                else -> R.drawable.bg_onboarding_art_1
            }
            tvOnboardingEmoji.setBackgroundResource(artBackground)

            tvOnboardingTag.text = when (position) {
                0 -> "WELCOME TO CAFE JAMBANG"
                1 -> "FRESHLY MADE FOR YOU"
                else -> "YOUR FAVORITE, ONE TAP AWAY"
            }
        }
    }

    override fun getItemCount(): Int = pages.size
}
