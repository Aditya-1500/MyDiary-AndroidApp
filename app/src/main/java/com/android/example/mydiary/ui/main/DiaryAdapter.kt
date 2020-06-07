package com.android.example.mydiary.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.example.mydiary.R
import com.android.example.mydiary.database.DiaryEntry
import kotlinx.android.synthetic.main.diary_entry.view.*

class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val title: TextView = itemView.findViewById(R.id.entry_title)
    val entry: TextView = itemView.findViewById(R.id.entry_text)
    val dateOfEntry: TextView = itemView.findViewById(R.id.date_of_entry)
}

class DiaryAdapter: RecyclerView.Adapter<ViewHolder>() {
    var entries = listOf<DiaryEntry>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int {
        return entries.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = entries[position]
        holder.title.text = item.Title
        holder.entry.text = item.Entry
        holder.dateOfEntry.text = item.dateOfEntry
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.diary_entry,parent,false)
        return ViewHolder(view)
    }
}