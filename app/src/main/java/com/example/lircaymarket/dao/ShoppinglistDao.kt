package com.example.lircaymarket.dao
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.lircaymarket.entity.Pantry
import com.example.lircaymarket.entity.Shoppinglist

@Dao
interface ShoppinglistDao {
    @Query("SELECT * FROM shoppinglist")
    fun getAll(): List<Shoppinglist>

    @Query("SELECT * FROM shoppinglist WHERE shoppinglistid IN (:shoppinglistid)")
    fun loadAllByIds(shoppinglistid: IntArray): List<Shoppinglist>

    @Query("SELECT * FROM shoppinglist WHERE user_id = :userid")
    fun getShoppinglistByUserID(userid: Int): Shoppinglist?
    @Insert
    fun insertAll(vararg shoppinglist: Shoppinglist)

    @Delete
    fun delete(shoppinglist: Shoppinglist)
}