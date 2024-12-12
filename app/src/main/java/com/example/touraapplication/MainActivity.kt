package com.example.touraapplication

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.touraapplication.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.signinbutton.setOnClickListener {
            val username = binding.username.text.toString()
            val password = binding.password.text.toString()

            if (isValidInput(username, password)) {
                val intent = Intent(this, HomePage::class.java)
                intent.putExtra("user_name", username)
                startActivity(intent)
            }
        }

//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)

//        var usernameInput= findViewById<EditText>(R.id.username)
//        val passwordInput= findViewById<EditText>(R.id.password)
//        val loginBtn= findViewById<Button>(R.id.signinbutton)


        ViewCompat.setOnApplyWindowInsetsListener(findViewById<ConstraintLayout>(R.id.login)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        }
    private fun isValidInput(username: String, password: String): Boolean {

        // Check if the user name is not empty
        if (TextUtils.isEmpty(username)) {
            binding.username.error = "Username cannot be empty"
            return false
        }

        // Check if the password is not empty
        if (TextUtils.isEmpty(password)) {
            binding.password.error = "Password cannot be empty"
            return false
        }

        // Check if the password has at least 8 characters
        if (password.length < 8) {
            binding.password.error = "Password must be at least 8 characters long" // Show error under password field
            return false
        }

        // Check if the password contains at least one letter
        if (!password.matches(".*[A-Za-z].*".toRegex())) {
            binding.password.error = "Password must contain at least one letter" // Show error under password field
            return false
        }

        // Check if the password contains at least one number
        if (!password.matches(".*[0-9].*".toRegex())) {
            binding.password.error = "Password must contain at least one number" // Show error under password field
            return false
        }

        return true
    }
}
