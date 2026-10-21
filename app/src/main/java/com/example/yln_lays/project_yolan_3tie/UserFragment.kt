package com.example.yln_lays.project_yolan_3tie

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import com.example.yln_lays.R
class UserFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user, container, false)
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        val btnLogout = view.findViewById<Button>(R.id.btnLogout)

        btnLogout.setOnClickListener {
            AlertDialog.Builder(requireContext())
                .setTitle("Konfirmasi Logout")
                .setMessage("Apakah kamu yakin ingin keluar?")
                .setNegativeButton("Batal", null)
                .setPositiveButton("Ya") { _, _ ->

                    val prefs = requireContext()
                        .getSharedPreferences(
                            "user_pref",
                            Context.MODE_PRIVATE
                        )

                    prefs.edit()
                        .putBoolean("isLogin", false)
                        .apply()

                    val intent = Intent(
                        requireContext(),
                        SignInActivity::class.java
                    )

                    intent.flags =
                        Intent.FLAG_ACTIVITY_NEW_TASK or
                                Intent.FLAG_ACTIVITY_CLEAR_TASK

                    startActivity(intent)
                }
                .show()
        }
    }
}