package com.example.yln_lays.project_yolan_3tie.Home

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.yln_lays.R
import com.example.yln_lays.databinding.ActivitySignUpBinding
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointBackward
import com.google.android.material.datepicker.MaterialDatePicker
import java.util.Calendar
import java.util.Locale
import java.util.TimeZone

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    private var tanggalLahir = ""
    private var tanggalLahirMillis: Long? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener {
            finish()
        }

        // DATE PICKER TANGGAL LAHIR
        binding.btnTanggalLahir.setOnClickListener {

            val hariIni = MaterialDatePicker.todayInUtcMilliseconds()

            val constraints = CalendarConstraints.Builder()
                .setEnd(hariIni)
                .setValidator(
                    DateValidatorPointBackward.before(hariIni)
                )
                .build()

            val datePicker = MaterialDatePicker.Builder.datePicker()
                .setTitleText("Pilih Tanggal Lahir")
                .setCalendarConstraints(constraints)
                .setSelection(tanggalLahirMillis ?: hariIni)
                .build()

            datePicker.addOnPositiveButtonClickListener { selection ->

                val batasTanggal =
                    MaterialDatePicker.todayInUtcMilliseconds()

                // Validasi tanggal setelah dipilih
                if (selection > batasTanggal) {
                    Toast.makeText(
                        this,
                        "Tanggal lahir tidak boleh di masa depan",
                        Toast.LENGTH_SHORT
                    ).show()

                    return@addOnPositiveButtonClickListener
                }

                val calendar = Calendar.getInstance(
                    TimeZone.getTimeZone("UTC")
                )

                calendar.timeInMillis = selection

                val formattedDate = String.format(
                    Locale.getDefault(),
                    "%02d/%02d/%04d",
                    calendar.get(Calendar.DAY_OF_MONTH),
                    calendar.get(Calendar.MONTH) + 1,
                    calendar.get(Calendar.YEAR)
                )

                tanggalLahirMillis = selection
                tanggalLahir = formattedDate
                binding.btnTanggalLahir.text = formattedDate
            }

            datePicker.show(
                supportFragmentManager,
                "DATE_PICKER"
            )
        }

        // TOMBOL PENDAFTARAN
        binding.btnCreateAccount.setOnClickListener {

            val nama = binding.etNama.text.toString().trim()
            val email = binding.etEmail.text.toString().trim()

            val jenisKelamin = when (
                binding.rgJenisKelamin.checkedRadioButtonId
            ) {
                R.id.rbLakiLaki -> "Laki-laki"
                R.id.rbPerempuan -> "Perempuan"
                else -> ""
            }

            if (nama.isEmpty()) {
                binding.etNama.error = "Nama wajib diisi"
                return@setOnClickListener
            }

            if (email.isEmpty()) {
                binding.etEmail.error = "Email wajib diisi"
                return@setOnClickListener
            }

            val tanggalDipilih = tanggalLahirMillis

            if (tanggalDipilih == null || tanggalLahir.isEmpty()) {
                Toast.makeText(
                    this,
                    "Pilih tanggal lahir terlebih dahulu",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            // Validasi ulang sebelum pendaftaran diproses
            val batasTanggal =
                MaterialDatePicker.todayInUtcMilliseconds()

            if (tanggalDipilih > batasTanggal) {
                Toast.makeText(
                    this,
                    "Tanggal lahir tidak boleh di masa depan",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            if (jenisKelamin.isEmpty()) {
                Toast.makeText(
                    this,
                    "Pilih jenis kelamin terlebih dahulu",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            val pref = getSharedPreferences(
                "user_pref",
                MODE_PRIVATE
            )

            pref.edit()
                .putString("nama", nama)
                .putString("email", email)
                .putString("tanggal_lahir", tanggalLahir)
                .putString("jenis_kelamin", jenisKelamin)
                .apply()

            startActivity(
                Intent(this, SignUpAccountActivity::class.java)
            )
        }
    }
}