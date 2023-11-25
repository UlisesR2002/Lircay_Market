package com.example.lircaymarket.entity

import android.os.Parcel
import android.os.Parcelable

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "pantry",
    foreignKeys = [ForeignKey(
        entity = User::class,
        parentColumns = ["userid"],
        childColumns = ["user_id"],
        onDelete = ForeignKey.CASCADE
    )],
    indices = [Index("user_id")]
)
data class Pantry(
    @PrimaryKey(autoGenerate = true) val pantryid: Int,
    @ColumnInfo(name = "user_id") val userId: Int
)
