package com.example.yln_lays.project_yolan_3tie

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.yln_lays.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Tombol Back
        binding.btnBack.setOnClickListener {
            finish()
        }

        // Tombol Create Account
        binding.btnCreateAccount.setOnClickListener {

            val username = binding.etUsername.text.toString()
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()
            val confirmPassword = binding.etConfirmPassword.text.toString()

            if (username.isEmpty()) {
                binding.etUsername.error = "Username harus diisi"
                return@setOnClickListener
            }

            if (email.isEmpty()) {
                binding.etEmail.error = "Email harus diisi"
                return@setOnClickListener
            }

            if (password.isEmpty()) {
                binding.etPassword.error = "Password harus diisi"
                return@setOnClickListener
            }

            if (confirmPassword.isEmpty()) {
                binding.etConfirmPassword.error = "Konfirmasi password harus diisi"
                return@setOnClickListener
            }

            if (password != confirmPassword) {
                binding.etConfirmPassword.error = "Password tidak sama"
                return@setOnClickListener
            }

            // Untuk sementara kembali ke halaman Sign In
            finish()
        }
    }
}