package com.example.labthree.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.labthree.R
import com.example.labthree.db.Image

class ChildGroupAdapter(private val images: List<Image>) : RecyclerView.Adapter<ChildGroupAdapter.ViewHolder>() {

    private lateinit var clickListener: OnItemClickListener

    inner class ViewHolder(itemView: View, listener: OnItemClickListener) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        clickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildGroupAdapter.ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val categoryView = inflater.inflate(R.layout.image_placeholder, parent, false)
        return ViewHolder(categoryView, clickListener)
    }

    override fun getItemCount(): Int {
        return images.size;
    }

    override fun onBindViewHolder(holder: ChildGroupAdapter.ViewHolder, position: Int) {

    }
}