package com.example.touraapplication

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.touraapplication.databinding.HomepageBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private lateinit var binding: HomepageBinding
    private lateinit var tourAdapter: MyAdapter

    private val touralist = mutableListOf(
        Tour(
            title = "Pyramids of Giza",
            date = "March 10, 2024",
            imageResId = R.drawable.pyramids_of_giza,
            price = "$150",
            description = "Explore one of the Seven Wonders of the Ancient World. Visit the Great Pyramid, the Sphinx, and learn about ancient Egyptian history."
        ),
        Tour(
            title = "Luxor Temples",
            date = "April 5, 2024",
            imageResId = R.drawable.luxor_temples,
            price = "$200",
            description = "Discover the magnificent temples of Luxor. Witness the grandeur of Karnak Temple and the beauty of the Luxor Temple."
        ),
        Tour(
            title = "Nile River Cruise",
            date = "May 12, 2024",
            imageResId = R.drawable.nile_cruise,
            price = "$300",
            description = "Enjoy a luxurious cruise on the Nile River, visiting key landmarks and enjoying scenic views of Egypt."
        ),
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
        ),
        Tour(
            title = "White Desert Safari",
            date = "January 8, 2025",
            imageResId = R.drawable.white_desert,
            price = "$350",
            description = "Embark on a thrilling desert safari to the surreal White Desert, known for its unique chalk rock formations and serene beauty."
        ),
        Tour(
            title = "Dahshur Pyramids Excursion",
            date = "February 14, 2025",
            imageResId = R.drawable.dahshur_pyramids,
            price = "$170",
            description = "Visit the Red Pyramid and Bent Pyramid at Dahshur, lesser-known gems of Egypt that showcase early pyramid construction techniques."
        ),
        Tour(
            title = "St. Catherine's Monastery Visit",
            date = "March 22, 2025",
            imageResId = R.drawable.st_catherine_monastery,
            price = "$130",
            description = "Discover the ancient St. Catherine's Monastery at the foot of Mount Sinai, a UNESCO World Heritage Site filled with history and spirituality."
        )
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = HomepageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        createNotificationChannel()

        // Set up Spinner
        val spinnerItems = listOf("Home Page", "Profile","Registration Page", "Tours Cart","Add Payment Card","Explore Page", "Customer Service","Sign Out")
        val spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerItems)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinner.adapter = spinnerAdapter
        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                when (position) {
                    0 -> Toast.makeText(this@MainActivity, "Home Page", Toast.LENGTH_SHORT).show()
                    1 -> {
                        // Navigate to UserProfileActivity
                        val intent = Intent(this@MainActivity, UserProfileActivity::class.java)
                        startActivity(intent)
                    }

                    2 -> {val intent = Intent(this@MainActivity, TripsActivity::class.java)
                        startActivity(intent)}
                    3-> {val intent = Intent(this@MainActivity, ToursCartActivity::class.java)
                        startActivity(intent)}
                    4->{
                        val intent = Intent(this@MainActivity, PaymentActivity::class.java)
                    startActivity(intent)
                }
                    5-> {val intent = Intent(this@MainActivity, ExploreActivity::class.java)
                    startActivity(intent)}

                6-> {val intent = Intent(this@MainActivity, CustomerServiceActivity::class.java)
                    startActivity(intent)}
                7->  signOut()

                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }


        // Set up RecyclerView
        tourAdapter = MyAdapter(touralist)
        binding.rvTours.layoutManager = LinearLayoutManager(this)
        binding.rvTours.adapter = tourAdapter
        // Trigger notification on button click (example)
        binding.notifyButton.setOnClickListener {
            sendNotification()
        }
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelId = "tour_notifications"
            val channelName = "Tour Notifications"
            val channelDescription = "Notifications for tour updates"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(channelId, channelName, importance).apply {
                description = channelDescription
            }

            val notificationManager: NotificationManager =
                getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun sendNotification() {
        val channelId = "tour_notifications"

        // Check if the POST_NOTIFICATIONS permission is granted for Android 13+
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (checkSelfPermission(android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(arrayOf(android.Manifest.permission.POST_NOTIFICATIONS), 101)
                return
            }
        }

        // Intent to open MainActivity when the notification is clicked
        val intent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(
            this, 0, intent, PendingIntent.FLAG_IMMUTABLE
        )

        val builder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(android.R.drawable.ic_dialog_info) // Replace with a valid drawable
            .setContentTitle("Tour Notification")
            .setContentText("Check out the latest updates on our tours!")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        with(NotificationManagerCompat.from(this)) {
            notify(1, builder.build()) // Unique ID for each notification
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == 101) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission was granted, send the notification
                sendNotification()
            } else {
                // Permission was denied, show a message or handle it
                Toast.makeText(this, "Notification permission denied", Toast.LENGTH_SHORT).show()
            }
        }
    }





    private fun navigateToLogin() {
        val intent = Intent(this, Login::class.java)
        startActivity(intent)
        finish()
    }


    private fun signOut() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Sign Out")
        builder.setMessage("Are you sure you want to sign out?")
        builder.setPositiveButton("Yes") { _, _ ->

            // Sign out from Firebase
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(this, Sign_Out::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK // Clear activity stack
            startActivity(intent)
            finish()
        }
        builder.setNegativeButton("No") { dialog, _ ->
            dialog.dismiss()
        }
        builder.create().show()
    }

    data class Tour(
        val title: String,
        val date: String,
        val imageResId: Int,
        val price: String,
        val description: String
    )



    override fun onBackPressed() {
        // Exit the app when back button is pressed
        finishAffinity()// Close all activities and exit the app
    }
}







