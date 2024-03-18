package com.ifs21017.dinopedia

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ifs21017.dinopedia.databinding.ItemRowDinofamilyBinding

class DinoFamilyAdapter(private val listDinoFamily: ArrayList<DinoFamily>) :
    RecyclerView.Adapter<DinoFamilyAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback:  OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemRowDinofamilyBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ListViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val dinoFamily = listDinoFamily[position]
        holder.binding.tvNamafamily.text = dinoFamily.namafamily
        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(listDinoFamily[holder.adapterPosition])
        }
    }

    override fun getItemCount(): Int = listDinoFamily.size
    class ListViewHolder(var binding: ItemRowDinofamilyBinding) : RecyclerView.ViewHolder(binding.root)

    interface OnItemClickCallback {
        fun onItemClicked(data: DinoFamily)
    }
}