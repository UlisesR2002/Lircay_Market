package com.example.lircaymarket.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.lircaymarket.dao.PantryDao
import com.example.lircaymarket.dao.ShoppinglistDao
import com.example.lircaymarket.dao.UserDao
import com.example.lircaymarket.dao.ProductDao
import com.example.lircaymarket.entity.User
import com.example.lircaymarket.entity.Pantry
import com.example.lircaymarket.entity.Product
import com.example.lircaymarket.entity.Shoppinglist

@Database(entities = [User::class, Pantry::class, Shoppinglist::class, Product::class], version = 4)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun pantryDao() : PantryDao
    abstract fun shoppinglistDao() : ShoppinglistDao
    abstract fun productDao(): ProductDao
}