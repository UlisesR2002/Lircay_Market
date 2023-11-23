package com.example.lircaymarket.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.lircaymarket.entity.Product

@Dao
interface ProductDao {
    @Query("SELECT * FROM product")
    fun getAll(): List<Product>

    @Query("SELECT * FROM product WHERE productid IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<Product>

    @Insert
    fun insertAll(vararg product: Product)

    @Delete
    fun delete(product: Product)
}