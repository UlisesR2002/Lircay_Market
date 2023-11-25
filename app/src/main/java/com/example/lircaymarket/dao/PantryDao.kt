package com.example.lircaymarket.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.lircaymarket.entity.Pantry
import com.example.lircaymarket.entity.User

@Dao
interface PantryDao {
    @Query("SELECT * FROM pantry")
    fun getAll(): List<Pantry>

    @Query("SELECT * FROM pantry WHERE pantryid IN (:pantryid)")
    fun loadAllByIds(pantryid: IntArray): List<Pantry>

    @Query("SELECT * FROM pantry WHERE user_id = :userid")
    fun getPantryByUserID(userid: Int): Pantry?
    @Insert
    fun insertAll(vararg pantries: Pantry)

    @Delete
    fun delete(pantry: Pantry)
}