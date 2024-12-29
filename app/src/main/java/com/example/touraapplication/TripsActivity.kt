package com.example.touraapplication


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class TripsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trips)

        val payButton1 = findViewById<Button>(R.id.payButton1)
        val payButton2 = findViewById<Button>(R.id.payButton2)
        val payButton3 = findViewById<Button>(R.id.payButton3)

        payButton1.setOnClickListener {
            navigateToPayment("Luxor Trip", "25th December 2024", "Luxor, Egypt")
        }

        payButton2.setOnClickListener {
            navigateToPayment("Giza Pyramids Trip", "15th January 2025", "Giza, Egypt")
        }

        payButton3.setOnClickListener {
            navigateToPayment("Dahab Beach Trip", "10th February 2025", "Dahab, Egypt")
        }
    }

    private fun navigateToPayment(tripName: String, tripDate: String, tripLocation: String) {
        val intent = Intent(this, PaymentActivity::class.java)
        intent.putExtra("TRIP_NAME", tripName)
        intent.putExtra("TRIP_DATE", tripDate)
        intent.putExtra("TRIP_LOCATION", tripLocation)
        startActivity(intent)
    }
}
