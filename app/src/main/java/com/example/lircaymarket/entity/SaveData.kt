package com.example.lircaymarket.entity

import androidx.room.Room
import com.example.lircaymarket.database.AppDatabase
import android.content.Context
import com.example.lircaymarket.dao.ProductDao

object SaveData {
    private var appDatabase: AppDatabase? = null
    private var _productDao: ProductDao? = null

    val productDao: ProductDao
        get() {
            if (_productDao == null) {
                throw IllegalStateException("ProductDao has not been initialized")
            }
            return _productDao!!
        }

    fun getDatabase(context: Context): AppDatabase {
        if (appDatabase == null) {
            appDatabase = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java, "database-name"
            ).build()

            // Inicializa _productDao después de construir la base de datos
            _productDao = appDatabase!!.productDao()
        }
        return appDatabase!!
    }
}