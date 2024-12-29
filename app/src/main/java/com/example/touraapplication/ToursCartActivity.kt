package com.example.touraapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ToursCartActivity : AppCompatActivity() {

    private lateinit var toursRecyclerView: RecyclerView
    private lateinit var toursCartAdapter: ToursCartAdapter

    // Sample Tour List
    private val tourList = mutableListOf(
        Tour(
            title = "Cairo City Exploration",
            date = "October 12, 2024",
            imageResId = R.drawable.cairo_city,
            price = "$180",
            description = "Immerse yourself in Cairo's vibrant culture, from bustling markets and historic mosques to modern attractions and stunning views."
        ),
        Tour(
            title = "Red Sea Snorkeling",
            date = "November 18, 2024",
            imageResId = R.drawable.red_sea_snorkeling,
            price = "$220",
            description = "Dive into the crystal-clear waters of the Red Sea and explore colorful coral reefs and exotic marine life on this snorkeling adventure."
        ),
        Tour(
            title = "Mount Sinai Sunrise Trek",
            date = "December 24, 2024",
            imageResId = R.drawable.mount_sinai,
            price = "$200",
            description = "Climb Mount Sinai under the stars and witness a spectacular sunrise from one of the most sacred spots in the world."
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tours_cart)

        // Initialize the RecyclerView
        toursRecyclerView = findViewById(R.id.toursRecyclerView)

        // Initialize the Adapter with the sample data
        toursCartAdapter = ToursCartAdapter(tourList)

        // Set up the RecyclerView
        toursRecyclerView.layoutManager = LinearLayoutManager(this)
        toursRecyclerView.adapter = toursCartAdapter
    }

    // Data class for Tour
    data class Tour(
        val title: String,
        val date: String,
        val imageResId: Int,
        val price: String,
        val description: String
    )
}
