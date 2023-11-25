package com.example.lircaymarket.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.lircaymarket.dao.MovementDao
import com.example.lircaymarket.dao.PantryDao
import com.example.lircaymarket.dao.ShoppinglistDao
import com.example.lircaymarket.dao.UserDao
import com.example.lircaymarket.dao.ProductDao
import com.example.lircaymarket.entity.User
import com.example.lircaymarket.entity.Pantry
import com.example.lircaymarket.entity.Shoppinglist
import com.example.lircaymarket.entity.Product
import com.example.lircaymarket.entity.Movement


@Database(entities = [User::class, Pantry::class, Shoppinglist::class, Product::class, Movement::class], version = 5)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun pantryDao() : PantryDao
    abstract fun shoppinglistDao() : ShoppinglistDao
    abstract fun productDao(): ProductDao
    abstract fun movementDao(): MovementDao
}