package com.example.lircaymarket.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.lircaymarket.entity.Product
@Dao
interface ProductDao {
    @Query("SELECT * FROM product")
    fun getAll(): List<Product>

    @Query("SELECT * FROM product WHERE productid IN (:productid)")
    fun loadAllByIds(productid: IntArray): List<Product>
    @Insert
    fun insertAll(vararg users: Product)

    @Delete
    fun delete(product: Product)
}