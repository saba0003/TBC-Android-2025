package com.example.tbc_android_2025

import android.os.Parcel
import android.os.Parcelable
import java.text.SimpleDateFormat
import java.util.*

data class User(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val birthday: String,
    val address: String,
    val email: String
) : Parcelable {

    constructor(parcel: Parcel) : this(
        id = parcel.readInt(),
        firstName = parcel.readString() ?: "",
        lastName = parcel.readString() ?: "",
        birthday = parcel.readString() ?: "",
        address = parcel.readString() ?: "",
        email = parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(firstName)
        parcel.writeString(lastName)
        parcel.writeString(birthday)
        parcel.writeString(address)
        parcel.writeString(email)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User = User(parcel)
        override fun newArray(size: Int): Array<User?> = arrayOfNulls(size)

        private val displayFormat = SimpleDateFormat("dd/MMM/yyyy", Locale.ENGLISH)

        fun millisToDisplay(millisString: String): String {
            return try {
                val ms = millisString.toLong()
                displayFormat.format(Date(ms))
            } catch (_: Exception) {
                millisString
            }
        }

        fun displayToMillis(display: String): String {
            return try {
                val d = displayFormat.parse(display) ?: return display
                d.time.toString()
            } catch (_: Exception) {
                display
            }
        }
    }

    fun birthdayDisplay(): String = millisToDisplay(millisString = birthday)
}
