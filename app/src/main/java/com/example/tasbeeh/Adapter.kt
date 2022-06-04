package com.example.tasbeeh

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Adapter(
    private val context: Context,
    private val wordList: List<Data>,
    private val listener: (Data) -> Unit
) : RecyclerView.Adapter<Adapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageInt: ImageView = itemView.findViewById(R.id.ivImageInt)
        private val divineWords: TextView = itemView.findViewById(R.id.tasbeh_word_home)
        private val divineWordsMeaning: TextView =itemView.findViewById(R.id.divineWordMeaning)

        fun bindView(data: Data, listener: (Data) -> Unit) {
            imageInt.setImageResource(data.imageInt)
            divineWords.text = data.divineWord
            divineWordsMeaning.text = data.divineWordMeaning
            itemView.setOnClickListener { listener(data) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_order, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(wordList[position], listener)
    }

    override fun getItemCount(): Int {
        return wordList.size
    }
}