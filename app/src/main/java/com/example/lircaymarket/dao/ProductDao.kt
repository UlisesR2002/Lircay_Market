package com.example.lircaymarket.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.lircaymarket.entity.Pantry
import com.example.lircaymarket.entity.Product
@Dao
interface ProductDao {
    @Query("SELECT * FROM product")
    fun getAll(): List<Product>

    @Query("SELECT * FROM product WHERE productid IN (:productid)")
    fun loadAllByIds(productid: IntArray): List<Product>

    @Query("SELECT * FROM product WHERE productid = :productid")
    fun getProductByID(productid: Int): Product?

    @Query("SELECT * FROM product WHERE shoppinglist_id = :shoppingListId")
    fun getProductsByShoppingListId(shoppingListId: Int): List<Product>


    @Insert
    fun insertAll(vararg products: Product)
    @Update
    fun update(product: Product)
    @Delete
    fun delete(product: Product)
}