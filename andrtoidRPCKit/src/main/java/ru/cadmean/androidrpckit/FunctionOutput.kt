package ru.cadmean.androidrpckit

import kotlinx.serialization.Serializable

@Serializable
public class FunctionOutput<out TResult>(
    public val error: Int,
    public val result: TResult?,
    public val metaData: Map<String, String?>?,
)