package ru.cadmean.androidrpckit

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Serializable
public class FunctionCall(
        public val args: Array<@Serializable(with = CallArgumentsSerializer::class) Any?>,
        public val auth: String? = null,
)