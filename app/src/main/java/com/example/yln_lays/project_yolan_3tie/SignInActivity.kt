package com.example.yln_lays.project_yolan_3tie

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.yln_lays.databinding.ActivitySignInBinding

class SignInActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Tombol Back
        binding.btnBack.setOnClickListener {
            finish()
        }

        // Tombol Sign In
        binding.btnSignIn.setOnClickListener {

            val username = binding.etUsername.text.toString()
            val password = binding.etPassword.text.toString()

            if (username.isEmpty()) {
                binding.etUsername.error = "Username harus diisi"
                return@setOnClickListener
            }
            if (password.isEmpty()) {
                binding.etPassword.error = "Password harus diisi"
                return@setOnClickListener
            }

            val intent = Intent(this, DashboardActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}