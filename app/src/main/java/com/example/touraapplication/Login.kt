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

        binding.loginButton.setOnClickListener {
            val username = binding.loginEmail.text.toString()
            val password = binding.loginPassword.text.toString()
            firebaseAuth.signInWithEmailAndPassword(username, password)
                .addOnCompleteListener { task ->
                    // if it login Successfully
                    if (task.isSuccessful) {

                        val user = firebaseAuth.currentUser
                        Toast.makeText(this, "Login successful!", Toast.LENGTH_SHORT).show()

                        //  go to MainActivity
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        val exceptionMessage = task.exception?.message
                        // Print the error
                        Toast.makeText(this, "Authentication failed: $exceptionMessage", Toast.LENGTH_SHORT).show()

                        // check if user name already exist
                        if (exceptionMessage?.contains("There is no user record corresponding to this identifier") == true) {
                            // if not got to signup
                            val intent = Intent(this, Signup::class.java)
                            startActivity(intent)


                        }
                    }
                }





        }
    }

}
