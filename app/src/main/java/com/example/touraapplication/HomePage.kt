package com.example.touraapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.touraapplication.databinding.HomepageBinding


class HomePage : AppCompatActivity() {


    private lateinit var binding: HomepageBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = HomepageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username = intent.getStringExtra("user_name")

        binding.greet.text = "Welcome, $username"
    }

}