package com.example.yln_lays.project_yolan_3tie.Home

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.yln_lays.R
import com.example.yln_lays.databinding.ActivitySignUpAccountBinding

class SignUpAccountActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpAccountBinding
    private var isPasswordVisible = false
    private var isConfirmPasswordVisible = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivitySignUpAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener {
            finish()
        }

        setupPasswordToggle()

        binding.etPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                checkPasswordStrength(s?.toString().orEmpty())
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        binding.btnNext.setOnClickListener {
            val username = binding.etUsername.text.toString().trim()
            val password = binding.etPassword.text.toString()
            val confirmPassword = binding.etConfirmPassword.text.toString()

            if (username.isEmpty()) {
                binding.etUsername.error = "Username wajib diisi"
                return@setOnClickListener
            }

            if (password.isEmpty()) {
                binding.etPassword.error = "Password wajib diisi"
                return@setOnClickListener
            }

            if (confirmPassword.isEmpty()) {
                binding.etConfirmPassword.error = "Konfirmasi password wajib diisi"
                return@setOnClickListener
            }

            if (password != confirmPassword) {
                binding.etConfirmPassword.error = "Password dan Confirm Password harus sama"
                return@setOnClickListener
            }

            val pref = getSharedPreferences("user_pref", MODE_PRIVATE)
            pref.edit()
                .putString("registered_username", username)
                .putString("registered_password", password)
                .putString("confirm_password", confirmPassword)
                .apply()

            startActivity(
                Intent(this, VerifikasiActivity::class.java)
            )
        }
    }

    private fun setupPasswordToggle() {
        binding.btnTogglePassword.setOnClickListener {
            isPasswordVisible = !isPasswordVisible
            if (isPasswordVisible) {
                binding.etPassword.transformationMethod = HideReturnsTransformationMethod.getInstance()
                binding.btnTogglePassword.setImageResource(R.drawable.ic_visibility)
            } else {
                binding.etPassword.transformationMethod = PasswordTransformationMethod.getInstance()
                binding.btnTogglePassword.setImageResource(R.drawable.ic_visibility_off)
            }
            binding.etPassword.setSelection(binding.etPassword.text.length)
        }

        binding.btnToggleConfirmPassword.setOnClickListener {
            isConfirmPasswordVisible = !isConfirmPasswordVisible
            if (isConfirmPasswordVisible) {
                binding.etConfirmPassword.transformationMethod = HideReturnsTransformationMethod.getInstance()
                binding.btnToggleConfirmPassword.setImageResource(R.drawable.ic_visibility)
            } else {
                binding.etConfirmPassword.transformationMethod = PasswordTransformationMethod.getInstance()
                binding.btnToggleConfirmPassword.setImageResource(R.drawable.ic_visibility_off)
            }
            binding.etConfirmPassword.setSelection(binding.etConfirmPassword.text.length)
        }
    }

    private fun checkPasswordStrength(password: String) {
        if (password.isEmpty()) {
            binding.layoutPasswordStrength.visibility = View.GONE
            return
        }

        binding.layoutPasswordStrength.visibility = View.VISIBLE

        val hasLength = password.length >= 8
        val hasUpperLower = password.any { it.isUpperCase() } && password.any { it.isLowerCase() }
        val hasNumber = password.any { it.isDigit() }
        val hasSpecial = password.any { !it.isLetterOrDigit() }

        updateRuleText(binding.tvRuleLength, "Minimal 8 karakter", hasLength)
        updateRuleText(binding.tvRuleUpperLower, "Huruf besar & kecil", hasUpperLower)
        updateRuleText(binding.tvRuleNumber, "Memiliki angka (0-9)", hasNumber)
        updateRuleText(binding.tvRuleSpecial, "Memiliki karakter khusus (!@#$%^&*)", hasSpecial)

        var score = 0
        if (hasLength) score++
        if (password.length >= 12) score++
        if (hasUpperLower) score++
        if (hasNumber) score++
        if (hasSpecial) score++

        val (label, colorRes, progress) = when {
            score <= 1 -> Triple("Sangat Lemah", "#D32F2F", 20)
            score == 2 -> Triple("Lemah", "#F57C00", 40)
            score == 3 -> Triple("Sedang", "#FBC02D", 60)
            score == 4 -> Triple("Kuat", "#388E3C", 80)
            else -> Triple("Sangat Kuat", "#1B5E20", 100)
        }

        val color = Color.parseColor(colorRes)

        binding.tvPasswordStrength.text = "Kekuatan Password: $label"
        binding.tvPasswordStrength.setTextColor(color)
        binding.pbPasswordStrength.progress = progress
        binding.pbPasswordStrength.progressTintList = ColorStateList.valueOf(color)
    }

    private fun updateRuleText(textView: android.widget.TextView, ruleName: String, isMet: Boolean) {
        if (isMet) {
            textView.text = "✓ $ruleName"
            textView.setTextColor(Color.parseColor("#388E3C"))
        } else {
            textView.text = "• $ruleName"
            textView.setTextColor(Color.parseColor("#888888"))
        }
    }
}