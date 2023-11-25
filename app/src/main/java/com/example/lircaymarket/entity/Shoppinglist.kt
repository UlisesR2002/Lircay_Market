package com.example.lircaymarket.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "shoppinglist",
    foreignKeys = [ForeignKey(
        entity = User::class,
        parentColumns = ["userid"],
        childColumns = ["user_id"],
        onDelete = ForeignKey.CASCADE
    )],
    indices = [Index("user_id")]
)
data class Shoppinglist(
    @PrimaryKey(autoGenerate = true) val shoppinglistid: Int,
    @ColumnInfo(name = "user_id") val userId: Int
)