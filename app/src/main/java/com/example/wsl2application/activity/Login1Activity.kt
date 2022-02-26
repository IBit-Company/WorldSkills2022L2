package com.example.wsl2application.activity

import android.content.Intent
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Shader
import android.graphics.Shader.TileMode
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.wsl2application.R
import com.example.wsl2application.database.MainDatabase
import com.example.wsl2application.database.UseDao
import com.example.wsl2application.databinding.ActivityLogin1Binding


class Login1Activity : AppCompatActivity() {
    lateinit var binding: ActivityLogin1Binding
    lateinit var userDao: UseDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogin1Binding.inflate(layoutInflater)
        setContentView(binding.root)

        userDao = Room.databaseBuilder(this , MainDatabase::class.java , getString(R.string.databaseName)).build().getUserDao()

        val textShader: Shader = LinearGradient(
            0f, 0f, 1f, binding.button2.getTextSize(), intArrayOf(
                Color.parseColor("#FF4B1F"),
                Color.parseColor("#FF9068"),

            ), null, TileMode.CLAMP
        )


        with(binding){
            button2.paint.shader = textShader

            button2.setOnClickListener {
                startActivity(Intent(this@Login1Activity , Login2Activity::class.java))
            }

            button1.setOnClickListener {
                val email = binding.emailEditText.text.toString()
                val password = binding.passwordEditText.text.toString()

                if (email.isNotBlank() && password.isNotBlank()){
                    Thread{
                        val user = userDao.getUser(email)

                        runOnUiThread {
                            if (user != null && user.password == password){
                                Toast.makeText(this@Login1Activity , "Successfully" , Toast.LENGTH_LONG).show()

                            }else
                                Toast.makeText(this@Login1Activity , "username and password doesn't match!" , Toast.LENGTH_LONG).show()
                        }

                    }.start()
                }else
                    Toast.makeText(this@Login1Activity , "Fill all inputs!" , Toast.LENGTH_LONG).show()


            }

        }
    }
}