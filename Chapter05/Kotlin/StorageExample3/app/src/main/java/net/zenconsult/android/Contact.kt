package net.zenconsult.android

import java.lang.StringBuilder

class Contact(
    public var firstName: String = "",
    public var lastName: String = "",
    public var address1: String = "",
    public var address2: String = "",
    public var email: String = "",
    public var phone: String = ""
) {

    fun getBytes(): ByteArray {
        return toString().toByteArray()
    }

    override fun toString(): String {
        val ret = StringBuilder()
        ret.apply {
            append(firstName + "|")
            append(lastName + "|")
            append(address1 + "|")
            append(address2 + "|")
            append(email + "|")
            append(phone + "|")
        }
        return ret.toString()
    }
}