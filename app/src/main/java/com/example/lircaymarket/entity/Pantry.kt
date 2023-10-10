package com.example.lircaymarket.entity

import android.os.Parcel
import android.os.Parcelable

data class Pantry(
    val pantryid: Int,
    //val products: List<Product>,
    val symptoms: List<String>
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        //parcel.createFixedArray(Product,1) ?: emptyList(),
        parcel.createStringArrayList() ?: emptyList()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(pantryid)
        //parcel.writeStringList(products)
        parcel.writeStringList(symptoms)
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
