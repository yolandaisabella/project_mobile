package com.example.yln_lays.project_yolan_3tie.Home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.yln_lays.R
class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(
            R.layout.fragment_home,
            container,
            false
        )
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        val btnWebView = view.findViewById<Button>(R.id.btnWebView)

        btnWebView.setOnClickListener {
            val intent = Intent(
                requireContext(),
                WebViewActivity::class.java
            )
            startActivity(intent)
        }
    }
}