package com.example.lircaymarket.entity

import android.os.Parcel
import android.os.Parcelable

data class Product(
    val productid: Int,
    val productname: String?,
    var productamount: Int,
    val productdescription: String?,
    val productcategory: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(productid)
        parcel.writeString(productname)
        parcel.writeInt(productamount)
        parcel.writeString(productdescription)
        parcel.writeString(productcategory)
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
