package net.zenconsult.android.model

import java.lang.StringBuilder

class Location(
    var identifier: String?,
    var latitude: Double = 0.0,
    var longitude: Double = 0.0
) {

    constructor() : this("", 0.0, 0.0)

    override fun toString(): String {
        val ret = StringBuilder()
        ret.append(identifier ?: "")
        ret.append(latitude)
        ret.append(longitude)
        return ret.toString()
    }

    public fun getBytes(): ByteArray {
        return toString().toByteArray()
    }
}