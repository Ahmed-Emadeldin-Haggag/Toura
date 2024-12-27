package com.example.touraapplication

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.touraapplication.databinding.HomepageBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: HomepageBinding
    private lateinit var tourAdapter: MyAdapter

    private val touralist = mutableListOf(
        Tour("Pyramids of Giza", "March 10, 2024", R.drawable.pyramids_of_giza),
        Tour("Luxor Temples", "April 5, 2024", R.drawable.luxor_temples),
        Tour("Nile River Cruise", "May 12, 2024", R.drawable.nile_cruise)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = HomepageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up RecyclerView
        tourAdapter = MyAdapter(touralist)
        binding.rvMovies.layoutManager = LinearLayoutManager(this)
        binding.rvMovies.adapter = tourAdapter

        updateTours()
    }
    data class Tour(
        val title: String,
        val date: String,
        val imageResId: Int
    )

    private fun updateTours() {
        val newTourList = listOf(
            Tour("Nile River Cruise", "May 12, 2024", R.drawable.nile_cruise))
//        touralist.addAll(newTourList)  // Add new tours to the list
//        tourAdapter.notifyDataSetChanged() // Notify adapter of the change
//    }
    }

    override fun onBackPressed() {
        // Exit the app when back button is pressed
        finishAffinity()// Close all activities and exit the app
    }
}




