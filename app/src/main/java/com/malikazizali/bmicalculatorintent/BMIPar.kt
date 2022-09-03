package com.malikazizali.bmicalculatorintent

import android.os.Parcel
import android.os.Parcelable

data class BMIPar(val umur : String, val tinggi : String, val berat : String, val bmi : Float, val kategori : String) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readFloat(),
        parcel.readString().toString()
    ) {
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(p0: Parcel?, p1: Int) {
        p0?.writeString(umur)
        p0?.writeString(tinggi)
        p0?.writeString(berat)
        p0?.writeFloat(bmi)
        p0?.writeString(kategori)
    }

    companion object CREATOR : Parcelable.Creator<BMIPar> {
        override fun createFromParcel(parcel: Parcel): BMIPar {
            return BMIPar(parcel)
        }

        override fun newArray(size: Int): Array<BMIPar?> {
            return arrayOfNulls(size)
        }
    }
}