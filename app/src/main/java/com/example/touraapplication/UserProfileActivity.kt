package com.example.touraapplication

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.touraapplication.databinding.ActivityUserProfileBinding

class UserProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserProfileBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize ViewBinding
        binding = ActivityUserProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("TourAppPrefs", MODE_PRIVATE)

        // Get references to the EditTexts and Button using ViewBinding
        val editName: EditText = binding.editName
        val editEmail: EditText = binding.editEmail
        val editPhone: EditText = binding.editPhone
        val btnUpdate: Button = binding.btnUpdate
        val userInfoTextView: TextView = binding.userInfo

        // Retrieve the user data from SharedPreferences
        val userName = sharedPreferences.getString("userName", "Unknown")
        val userEmail = sharedPreferences.getString("userEmail", "Unknown")
        val userPhone = sharedPreferences.getString("userPhone", "Unknown")

        // Set the initial user information in the UI
        userInfoTextView.text = "Name: $userName\nEmail: $userEmail\nPhone: $userPhone"

        // Fill the EditText fields with current user data
        editName.setText(userName)
        editEmail.setText(userEmail)
        editPhone.setText(userPhone)

        // Button click listener to update user info
        btnUpdate.setOnClickListener {
            val name = editName.text.toString()
            val email = editEmail.text.toString()
            val phone = editPhone.text.toString()

            if (name.isNotEmpty() && email.isNotEmpty() && phone.isNotEmpty()) {
                // Update the user info in SharedPreferences
                val editor = sharedPreferences.edit()
                editor.putString("userName", name)
                editor.putString("userEmail", email)
                editor.putString("userPhone", phone)
                editor.apply()

                // Optionally, update the user info displayed on the screen
                userInfoTextView.text = "Name: $name\nEmail: $email\nPhone: $phone"

                // Show a success message
                Toast.makeText(this, "User info updated successfully", Toast.LENGTH_SHORT).show()

                // Navigate to MainActivity
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            } else {
                // Show an error message if any field is empty
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
