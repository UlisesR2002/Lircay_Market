package com.example.lircaymarket.entity

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
@Entity(
    foreignKeys = [
        ForeignKey(
            entity = Pantry::class,
            parentColumns = ["pantryid"],
            childColumns = ["pantry_id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Product(
    @PrimaryKey(autoGenerate = true) val productid: Int,
    @ColumnInfo(name = "productname") var productname: String?,
    @ColumnInfo(name = "productamount") var productamount: Int,
    @ColumnInfo(name = "productdescription") var productdescription: String?,
    @ColumnInfo(name = "productcategory") var productcategory: String?,
    @ColumnInfo(name = "productprice") var productprice: Int,
    @ColumnInfo(name = "pantry_id") var pantryId: Int?
)