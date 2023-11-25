package com.example.lircaymarket.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.lircaymarket.entity.Pantry

@Dao
interface PantryDao {
    @Query("SELECT * FROM pantry")
    fun getAll(): List<Pantry>

    @Query("SELECT * FROM pantry WHERE pantryid IN (:pantryid)")
    fun loadAllByIds(pantryid: IntArray): List<Pantry>
    @Insert
    fun insertAll(vararg users: Pantry)

    @Delete
    fun delete(pantry: Pantry)
}