package com.example.tasbeeh

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tasbeeh.data.ZikrInfo
import com.example.tasbeeh.databinding.ItemOrderBinding

class ZikrAdapter() : RecyclerView.Adapter<ZikrAdapter.ViewHolder>() {

    var callback: ((ZikrInfo) -> Unit)? = null
    private var zikrs: List<ZikrInfo>? = null

    fun submitList(zikrs: List<ZikrInfo>) {
        this.zikrs = zikrs
        notifyItemRangeChanged(0, itemCount)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding =
            ItemOrderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val zikr = zikrs?.get(position)
        zikr?.let {
            holder.bindView(it)
        }
    }

    override fun getItemCount() = zikrs?.size ?: 0

    inner class ViewHolder(private val binding: ItemOrderBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindView(zikrInfo: ZikrInfo) {
            binding.tvZikrTitle.text = zikrInfo.zikr
            binding.tvZikrMeaning.text = zikrInfo.translation
            binding.tvArabicWord.text = zikrInfo.arabicWord
            binding.root.setOnClickListener { callback?.invoke(zikrInfo) }
        }
    }
}