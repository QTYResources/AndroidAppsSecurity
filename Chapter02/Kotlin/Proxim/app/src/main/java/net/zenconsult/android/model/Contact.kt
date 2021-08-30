package net.zenconsult.android.model

import java.lang.StringBuilder

class Contact(
    var firstName: String?,
    var lastName: String?,
    var address1: String?,
    var address2: String?,
    var email: String?,
    var phone: String?
) {

    constructor() : this("", "", "", "", "", "")

    override fun toString(): String {
        val ret = StringBuilder()
        ret.append(firstName ?: "")
        ret.append("|")
        ret.append(lastName ?: "")
        ret.append("|")
        ret.append(address1 ?: "")
        ret.append("|")
        ret.append(address2 ?: "")
        ret.append("|")
        ret.append(email ?: "")
        ret.append("|")
        ret.append(phone ?: "")
        ret.append("|")
        return ret.toString()
    }

    public fun getBytes(): ByteArray {
        return toString().toByteArray()
    }
}