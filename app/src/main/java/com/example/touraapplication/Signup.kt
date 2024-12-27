package com.example.touraapplication

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.touraapplication.databinding.SignupBinding
import com.google.firebase.auth.FirebaseAuth


class Signup: AppCompatActivity() {

    private lateinit var binding: SignupBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SignupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth = FirebaseAuth.getInstance()

        // Sign Up Button
        binding.signupButton.setOnClickListener {
            val email = binding.SignupUsername.text.toString()
            val password = binding.signupPassword.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please enter both email and password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (isValidInput(email, password)) {
                firebaseAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        // After successful signup, redirect to Login
                        if (task.isSuccessful) {
                             val intent = Intent(this, MainActivity::class.java)
                             startActivity(intent)
                             finish()

                        } else {
                            val exception = task.exception
                            val errorMessage = exception?.localizedMessage ?: "Signup failed. Please try again."
                            Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
                        }
                    }
            } else {
                Toast.makeText(this, "Please fix the errors", Toast.LENGTH_SHORT).show()
            }
        }
        // Log In Button Click
        binding.loginButton.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun isValidInput(username: String, password: String): Boolean {

        // Check if the user name is not empty
        if (TextUtils.isEmpty(username)) {
            binding.SignupUsername.error = "Username cannot be empty"
            return false
        }

        // Check if the password is not empty
        if (TextUtils.isEmpty(password)) {
            binding.signupPassword.error = "Password cannot be empty"
            return false
        }

        // Check if the password has at least 8 characters
        if (password.length < 8) {
            binding.signupPassword.error = "Password must be at least 8 characters long"
            return false
        }

        // Check if the password contains at least one letter
        if (!password.matches(".*[A-Za-z].*".toRegex())) {
            binding.signupPassword.error = "Password must contain at least one letter"
            return false
        }

        // Check if the password contains at least one number
        if (!password.matches(".*[0-9].*".toRegex())) {
            binding.signupPassword.error = "Password must contain at least one number"
            return false
        }

        return true
    }
}