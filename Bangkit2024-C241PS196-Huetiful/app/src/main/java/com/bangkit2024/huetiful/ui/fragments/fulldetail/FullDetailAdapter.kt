package com.bangkit2024.huetiful.ui.fragments.fulldetail

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bangkit2024.huetiful.data.local.model.DetailPalateModel
import com.bangkit2024.huetiful.databinding.ItemDetailBinding

class FullDetailAdapter :ListAdapter<DetailPalateModel, FullDetailAdapter.FullDetailViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FullDetailViewHolder {
        val binding = ItemDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FullDetailViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FullDetailViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class FullDetailViewHolder(binding: ItemDetailBinding): RecyclerView.ViewHolder(binding.root) {
        private val itemColor = binding.vItemDetail

        fun bind(data: DetailPalateModel) {
            itemColor.setBackgroundColor(Color.parseColor(data.color))
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DetailPalateModel>() {
            override fun areItemsTheSame(
                oldItem: DetailPalateModel,
                newItem: DetailPalateModel
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: DetailPalateModel,
                newItem: DetailPalateModel
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

}