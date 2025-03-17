package com.example.recyclerview.recycler


import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview.R

class MyAdapter(private val dataSet: List<String>) : RecyclerView.Adapter<MyView>() {

    var clickPosition : Int = RecyclerView.NO_POSITION


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyView {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.linea, parent, false)
        return MyView(view)

    }

    override fun getItemCount() = dataSet.size


    override fun onBindViewHolder(holder: MyView, position: Int) {
       holder.tvAnimales.text = dataSet[position]

        if (position == clickPosition){
            holder.tvAnimales.setTextColor(Color.WHITE)
            holder.tvAnimales.setBackgroundColor(Color.RED)
        }else{
            holder.tvAnimales.setTextColor(Color.BLACK)
            holder.tvAnimales.setBackgroundColor(Color.WHITE)
        }

        holder.tvAnimales.setOnClickListener(){
//            holder.tvAnimales.setTextColor(Color.WHITE)
//            holder.tvAnimales.setBackgroundColor(Color.RED)
            notifyItemChanged(clickPosition)
            clickPosition = position
            notifyItemChanged(clickPosition)

        }


    }
}

