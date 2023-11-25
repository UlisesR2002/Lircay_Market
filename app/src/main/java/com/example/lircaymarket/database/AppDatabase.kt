package com.example.lircaymarket.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.lircaymarket.dao.PantryDao
import com.example.lircaymarket.dao.UserDao
import com.example.lircaymarket.dao.ProductDao
import com.example.lircaymarket.entity.User
import com.example.lircaymarket.entity.Pantry
import com.example.lircaymarket.entity.Product

@Database(entities = [User::class, Pantry::class,Product::class], version = 3)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun pantryDao() : PantryDao
    abstract fun productDao(): ProductDao
}