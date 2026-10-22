package com.example.yln_lays.project_yolan_3tie.Home

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.yln_lays.databinding.ActivitySignUpBinding
import kotlin.jvm.java

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener {
            finish()
        }

        binding.btnCreateAccount.setOnClickListener {
            val nama = binding.etNama.text.toString().trim()
            val handphone = binding.etHandphone.text.toString().trim()
            val username = binding.etUsername.text.toString().trim()
            val password = binding.etPassword.text.toString()

            if (nama.isEmpty()) {
                binding.etNama.error = "Nama harus diisi"
                return@setOnClickListener
            }

            if (handphone.isEmpty()) {
                binding.etHandphone.error = "Nomor handphone harus diisi"
                return@setOnClickListener
            }

            if (username.isEmpty()) {
                binding.etUsername.error = "Username harus diisi"
                return@setOnClickListener
            }

            if (password.isEmpty()) {
                binding.etPassword.error = "Password harus diisi"
                return@setOnClickListener
            }

            val intent = Intent(this, VerifyOtpActivity::class.java)
            intent.putExtra("nama", nama)
            intent.putExtra("handphone", handphone)
            intent.putExtra("username", username)
            intent.putExtra("password", password)

            startActivity(intent)
        }
    }
}