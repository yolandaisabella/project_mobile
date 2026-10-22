package com.example.yln_lays.project_yolan_3tie.Home

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.yln_lays.R
import com.example.yln_lays.databinding.ActivityVerifyOtpBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class VerifyOtpActivity : AppCompatActivity() {
    private lateinit var binding: ActivityVerifyOtpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityVerifyOtpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val nama = intent.getStringExtra("nama").orEmpty()
        val handphone = intent.getStringExtra("handphone").orEmpty()
        val username = intent.getStringExtra("username").orEmpty()
        val password = intent.getStringExtra("password").orEmpty()

        binding.tvInfo.text =
            "Masukkan nomor handphone sebagai kode OTP: $handphone"

        binding.btnBack.setOnClickListener {
            finish()
        }

        binding.btnVerify.setOnClickListener {
            val otp = binding.etOtp.text.toString().trim()

            if (otp.isEmpty() || otp != handphone) {
                MaterialAlertDialogBuilder(this)
                    .setTitle("Verifikasi Gagal")
                    .setMessage(
                        if (otp.isEmpty()) {
                            "Kode OTP tidak boleh kosong."
                        } else {
                            "Kode OTP tidak sesuai dengan nomor handphone."
                        }
                    )
                    .setPositiveButton("OK", null)
                    .show()

                return@setOnClickListener
            }

            val sharedPref =
                getSharedPreferences("user_pref", MODE_PRIVATE)

            val berhasil = sharedPref.edit()
                .putString("nama", nama)
                .putString("handphone", handphone)
                .putString("registered_username", username)
                .putString("registered_password", password)
                .commit()

            if (!berhasil) {
                MaterialAlertDialogBuilder(this)
                    .setTitle("Gagal")
                    .setMessage("Data registrasi gagal disimpan.")
                    .setPositiveButton("OK", null)
                    .show()

                return@setOnClickListener
            }

            MaterialAlertDialogBuilder(this)
                .setTitle("Registrasi Berhasil")
                .setMessage("Silakan login menggunakan akun baru.")
                .setCancelable(false)
                .setPositiveButton("Login") { _, _ ->
                    val intent = Intent(
                        this,
                        SignInActivity::class.java
                    )

                    intent.flags =
                        Intent.FLAG_ACTIVITY_CLEAR_TOP or
                                Intent.FLAG_ACTIVITY_SINGLE_TOP

                    startActivity(intent)
                    finish()
                }
                .show()
        }
    }
}