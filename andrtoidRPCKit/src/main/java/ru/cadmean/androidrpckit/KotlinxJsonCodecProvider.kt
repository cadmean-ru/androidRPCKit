package ru.cadmean.androidrpckit

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json


public class KotlinxJsonCodecProvider {

    public inline fun <reified T>encode(src: T): ByteArray {
        return Json.encodeToString(src).toByteArray()
    }

    public val contentType: String = "application/json"

    public inline fun <reified T>decode(data: ByteArray): T {
        val str = String(data)
        return Json.decodeFromString(str)
    }
}