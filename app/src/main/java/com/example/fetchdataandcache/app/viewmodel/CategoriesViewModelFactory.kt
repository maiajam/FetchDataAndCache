package com.example.fetchdataandcache.app.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.fetchdataandcache.data.repo.CategoryRepo


class CategoriesViewModelFactory constructor(private val repository: CategoryRepo): ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return if (modelClass.isAssignableFrom(CategoriesViewModel::class.java)) {
                CategoriesViewModel(this.repository) as T
            } else {
                throw IllegalArgumentException("ViewModel Not Found")
            }
        }
    }

