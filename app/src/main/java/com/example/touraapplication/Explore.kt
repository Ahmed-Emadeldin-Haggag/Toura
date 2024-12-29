package com.example.touraapplication

class Explore {
}

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ExploreActivity : AppCompatActivity() {

    private lateinit var latestUpdatesText: TextView
    private lateinit var tripPackagesText: TextView
    private lateinit var blogPostsText: TextView
    private lateinit var callToActionButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_explore)

        // Initializing views
        latestUpdatesText = findViewById(R.id.latestUpdatesText)
        tripPackagesText = findViewById(R.id.tripPackagesText)
        blogPostsText = findViewById(R.id.blogPostsText)
        callToActionButton = findViewById(R.id.callToActionButton)

        // Load data from server or API
        loadData()

        // Handling call-to-action button click
        callToActionButton.setOnClickListener {
            navigateToServicesPage()
        }
    }

    private fun loadData() {
        // Simulating API calls to fetch the data for latest updates, trip packages, and blog posts
        GlobalScope.launch(Dispatchers.Main) {
            val latestUpdates = fetchLatestUpdates()
            val tripPackages = fetchNewTripPackages()
            val blogPosts = fetchBlogPosts()

            // Updating the UI with the fetched data
            latestUpdatesText.text = latestUpdates
            tripPackagesText.text = tripPackages
            blogPostsText.text = blogPosts
        }
    }

    private suspend fun fetchLatestUpdates(): String {
        // Simulate fetching data from server
        // In a real-world app, you would use an API call here
        return "📱 We just launched a new Instagram contest! Win a free trip to the Red Sea! 🎉\n" +
                "🔔 Exciting news on our Twitter: We’re adding 3 new destinations this month!\n" +
                "📣 Check out our Facebook post for exclusive deals on upcoming group tours!"
    }

    private suspend fun fetchNewTripPackages(): String {
        // Simulate fetching new trip packages from server
        return "🌴 A 7-day luxury trip to Sharm El Sheikh with all-inclusive amenities!\n" +
                "🚤 Exclusive deal: Book a trip to Alexandria and get a free city tour!\n" +
                "🏖️ Last-minute deal: 50% off on trips to the North Coast this weekend!"
    }

    private suspend fun fetchBlogPosts(): String {
        // Simulate fetching blog posts or guides from server
        return "📝 How to Choose the Best Beach Vacation in Egypt – A Complete Guide\n" +
                "🌍 Top 5 Trending Destinations You Must Visit in 2024\n" +
                "📖 The Ultimate Travel Guide to Luxor: Unveiling Ancient Wonders"
    }

    private fun navigateToServicesPage() {
        // This method will handle the navigation to the services page
        Toast.makeText(this, "Navigating to Services Page...", Toast.LENGTH_SHORT).show()
        // You can start a new Activity or navigate based on your app flow
        // For example:
        // val intent = Intent(this, ServicesActivity::class.java)
        // startActivity(intent)
    }
}

