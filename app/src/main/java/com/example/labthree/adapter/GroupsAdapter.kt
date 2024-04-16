package com.example.labthree.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.labthree.BottomFragment
import com.example.labthree.R
import com.example.labthree.entity.ImageGroup
import com.google.android.material.internal.ContextUtils.getActivity

class GroupsAdapter(private val groups: List<ImageGroup>) : RecyclerView.Adapter<GroupsAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val photosMonth = itemView.findViewById<TextView>(R.id.photosMonth)
        val photosRV: RecyclerView = itemView.findViewById<RecyclerView>(R.id.rvMonthPhotos)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val categoryView = inflater.inflate(R.layout.image_group, parent, false)
        return ViewHolder(categoryView)
    }

    override fun getItemCount(): Int {
        return groups.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val imageGroup: ImageGroup = groups.get(position)
        val photosMonth = holder.photosMonth
        photosMonth.setText(imageGroup.name)
        holder.photosRV.setHasFixedSize(true)
        holder.photosRV.layoutManager = GridLayoutManager(holder.itemView.context, 3)
        val adapter = ChildGroupAdapter(imageGroup.images)

        adapter.setOnItemClickListener(object : ChildGroupAdapter.OnItemClickListener{
            override fun onItemClick(position: Int) {
                val bottomSheet = BottomFragment()
                val bundle = Bundle()
                val image = imageGroup.images.get(position)
                bundle.putString("title", image.title)
                bundle.putString("description", image.description)
                bundle.putString("date", "" + image.year + "." + image.month + "." + image.day)
                bundle.putString("size", image.size.toString())
                bundle.putString("place", image.place)
                bottomSheet.arguments = bundle
                val manager = (holder.itemView.context as FragmentActivity).supportFragmentManager
                bottomSheet.show(manager , "Tag")
            }

        })
        holder.photosRV.adapter = adapter
    }

}