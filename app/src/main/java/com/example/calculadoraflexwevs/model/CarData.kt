package com.example.calculadoraflexwevs.model

import android.os.Parcel
import android.os.Parcelable

data class CarData(
    val gasPrice: Double = 0.0,
    val ethanolPrice: Double = 0.0,
    val gasAverage: Double = 0.0,
    val ethanolAverage: Double = 0.0
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeDouble(gasPrice)
        parcel.writeDouble(ethanolPrice)
        parcel.writeDouble(gasAverage)
        parcel.writeDouble(ethanolAverage)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CarData> {
        override fun createFromParcel(parcel: Parcel): CarData {
            return CarData(parcel)
        }

        override fun newArray(size: Int): Array<CarData?> {
            return arrayOfNulls(size)
        }
    }
}