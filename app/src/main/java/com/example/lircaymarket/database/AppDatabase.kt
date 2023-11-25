package com.example.lircaymarket.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.lircaymarket.dao.UserDao
import com.example.lircaymarket.dao.ProductDao
import com.example.lircaymarket.entity.User
import com.example.lircaymarket.entity.Product

@Database(entities = [User::class, Product::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun productDao(): ProductDao
}