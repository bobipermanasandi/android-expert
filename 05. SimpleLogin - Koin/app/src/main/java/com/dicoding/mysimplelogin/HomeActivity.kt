package com.dicoding.mysimplelogin

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.mysimplelogin.databinding.ActivityHomeBinding
import org.koin.android.ext.android.inject
import kotlin.getValue

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
//    private lateinit var userRepository: UserRepository
    private val userRepository: UserRepository by inject()

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val sesi = SessionManager(this)
//        userRepository = UserRepository.getInstance(sesi)

        binding.tvWelcome.text = "Welcome ${userRepository.getUser()}"

        binding.btnLogout.setOnClickListener {
            userRepository.logoutUser()
            moveToMainActivity()
        }
    }

    private fun moveToMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}
