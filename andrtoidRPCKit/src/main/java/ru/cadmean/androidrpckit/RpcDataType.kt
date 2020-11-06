package ru.cadmean.androidrpckit

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public enum class RpcDataType(public val strCode: String) {
    @SerialName("int") Integer("int"),
    @SerialName("float") Float("float"),
    @SerialName("bool") Boolean("bool"),
    @SerialName("string") Str("string"),
    @SerialName("date") Date("date"),
    @SerialName("list") List("list"),
    @SerialName("object") Object("object"),
    @SerialName("auth") Auth("auth"),
    @SerialName("null") Null("null"),
}