package com.example.toursapp

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ToursCartActivity : AppCompatActivity() {

    private lateinit var tour1Title: TextView
    private lateinit var tour1Date: TextView
    private lateinit var tour1Description: TextView
    private lateinit var tour1Price: TextView
    private lateinit var tour1Status: TextView

    private lateinit var tour2Title: TextView
    private lateinit var tour2Date: TextView
    private lateinit var tour2Description: TextView
    private lateinit var tour2Price: TextView
    private lateinit var tour2Status: TextView

    private lateinit var tour3Title: TextView
    private lateinit var tour3Date: TextView
    private lateinit var tour3Description: TextView
    private lateinit var tour3Price: TextView
    private lateinit var tour3Status: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tours_cart)

        // Rushing all the UI elements
        tour1Title = findViewById(R.id.tour1Title)
        tour1Date = findViewById(R.id.tour1Date)
        tour1Description = findViewById(R.id.tour1Description)
        tour1Price = findViewById(R.id.tour1Price)
        tour1Status = findViewById(R.id.tour1Status)

        tour2Title = findViewById(R.id.tour2Title)
        tour2Date = findViewById(R.id.tour2Date)
        tour2Description = findViewById(R.id.tour2Description)
        tour2Price = findViewById(R.id.tour2Price)
        tour2Status = findViewById(R.id.tour2Status)

        tour3Title = findViewById(R.id.tour3Title)
        tour3Date = findViewById(R.id.tour3Date)
        tour3Description = findViewById(R.id.tour3Description)
        tour3Price = findViewById(R.id.tour3Price)
        tour3Status = findViewById(R.id.tour3Status)

        // Setting tour 1 (Luxor) data
        tour1Title.text = "Luxor Tour"
        tour1Date.text = "Date: 12/01/2024"
        tour1Description.text = "Visit the magnificent temples of Luxor. A rich cultural experience!"
        tour1Price.text = "Price: 1200 EGP"
        tour1Status.text = "Status: Completed"

        // Setting tour 2 (Giza)
        tour2Title.text = "Giza Pyramids Tour"
        tour2Date.text = "Date: 15/02/2024"
        tour2Description.text = "Explore the ancient wonders of the Giza Pyramids."
        tour2Price.text = "Price: 800 EGP"
        tour2Status.text = "Status: Completed"

        // Setting tour 3 (Dahab)
        tour3Title.text = "Dahab Beach Tour"
        tour3Date.text = "Date: 20/03/2024"
        tour3Description.text = "Enjoy the serene beaches of Dahab, a perfect getaway."
        tour3Price.text = "Price: 1000 EGP"
        tour3Status.text = "Status: Pending Payment"
    }
}
