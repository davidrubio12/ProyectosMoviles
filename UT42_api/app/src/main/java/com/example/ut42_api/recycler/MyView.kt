package com.example.ut42_api.recycler

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.ut42_api.R

class MyView(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val imV1 = itemView.findViewById<View>(R.id.imV1) as ImageView
}