package com.example.fetchdataandcache.app.viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fetchdataandcache.data.repo.CategoryRepo
import com.example.fetchdataandcache.data.entity.CategoriesItem
import com.example.fetchdataandcache.data.entity.CategoryResponse
import retrofit2.Call
import retrofit2.Callback


class CategoriesViewModel constructor(private val repository: CategoryRepo) : ViewModel() {

    val categoryList = MutableLiveData<List<CategoriesItem>>()
    val errorMessage = MutableLiveData<String>()

   fun getAllCategories(): LiveData<List<CategoriesItem>> = getAllCategoriesOffline()?.let { getAllCategoriesOffline()  }?:getAllCategRemotly()


    private fun getAllCategRemotly() :LiveData<List<CategoriesItem>>{
        val response = repository.getCategoriesFromRemote();

        response.enqueue(object : Callback<CategoryResponse> {
            override fun onResponse(call: Call<CategoryResponse>, response: retrofit2.Response<CategoryResponse>) {
                response.body()?.categories?.let { repository.saveAllCateg(it as List<CategoriesItem>) }
                categoryList.postValue(response.body()?.categories as List<CategoriesItem>?)
            }

            override fun onFailure(call: Call<CategoryResponse>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })

        return categoryList
    }

    private fun getAllCategoriesOffline(): LiveData<List<CategoriesItem>>? = repository.getCategoriesOffline()
    fun getFirstCatagoryThumb() : String? =  repository.getFirstCategory().strCategoryThumb
}

