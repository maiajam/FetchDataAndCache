package com.example.fetchdataandcache.app.ui

import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fetchdataandcache.R
import com.example.fetchdataandcache.app.adapter.CategoryAdapter
import com.example.fetchdataandcache.app.viewmodel.CategoriesViewModel
import com.example.fetchdataandcache.app.viewmodel.CategoriesViewModelFactory
import com.example.fetchdataandcache.data.remote.ApiService.Companion.getInstance
import com.example.fetchdataandcache.data.repo.CategoryRepo
import com.example.fetchdataandcache.databinding.ActivityMainBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity(), OnClickListener {

    private lateinit var shareButton: FloatingActionButton
    private lateinit var recyclerViewNews: RecyclerView
    private lateinit var binding: ActivityMainBinding
    lateinit var viewModel: CategoriesViewModel
    lateinit var catAdapter: CategoryAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initAndSetupRecyView()
        initShareingB()
    }

    private fun initShareingB() {
        shareButton = binding.shareButton
        shareButton.setOnClickListener(OnClickListener { shareData() })
    }

    private fun shareData() {

        Toast.makeText(baseContext,getString(R.string.sharing_Toast_msg) + viewModel.getTheFirstCategory(),Toast.LENGTH_LONG).show()
    }

    private fun initAndSetupRecyView() {
        recyclerViewNews = binding.CategoriesRecyView
        recyclerViewNews.layoutManager = LinearLayoutManager(this)
        initRecy()
        recyclerViewNews.setHasFixedSize(true)
    }

    private fun initRecy() {
        viewModel =
            ViewModelProvider(
                this,
                CategoriesViewModelFactory(CategoryRepo(getInstance(), baseContext))
            )
                .get(CategoriesViewModel::class.java)

        viewModel.getAllCategories().observe(this, Observer {
            catAdapter = CategoryAdapter(it)
            recyclerViewNews.adapter = catAdapter
            catAdapter.notifyDataSetChanged()
        })
        viewModel.errorMessage.observe(this, Observer {
        })

    }

    override fun onClick(p0: View?) {
        //  var categList = viewModel.getAllCategoriesOffline()
        //  Toast.makeText(
        //    baseContext,
        //  "Hey you have " + categList?.size + " item and I dont know have time to implement sharing on facebook ",
        //  Toast.LENGTH_LONG
        //).show()
    }
}

