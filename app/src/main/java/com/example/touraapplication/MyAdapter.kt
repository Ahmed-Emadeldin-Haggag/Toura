package com.example.touraapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.touraapplication.MainActivity.Tour
import com.example.touraapplication.databinding.RvTourasItemsBinding

class MyAdapter(private var tourList: List<Tour>) : RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    class ViewHolder(val binding: RvTourasItemsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(tour:Tour){
            binding.title.text = tour.title
            binding.date.text = tour.date
            binding.image.setImageDrawable(
                ContextCompat.getDrawable(binding.root.context, tour.imageResId)
            )


        }
    }

    // Inflate the layout for each item and return the ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(RvTourasItemsBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    // Bind the data of a tour to the ViewHolder
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(tourList[position])
    }

    // Return the total number of items
    override fun getItemCount(): Int =tourList.size

    fun updateList(newList:List<Tour>){
        val diffUtilCallback = MyDiffUtil(oldList = this.tourList, newList = newList)
        val diffResult = DiffUtil.calculateDiff(diffUtilCallback)

        this.tourList = newList // Update the data
        diffResult.dispatchUpdatesTo(this) // Notify the adapter about changes
    }

}


