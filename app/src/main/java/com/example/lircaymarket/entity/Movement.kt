package com.example.lircaymarket.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
@Entity(
    foreignKeys = [
        ForeignKey(
            entity = User::class,
            parentColumns = ["userid"],
            childColumns = ["user_id"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Product::class,
            parentColumns = ["productid"],
            childColumns = ["product_id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Movement(
    @PrimaryKey(autoGenerate = true) val movementid: Int,
    @ColumnInfo(name = "user_id") var userid: Int?,
    @ColumnInfo(name = "product_id") var productid: Int?,
    @ColumnInfo(name = "movement_type") var movementtype: Int?
)
