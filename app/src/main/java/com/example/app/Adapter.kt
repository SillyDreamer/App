package com.example.app

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Adapter(private var list: ArrayList<Predmet>) :
    RecyclerView.Adapter<Adapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = list[position].name
        holder.type.text = list[position].type
        if (list[position].type == "Лекция")
            holder.line.setBackgroundColor(Color.GREEN)
        else if (list[position].type == "Лабораторная работа")
            holder.line.setBackgroundColor(Color.BLUE)
        holder.teacher.text = list[position].teacher
        holder.hall.text = "Аудитория " + list[position].hall
        holder.time.text = list[position].time

    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name: TextView = itemView.findViewById(R.id.name)
        var type: TextView = itemView.findViewById(R.id.type)
        var teacher: TextView = itemView.findViewById(R.id.teacher)
        var time: TextView = itemView.findViewById(R.id.time)
        var hall: TextView = itemView.findViewById(R.id.hall)
        val line: View = itemView.findViewById(R.id.view1)

    }

}