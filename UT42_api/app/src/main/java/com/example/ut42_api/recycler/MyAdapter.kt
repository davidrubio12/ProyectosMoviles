package com.example.ut42_api.recycler

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ut42_api.R
import com.example.ut42_api.model.DogRespuesta

class MyAdapter(private val dataSet: DogRespuesta) : RecyclerView.Adapter<MyView>(){

    lateinit var myContexto : Context



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyView {
        myContexto = parent.context
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row,parent,false)

        return MyView(view)
    }

    override fun getItemCount()= dataSet.message!!.size

    override fun onBindViewHolder(holder: MyView, position: Int) {
       val url :  String = dataSet.message!![position]
        Glide.with(myContexto)
            .load(url)
            .into(holder.imV1)



    }
}