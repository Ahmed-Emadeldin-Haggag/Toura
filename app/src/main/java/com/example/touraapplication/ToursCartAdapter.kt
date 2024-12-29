package com.example.touraapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ToursCartAdapter(private val tourList: MutableList<ToursCartActivity.Tour>) :
    RecyclerView.Adapter<ToursCartAdapter.TourViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TourViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_touras_items, parent, false)
        return TourViewHolder(view)
    }

    override fun onBindViewHolder(holder: TourViewHolder, position: Int) {
        val tour = tourList[position]
        holder.nameTextView.text = tour.title
        holder.dateTextView.text = "Date: ${tour.date}"
        holder.descriptionTextView.text = tour.description
        holder.priceTextView.text = "Price: ${tour.price} EGP"
        holder.imageView.setImageResource(tour.imageResId)  // Set image resource
    }

    override fun getItemCount(): Int {
        return tourList.size
    }

    class TourViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.title)
        val dateTextView: TextView = itemView.findViewById(R.id.date)
        val descriptionTextView: TextView = itemView.findViewById(R.id.description)
        val priceTextView: TextView = itemView.findViewById(R.id.price)
        val imageView: ImageView = itemView.findViewById(R.id.image)  // Image view for the tour image
    }
}
