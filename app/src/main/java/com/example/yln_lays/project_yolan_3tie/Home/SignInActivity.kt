package com.example.yln_lays.project_yolan_3tie.Home

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.yln_lays.databinding.ActivitySignInBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class SignInActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPref =
            getSharedPreferences("user_pref", MODE_PRIVATE)

        binding.btnBack.setOnClickListener {
            finish()
        }

        binding.tvCreateAccount.setOnClickListener {
            startActivity(
                Intent(this, SignUpActivity::class.java)
            )
        }

        binding.btnSignIn.setOnClickListener {
            val username =
                binding.etUsername.text.toString().trim()
            val password =
                binding.etPassword.text.toString()

            if (username.isEmpty()) {
                binding.etUsername.error = "Username harus diisi"
                return@setOnClickListener
            }

            if (password.isEmpty()) {
                binding.etPassword.error = "Password harus diisi"
                return@setOnClickListener
            }

            val registeredUsername =
                sharedPref.getString("registered_username", null)
            val registeredPassword =
                sharedPref.getString("registered_password", null)

            val loginDefault = username == password

            val loginRegistered =
                registeredUsername != null &&
                        registeredPassword != null &&
                        username == registeredUsername &&
                        password == registeredPassword

            if (loginDefault || loginRegistered) {
                sharedPref.edit()
                    .putBoolean("isLogin", true)
                    .putString("username", username)
                    .apply()

                startActivity(
                    Intent(this, DashboardActivity::class.java)
                )

                finish()
            } else {
                MaterialAlertDialogBuilder(this)
                    .setTitle("Login Gagal")
                    .setMessage("Username atau password salah.")
                    .setPositiveButton("OK", null)
                    .show()
            }
        }
    }
}