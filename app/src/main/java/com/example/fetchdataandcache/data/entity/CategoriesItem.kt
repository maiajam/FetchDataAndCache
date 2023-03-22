package com.example.fetchdataandcache.data.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName="category_table")
    data class CategoriesItem(
         @ColumnInfo(name="strCategory")
        val strCategory: String? = null,
         @ColumnInfo(name="strCategoryDesc")
        val strCategoryDescription: String? = null,
        @PrimaryKey(autoGenerate = false)
        val idCategory: String,
         @ColumnInfo(name="strCategoryThumb")
        val strCategoryThumb: String? = null
    )
