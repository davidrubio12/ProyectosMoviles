package com.example.proyectodemoviles.recycler

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.proyectodemoviles.R
import com.example.proyectodemoviles.model.ProductRespuesta
import com.example.proyectodemoviles.util.Foto

class MyAdapter(private val dataSet: List<Foto>, private val onItemClick: (Foto) -> Unit) // recibe la URL o el identificador del producto
 : RecyclerView.Adapter<MyView>(){

    lateinit var myContexto : Context



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyView {
        myContexto = parent.context
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row,parent,false)

        return MyView(view)
    }

    override fun getItemCount()= dataSet.size

    override fun onBindViewHolder(holder: MyView, position: Int) {
        val foto = dataSet[position]

        Glide.with(myContexto)
            .load(foto.url)
            .into(holder.imV1)
        // Aquí se gestiona el click
        holder.itemView.setOnClickListener {
            onItemClick(foto)
        }

    }
}