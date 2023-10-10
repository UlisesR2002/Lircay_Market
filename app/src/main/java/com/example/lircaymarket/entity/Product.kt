package com.example.lircaymarket.entity

import android.os.Parcel
import android.os.Parcelable

data class Product(
    val productid: Int,
    val productname: String?,
    val productcount: Int,
    val productdescription: String?,
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(productid)
        parcel.writeString(productname)
        parcel.writeInt(productcount)
        parcel.writeString(productdescription)
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
