package com.example.lircaymarket.entity

import android.os.Parcel
import android.os.Parcelable
import java.util.ArrayList

data class Shoppinglist(
    val shoppinglistid: Int,
    var product: Product?


    //val symptoms: List<String>
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readParcelable(Product::class.java.classLoader)
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(shoppinglistid)
        parcel.writeParcelable(product, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Shoppinglist> {
        override fun createFromParcel(parcel: Parcel): Shoppinglist {
            return Shoppinglist(parcel)
        }

        override fun newArray(size: Int): Array<Shoppinglist?> {
            return arrayOfNulls(size)
        }
    }
}
