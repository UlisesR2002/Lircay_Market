package com.example.lircaymarket.entity

import androidx.room.Room
import com.example.lircaymarket.database.AppDatabase
import android.content.Context

object SaveData
{
    private var appDatabase: AppDatabase? = null
    var Market = arrayListOf<Market>()
    var pantry = arrayListOf<Pantry>()
    var shoppinglist = arrayListOf<Shoppinglist>()

    fun getDatabase(context: Context) : AppDatabase {
        if (appDatabase == null) {
            appDatabase = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java, "database-name"
            ).build()
        }
        return appDatabase!!
    }
}