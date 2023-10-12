package com.example.lircaymarket.entity

import android.os.Parcel
import android.os.Parcelable
import java.util.ArrayList

data class Pantry(
    val pantryid: Int,
    val products: ArrayList<Product>?


    //val symptoms: List<String>
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.createTypedArrayList(Product.CREATOR)
        //parcel.readArrayList(Product),

        //parcel.createStringArrayList() ?: emptyList()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(pantryid)
        parcel.writeTypedList(products)
        //parcel.writeStringList(products)

       // parcel.writeStringList(symptoms)
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
