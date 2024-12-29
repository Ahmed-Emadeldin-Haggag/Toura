package com.example.touraapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ExploreActivity : AppCompatActivity() {

    private lateinit var latestUpdatesText: TextView
    private lateinit var tripPackagesText: TextView
    private lateinit var blogPostsText: TextView
    private lateinit var callToActionButton: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.explore_activity)

        // Binding views using findViewById
        latestUpdatesText = findViewById(R.id.latestUpdatesTitle)
        tripPackagesText = findViewById(R.id.newTripPackagesTitle)
        blogPostsText = findViewById(R.id.blogPostsAndGuidesTitle)
        callToActionButton = findViewById(R.id.callToActionButton)

        // Setting initial text for TextViews
        latestUpdatesText.text = "Check out our latest social media updates!"
        tripPackagesText.text = "New exciting trip packages for 2024!"
        blogPostsText.text = "Explore our latest blogs and travel guides."

        // Setting up a click listener for the call-to-action button
        callToActionButton.setOnClickListener {
            // Example interaction
            callToActionButton.text = "Clicked!" // Change button text on click
        }
    }
}
