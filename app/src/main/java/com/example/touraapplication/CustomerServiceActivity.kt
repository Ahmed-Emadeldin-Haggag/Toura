package com.example.touraapplication

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class CustomerServiceActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_service)

        // Initialize the phone numbers
        val phoneNumbers = mapOf(
            R.id.serviceBox1 to "tel:01062182129", // Trips inquiries
            R.id.serviceBox2 to "tel:01029174808", // Hotels inquiries
            R.id.serviceBox3 to "tel:01062182230", // Technical support
            R.id.serviceBox4 to "tel:01029174809"  // Financial support
        )

        // Find the service boxes and set click listeners
        for ((boxId, phoneNumber) in phoneNumbers) {
            val serviceBox = findViewById<LinearLayout>(boxId)
            serviceBox.setOnClickListener { makeCall(phoneNumber) }
        }

        // Find the TextViews for phone numbers and set click listeners
        val serviceTextViews = mapOf(
            R.id.serviceContact1 to "tel:01062182129",
            R.id.serviceContact2 to "tel:01029174808",
            R.id.serviceContact3 to "tel:01062182230",
            R.id.serviceContact4 to "tel:01029174809"
        )

        for ((textViewId, phoneNumber) in serviceTextViews) {
            val serviceContact = findViewById<TextView>(textViewId)
            serviceContact.setOnClickListener { makeCall(phoneNumber) }
        }
    }

    // Helper function to initiate a call
    private fun makeCall(phoneNumber: String) {
        val intent = Intent(Intent.ACTION_DIAL, Uri.parse(phoneNumber))
        startActivity(intent)
    }
}
