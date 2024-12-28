package com.example.touraapplication


import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.touraapplication.databinding.HomepageBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private lateinit var binding: HomepageBinding
    private lateinit var tourAdapter: MyAdapter
    private lateinit var sharedPreferences: SharedPreferences

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
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = HomepageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPreferences = getSharedPreferences("TourAppPrefs", MODE_PRIVATE)

        if (!isUserLoggedIn()) {
            navigateToLogin()
        }

        // Set up Spinner
        val spinnerItems = listOf("Home Page", "Profile", "Tours Cart","Settings","Sign Out")
        val spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerItems)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinner.adapter = spinnerAdapter
        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                when (position) {
                    0 -> Toast.makeText(this@MainActivity, "Home Page", Toast.LENGTH_SHORT).show()
                    1 -> Toast.makeText(this@MainActivity, "Profile", Toast.LENGTH_SHORT).show()
                    2 -> Toast.makeText(this@MainActivity, "Tours Cart", Toast.LENGTH_SHORT).show()
                    3 -> Toast.makeText(this@MainActivity, "Settings", Toast.LENGTH_SHORT).show()
                    4 ->signOut()

                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
            }
        }



        // Set up RecyclerView
        tourAdapter = MyAdapter(touralist)
        binding.rvTours.layoutManager = LinearLayoutManager(this)
        binding.rvTours.adapter = tourAdapter
    }

    private fun isUserLoggedIn(): Boolean {
        return sharedPreferences.getBoolean("isLoggedIn", false)
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
            // Clear login state
            val editor = sharedPreferences.edit()
            editor.putBoolean("isLoggedIn", false)
            editor.apply()

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







