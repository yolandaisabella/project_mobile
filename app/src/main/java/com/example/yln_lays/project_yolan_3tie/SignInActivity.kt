package com.example.yln_lays.project_yolan_3tie

import android.content.Intent
import android.os.Bundle
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

        // SharedPreferences
        val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)

        // Tombol Back
        binding.btnBack.setOnClickListener {
            finish()
        }

        // Tombol Sign In
        binding.btnSignIn.setOnClickListener {

            val username = binding.etUsername.text.toString()
            val password = binding.etPassword.text.toString()

            // Cek username kosong
            if (username.isEmpty()) {
                binding.etUsername.error = "Username harus diisi"
                return@setOnClickListener
            }

            // Cek password kosong
            if (password.isEmpty()) {
                binding.etPassword.error = "Password harus diisi"
                return@setOnClickListener
            }

            // Cek username dan password
            if (username == password) {

                // Simpan status login
                val editor = sharedPref.edit()
                editor.putBoolean("isLogin", true)
                editor.putString("username", username)
                editor.apply()

                // Login berhasil → Dashboard
                val intent = Intent(
                    this,
                    DashboardActivity::class.java
                )

                startActivity(intent)
                finish()

            } else {

                // Username dan password berbeda
                binding.etPassword.error =
                    "Username dan password harus sama"
            }
        }
    }
}