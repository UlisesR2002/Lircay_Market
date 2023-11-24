package com.example.lircaymarket.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.lircaymarket.dao.UserDao
import com.example.lircaymarket.entity.User

@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}