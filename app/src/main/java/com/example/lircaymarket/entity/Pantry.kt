package com.example.lircaymarket.entity

import android.os.Parcel
import android.os.Parcelable
import java.util.ArrayList

data class Pantry(
    val pantryid: Int,
    var product: Product?

) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readParcelable(Product::class.java.classLoader)
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(pantryid)
        parcel.writeParcelable(product, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Pantry> {
        override fun createFromParcel(parcel: Parcel): Pantry {
            return Pantry(parcel)
        }

        override fun newArray(size: Int): Array<Pantry?> {
            return arrayOfNulls(size)
        }
    }
}
