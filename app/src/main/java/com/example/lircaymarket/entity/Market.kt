package com.example.lircaymarket.entity

import android.os.Parcel
import android.os.Parcelable

data class Market(
    val marketid: Int,
    var marketname: String?,
    var marketdescription: String?,
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(marketid)
        parcel.writeString(marketname)
        parcel.writeString(marketdescription)
    }

    override fun describeContents(): Int {
        return 0
    }


    companion object CREATOR : Parcelable.Creator<Market> {
        override fun createFromParcel(parcel: Parcel): Market {
            return Market(parcel)
        }

        override fun newArray(size: Int): Array<Market?> {
            return arrayOfNulls(size)
        }
    }
}
