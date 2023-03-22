package com.example.fetchdataandcache.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.fetchdataandcache.data.entity.CategoriesItem

@Dao
interface CategoryDao {
    @Query("select * from category_table")
    fun getCategFromRoom(): LiveData<List<CategoriesItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCategToRoom(cars:List<CategoriesItem>)

    @Query("select * from category_table limit 1 " )
     fun getFirstCategory(): CategoriesItem
}
