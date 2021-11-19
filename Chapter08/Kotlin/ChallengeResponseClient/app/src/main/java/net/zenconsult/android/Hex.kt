package net.zenconsult.android

import kotlin.experimental.and

/*
 * Look - it's a disclaimer!
 *
 * Copyright (c) 1996 Widget Workshop, Inc. All Rights Reserved.
 *
 * Permission to use, copy, modify, and distribute this software
 * and its documentation for NON-COMMERCIAL or COMMERCIAL purposes and
 * without fee is hereby granted, provided that this copyright notice is kept
 * intact.
 *
 * WIDGET WORKSHOP MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF
 * THE SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED
 * TO THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A
 * PARTICULAR PURPOSE, OR NON-INFRINGEMENT. WIDGET WORKSHOP SHALL NOT BE LIABLE FOR
 * ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR
 * DISTRIBUTING THIS SOFTWARE OR ITS DERIVATIVES.
 *
 * THIS SOFTWARE IS NOT DESIGNED OR INTENDED FOR USE OR RESALE AS ON-LINE
 * CONTROL EQUIPMENT IN HAZARDOUS ENVIRONMENTS REQUIRING FAIL-SAFE
 * PERFORMANCE, SUCH AS IN THE OPERATION OF NUCLEAR FACILITIES, AIRCRAFT
 * NAVIGATION OR COMMUNICATION SYSTEMS, AIR TRAFFIC CONTROL, DIRECT LIFE
 * SUPPORT MACHINES, OR WEAPONS SYSTEMS, IN WHICH THE FAILURE OF THE
 * SOFTWARE COULD LEAD DIRECTLY TO DEATH, PERSONAL INJURY, OR SEVERE
 * PHYSICAL OR ENVIRONMENTAL DAMAGE ("HIGH RISK ACTIVITIES").  WIDGET WORKSHOP
 * SPECIFICALLY DISCLAIMS ANY EXPRESS OR IMPLIED WARRANTY OF FITNESS FOR
 * HIGH RISK ACTIVITIES.
 */
/* What won't those crazy lawyers think up next? */

class Hex {

    companion object {

        // Converts a string of hex digits into a byte array of those digits
        fun toByteArr(no: String): ByteArray {
            val number = ByteArray(no.length / 2)
            for (i in no.indices step 2) {
                val j = Integer.parseInt(no.substring(i, i + 2), 16)
                number[i / 2] = (j and 0x000000ff).toByte()
            }
            return number
        }

        fun printHex(b: ByteArray?) {
            printHex(b, b?.size)
        }

        fun printHex(b: ShortArray?) {
            printHex(b, b?.size)
        }

        fun printHex(b: IntArray?) {
            printHex(b, b?.size)
        }

        fun printHexZ(b: ByteArray?) {
            System.out.println(toHexZ(b, b?.size))
        }

        fun printHex(label: String, b: ByteArray?) {
            printHex(label, b, b?.size)
        }

        fun printHex(label: String, b: ShortArray?) {
            printHex(label, b, b?.size)
        }

        fun printHex(label: String, b: IntArray?) {
            printHex(label, b, b?.size)
        }

        fun toHexF(label: String, b: ByteArray?): String {
            return toHexF(label, b, b?.size)
        }

        fun toHexF(label: String, b: ShortArray?): String {
            return toHexF(label, b, b?.size)
        }

        fun toHexF(label: String, b: IntArray?): String {
            return toHexF(label, b, b?.size)
        }

        fun toHexF(b: IntArray?): String? {
            return toHexF(b, b?.size)
        }

        fun toHexF(b: ShortArray?): String? {
            return toHexF(b, b?.size)
        }

        fun toHexF(b: ByteArray?): String? {
            return toHexF(b, b?.size)
        }

        fun toHex(b: ByteArray?): String {
            return toHex(b, b?.size)
        }

        fun toHex(b: ShortArray?): String {
            return toHex(b, b?.size)
        }

        fun toHex(b: IntArray?): String {
            return toHex(b, b?.size)
        }

        fun printHex(label: String, b: ByteArray?, len: Int?) {
            println(label)
            printHex(b, len)
        }

        fun printHex(label: String, b: ShortArray?, len: Int?) {
            println(label)
            printHex(b, len)
        }

        fun printHex(label: String, b: IntArray?, len: Int?) {
            println(label)
            printHex(b, len)
        }

        fun printHex(b: ByteArray?, len: Int?) {
            print(toHexF(b, len))
        }

        fun printHex(b: ShortArray?, len: Int?) {
            print(toHexF(b, len))
        }

        fun printHex(b: IntArray?, len: Int?) {
            print(toHexF(b, len))
        }

        fun toHexF(label: String, b: IntArray?, len: Int?): String {
            return "$label\n${toHexF(b, len)}"
        }

        fun toHexF(label: String, b: ShortArray?, len: Int?): String {
            return "$label\n${toHexF(b, len)}"
        }

        fun toHexF(label: String, b: ByteArray?, len: Int?): String {
            return "$label\n${toHexF(b, len)}"
        }

        fun toHexS(b: ByteArray?, len: Int?): String? {
            val s = StringBuffer()
            if (b == null) {
                return null
            }
            for (i in 0 until len!!) {
                s.append("${toHex(b[i])} ")
            }
            if (len % 16 != 0) {
                s.append("\n")
            }
            return s.toString()
        }

        fun toHexF(b: ShortArray?, len: Int?): String? {
            val s = StringBuffer()
            if (b == null) {
                return null
            }
            for (i in 0 until len!!) {
                s.append(" ${toHex(b[i])}")
                if (i % 16 == 7) {
                    s.append("\n")
                } else if (i % 4 == 3) {
                    s.append(" ")
                }
            }
            if (len % 8 != 0) {
                s.append("\n")
            }
            return s.toString()
        }

        fun toHexF(b: IntArray?, len: Int?): String? {
            val s = StringBuffer()
            if (b == null) {
                return null
            }
            for (i in 0 until len!!) {
                s.append(" ${toHex(b[i])}")
                if (i % 4 == 3) {
                    s.append("\n")
                }
            }

            if (len % 4 != 0) {
                s.append("\n")
            }
            return s.toString()
        }

        fun toHexF(b: ByteArray?, len: Int?): String? {
            val s = StringBuffer()
            if (b == null) {
                return null
            }
            for (i in 0 until len!!) {
                s.append(" ${toHex(b[i])}")
                if (i % 16 == 7) {
                    s.append("\n")
                } else if (i % 4 == 3) {
                    s.append(" ")
               }
            }
            if (len % 8 != 0) {
                s.append("\n")
            }
            return s.toString()
        }

        fun toHex(b: IntArray?, len: Int?): String {
            if (b == null) {
                return ""
            }
            val s = StringBuffer()
            for (i in 0 until len!!) {
                s.append(toHex(b[i]))
            }
            return s.toString()
        }

        fun toHex(b: ShortArray?, len: Int?): String {
            if (b == null) {
                return ""
            }
            val s = StringBuffer()
            for (i in 0 until len!!) {
                s.append(toHex(b[i]))
            }
            return s.toString()
        }

        fun toHex(b: ByteArray?, len: Int?): String {
            if (b == null) {
                return ""
            }
            val s = StringBuffer("")
            for (i in 0 until len!!) {
                s.append(toHex(b[i]))
            }
            return s.toString()
        }

        fun toHex(b: Byte): String {
            val i = (b.toInt() shl 24) ushr 24
            if (i < 16) {
                return "0${i.toString(16)}"
            } else {
                return i.toString(16)
            }
        }

        fun toHex(i: Short): String {
            val b = ByteArray(2)
            b[0] = ((i.toInt() and 0x0000ff00) ushr 8).toByte()
            b[1] = ((i.toInt() and 0x000000ff) ushr 8).toByte()
            return toHex(b[0]) + toHex(b[1])
        }

        fun toHex(i: Int): String {
            val b = ByteArray(4)
            b[0] = ((i and 0xff000000.toInt()) ushr 24).toByte()
            b[1] = ((i and 0x00ff0000) ushr 16).toByte()
            b[2] = ((i and 0x0000ff00) ushr 8).toByte()
            b[3] = (i and 0x000000ff).toByte()
            return toHex(b[0]) + toHex(b[1]) +toHex(b[2]) +toHex(b[3]);
        }

        fun toHexZ(b: ByteArray?, len: Int?): String? {
            val s = StringBuffer()
            if (b == null) {
                return null
            }
            for (i in 0 until len!!) {
                s.append(toHex(b[i]))
            }
            return s.toString()
        }
    }
}