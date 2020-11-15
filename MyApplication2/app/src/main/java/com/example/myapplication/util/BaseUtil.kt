package com.example.myapplication.util

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import org.json.JSONArray

object BaseUtil {
    public fun parseJSONWithJSONObject(jsonData:String):JSONArray{
        return JSONArray(jsonData);
    }
    private fun charToByte(c: Char): Byte {

        return "0123456789ABCDEF".indexOf(c).toByte()
    }
    fun hexStringToBytes(hexString: String?): ByteArray? {
        var hexString = hexString
        if (hexString == null || hexString == "") {
            return null
        }
        hexString = hexString.toUpperCase()
        val length = hexString.length / 2
        val hexChars = hexString.toCharArray()
        val d = ByteArray(length)
        for (i in 0..length - 1) {
            val pos = i * 2
            d[i] = (charToByte(hexChars[pos]).toInt() shl 4 or charToByte(hexChars[pos + 1]).toInt()).toByte()
        }
        return d
    }
}