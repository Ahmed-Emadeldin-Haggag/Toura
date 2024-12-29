package com.example.tourapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ToursCartAdapter(private val tourList: List<Tour>) : RecyclerView.Adapter<ToursCartAdapter.TourViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TourViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_tour, parent, false)
        return TourViewHolder(view)
    }

    override fun onBindViewHolder(holder: TourViewHolder, position: Int) {
        val tour = tourList[position]
        holder.nameTextView.text = tour.name
        holder.priceTextView.text = "Price: ${tour.price} EGP"
        holder.dateTextView.text = "Date: ${tour.date}"
        holder.descriptionTextView.text = tour.description
    }

    override fun getItemCount(): Int {
        return tourList.size
    }

    class TourViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.tourName)
        val priceTextView: TextView = itemView.findViewById(R.id.tourPrice)
        val dateTextView: TextView = itemView.findViewById(R.id.tourDate)
        val descriptionTextView: TextView = itemView.findViewById(R.id.tourDescription)
    }
}
