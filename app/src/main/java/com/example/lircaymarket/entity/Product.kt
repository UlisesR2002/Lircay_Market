package com.example.lircaymarket.entity

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Product(
    @PrimaryKey(autoGenerate = true) val productid: Int,
    @ColumnInfo(name = "productname") var productname: String?,
    @ColumnInfo(name = "productamount") var productamount: Int,
    @ColumnInfo(name = "productdescription") var productdescription: String?,
    @ColumnInfo(name = "productcategory") var productcategory: String?,
    @ColumnInfo(name = "productprice") var productprice: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(productid)
        parcel.writeString(productname)
        parcel.writeInt(productamount)
        parcel.writeString(productdescription)
        parcel.writeString(productcategory)
        parcel.writeInt(productprice)
    }

    override fun describeContents(): Int {
        return 0
    }


    companion object CREATOR : Parcelable.Creator<Product> {
        override fun createFromParcel(parcel: Parcel): Product {
            return Product(parcel)
        }

        override fun newArray(size: Int): Array<Product?> {
            return arrayOfNulls(size)
        }
    }
}
