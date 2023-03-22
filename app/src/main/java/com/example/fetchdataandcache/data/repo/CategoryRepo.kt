package com.example.fetchdataandcache.data.repo

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.fetchdataandcache.data.local.AppDataBase
import com.example.fetchdataandcache.data.local.CategoryDao
import com.example.fetchdataandcache.data.remote.ApiService
import com.example.fetchdataandcache.data.entity.CategoriesItem

class CategoryRepo constructor(private val apiService: ApiService, context: Context) {

    var db: CategoryDao? = AppDataBase.getInstance(context)?.catDao()


    fun getCategoriesFromRemote() = apiService.getCategories()
    fun getCategoriesOffline(): LiveData<List<CategoriesItem>> = db?.getCategFromRoom()!!
    fun saveAllCateg(catList: List<CategoriesItem>) = db?.insertCategToRoom(catList)
    fun getFirstCategory() :CategoriesItem = db?.getFirstCategory()!!
}