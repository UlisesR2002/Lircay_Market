package com.example.lircaymarket.entity

import android.os.Parcel
import android.os.Parcelable

data class Product(
    val productid: Int,
    var productname: String?,
    var productamount: Int,
    var productdescription: String?,
    var productcategory: String?,
    var productprice: Int
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
