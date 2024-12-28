package com.example.touraapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.touraapplication.databinding.LoginBinding
import com.google.firebase.auth.FirebaseAuth


class Login : AppCompatActivity() {

    private lateinit var binding: LoginBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth = FirebaseAuth.getInstance()

        // Login Button
        binding.loginButton.setOnClickListener {
            val username = binding.loginEmail.text.toString()
            val password = binding.loginPassword.text.toString()
            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please enter both email and password", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }
            firebaseAuth.signInWithEmailAndPassword(username, password)
                .addOnCompleteListener { task ->
                    // if it login Successfully
                    if (task.isSuccessful) {
                        //  go to MainActivity
                        Toast.makeText(this, "Login successful!", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        val exceptionMessage = task.exception?.message
                        // Print the error
                        Toast.makeText(
                            this,
                            "Authentication failed: $exceptionMessage",
                            Toast.LENGTH_SHORT
                        ).show()


                    }
                }
        }

        // Sign Up Button Click
        binding.signupButton.setOnClickListener {
            val intent = Intent(this, Signup::class.java)
            startActivity(intent)
        }
    }
}
