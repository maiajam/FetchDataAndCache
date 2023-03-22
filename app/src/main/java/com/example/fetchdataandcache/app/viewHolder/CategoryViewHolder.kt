package com.example.fetchdataandcache.app.viewHolder

import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.fetchdataandcache.databinding.CategoryItemBinding
import com.example.fetchdataandcache.data.entity.CategoriesItem

class CategoryViewHolder(private val itemBinding: CategoryItemBinding) : ViewHolder(itemBinding.root) {

    fun bind(item: CategoriesItem)
    {
        itemBinding.category = item
    }
}