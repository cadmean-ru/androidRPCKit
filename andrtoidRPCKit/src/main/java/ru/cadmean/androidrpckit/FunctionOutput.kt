package ru.cadmean.androidrpckit

public class FunctionOutput<TResult>(
    public val error: Int,
    public val result: TResult,
    public val metaData: Map<String, Any>?,
)