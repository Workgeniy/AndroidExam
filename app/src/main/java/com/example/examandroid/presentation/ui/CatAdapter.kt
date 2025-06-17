package com.example.examandroid.presentation.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.examandroid.databinding.ItemCatBinding
import com.example.examandroid.domein.model.CatImage


class CatAdapter(
    private val onFavoriteToggle: ((CatImage, Boolean) -> Unit)? = null
) : ListAdapter<CatImage, CatAdapter.CatViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatViewHolder {
        val binding = ItemCatBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CatViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class CatViewHolder(private val binding: ItemCatBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(cat: CatImage) {
            binding.catImageView.load(cat.url)

            binding.favoriteButton.setImageResource(
                if (cat.isFavorite) android.R.drawable.btn_star_big_on
                else android.R.drawable.btn_star_big_off
            )

            binding.favoriteButton.setOnClickListener {
                try {
                    onFavoriteToggle?.invoke(cat, !cat.isFavorite)
                } catch (e: Exception) {
                    e.printStackTrace()
                    Toast.makeText(binding.root.context, "Ошибка добавления в избранное", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<CatImage>() {
        override fun areItemsTheSame(oldItem: CatImage, newItem: CatImage) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: CatImage, newItem: CatImage) = oldItem == newItem
    }
}