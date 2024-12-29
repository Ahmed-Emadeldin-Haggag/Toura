package com.example.touraapplication

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.touraapplication.databinding.SignupBinding
import com.google.firebase.auth.FirebaseAuth

class Signup : AppCompatActivity() {

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
            val password2 = binding.checkpassword.text.toString()

            if (isValidInput(email, password, password2)) {
                // Create user with email and password
                firebaseAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            // After successful signup, redirect to MainActivity
                            val intent = Intent(this, UserProfileActivity::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            val exception = task.exception
                            val errorMessage = exception?.localizedMessage
                                ?: "Signup failed. Please try again."
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

    // Input validation
    private fun isValidInput(email: String, password: String, password2: String): Boolean {
        // Check if email is empty
        if (TextUtils.isEmpty(email)) {
            binding.SignupUsername.error = "Email cannot be empty"
            return false
        }

        // Check if email is in a valid format
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.SignupUsername.error = "Please enter a valid email"
            return false
        }

        // Check if password is empty
        if (TextUtils.isEmpty(password)) {
            binding.signupPassword.error = "Password cannot be empty"
            return false
        }

        // Check if password has at least 8 characters
        if (password.length < 8) {
            binding.signupPassword.error = "Password must be at least 8 characters long"
            return false
        }

        // Check if password contains at least one letter
        if (!password.matches(".*[A-Za-z].*".toRegex())) {
            binding.signupPassword.error = "Password must contain at least one letter"
            return false
        }

        // Check if password contains at least one number
        if (!password.matches(".*[0-9].*".toRegex())) {
            binding.signupPassword.error = "Password must contain at least one number"
            return false
        }

        // Check if both passwords match
        if (password != password2) {
            binding.checkpassword.error = "Passwords do not match"
            return false
        }

        return true
    }
}
