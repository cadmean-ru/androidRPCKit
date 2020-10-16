package ru.cadmean.androidrpckit

public class RpcClient(public val serverUrl: String) {
    public val configuration: RpcConfiguration = RpcConfiguration()

    public fun f(functionName: String): Function = Function(functionName, this)
}