package com.example.touraapplication

import androidx.recyclerview.widget.DiffUtil
import com.example.touraapplication.MainActivity.Tour

class MyDiffUtil(private val oldList: List<Tour>, private val newList: List<Tour>) : DiffUtil.Callback() {

    // Return the old list size
    override fun getOldListSize(): Int {
        return oldList.size
    }

    // Return the new list size
    override fun getNewListSize(): Int {
        return newList.size

    }

    // Check if the items at the given positions are the same (using title as unique identifier)
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].title == newList[newItemPosition].title
    }

    // Check if the contents of the items at the given positions are the same
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}
