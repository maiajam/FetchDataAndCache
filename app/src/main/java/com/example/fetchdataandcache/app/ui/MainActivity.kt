package com.example.fetchdataandcache.app.ui

import android.content.Intent
import android.net.Uri
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
import com.facebook.CallbackManager
import com.facebook.FacebookSdk
import com.facebook.share.model.ShareLinkContent
import com.facebook.share.model.ShareModel

import com.facebook.share.widget.ShareDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity(), OnClickListener {

    private lateinit var shareButton: FloatingActionButton
    private lateinit var recyclerViewNews: RecyclerView
    private lateinit var binding: ActivityMainBinding
    lateinit var viewModel: CategoriesViewModel
    lateinit var catAdapter: CategoryAdapter
    lateinit var shareDialog: ShareDialog
    lateinit var callbackManager: CallbackManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        FacebookSdk.sdkInitialize(applicationContext)
        callbackManager = CallbackManager.Factory.create()
        shareDialog = ShareDialog()
        initAndSetupRecyView()
        initShareingB()
    }

    private fun initShareingB() {
        shareButton = binding.shareButton
        shareButton.setOnClickListener(OnClickListener { shareData() })
    }

    private fun shareData() {

        //the code will share the first catagory thumb

        val linkContent = ShareLinkContent.Builder()
            .setContentUrl(Uri.parse(viewModel.getFirstCatagoryThumb()))
            .setQuote("Category Image")
            .build()

        if (ShareDialog.canShow(ShareLinkContent::class.java)) {
            shareDialog.show(linkContent)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        // call the SDK's callbackManager in your onActivityResult to handle the response
        callbackManager.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)

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

