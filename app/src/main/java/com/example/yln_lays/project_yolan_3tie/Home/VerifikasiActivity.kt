package com.example.yln_lays.project_yolan_3tie.Home

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.yln_lays.databinding.ActivityVerifikasiBinding

class VerifikasiActivity : AppCompatActivity() {

    private lateinit var binding: ActivityVerifikasiBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityVerifikasiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener {
            finish()
        }

        binding.btnKembali.setOnClickListener {
            finish()
        }

        tampilkanData()

        binding.btnSubmit.setOnClickListener {
            Toast.makeText(
                this,
                "Registrasi Berhasil! Silakan login dengan akun kamu.",
                Toast.LENGTH_LONG
            ).show()

            val intent = Intent(this, SignInActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            finish()
        }
    }

    private fun tampilkanData() {
        val pref = getSharedPreferences(
            "user_pref",
            MODE_PRIVATE
        )

        binding.tvNama.text = "Nama: ${pref.getString("nama", "-")}"
        binding.tvEmail.text = "Email: ${pref.getString("email", "-")}"
        binding.tvTanggalLahir.text = "Tanggal Lahir: ${pref.getString("tanggal_lahir", "-")}"
        binding.tvJenisKelamin.text = "Jenis Kelamin: ${pref.getString("jenis_kelamin", "-")}"
        binding.tvUsername.text = "Username: ${pref.getString("registered_username", "-")}"
    }
}