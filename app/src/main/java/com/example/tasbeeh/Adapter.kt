package com.example.tasbeeh

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Adapter(
    private val infoData: List<Data>,
) : RecyclerView.Adapter<Adapter.ViewHolder>() {

    var onItemClick: ((Data) -> Unit)? = null

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
         val imageInt: ImageView = itemView.findViewById(R.id.ivImageInt)
         val tasbehWords: TextView = itemView.findViewById(R.id.tasbeh_word_home)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_order, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val word = infoData[position]
        holder.imageInt.setImageResource(word.imageInt)
        holder.tasbehWords.text = word.tasbehWord

        holder.itemView.setOnClickListener{
            onItemClick?.invoke(word)
        }
    }

    override fun getItemCount(): Int {
        return infoData.size
    }
}