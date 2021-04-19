package top.iqqcode.activitydemo

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

/**
 * @Author: iqqcode
 * @Date: 2021-04-18 21:32
 * @Description:
 */
class User() : Parcelable {
    var username: String?  = "android"
    var password: Int = 18

    constructor(parcel: Parcel) : this() {
        username = parcel.readString()
        password = parcel.readInt()
    }

    override fun describeContents(): Int {
        TODO("Not yet implemented")
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(username)
        parcel.writeInt(password)
    }

    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User {
            return User(parcel)
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }
    }
}