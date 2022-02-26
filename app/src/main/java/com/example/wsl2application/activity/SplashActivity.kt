package com.example.wsl2application.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.wsl2application.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    lateinit var  binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Handler(mainLooper).postDelayed({
            startActivity(Intent(this , Login1Activity::class.java))
        } , 1000)
    }
}