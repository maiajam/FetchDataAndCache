package com.example.fetchdataandcache.app.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fetchdataandcache.app.viewHolder.CategoryViewHolder
import com.example.fetchdataandcache.databinding.CategoryItemBinding
import com.example.fetchdataandcache.data.entity.CategoriesItem

class CategoryAdapter(private val catList: List<CategoriesItem>) : RecyclerView.Adapter<CategoryViewHolder>() {

    private lateinit var binding: CategoryItemBinding


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {

        binding = CategoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(binding)
    }

    override fun getItemCount(): Int {
      return catList.size
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
       holder.bind(catList[position])
    }
}