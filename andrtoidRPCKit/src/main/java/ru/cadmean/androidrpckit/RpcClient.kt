package ru.cadmean.androidrpckit

public class RpcClient(public val serverUrl: String) {
    public companion object {
        public const val supportedRpcVersion: String = "2.1"
    }

    public val configuration: RpcConfiguration = RpcConfiguration()

    public fun f(functionName: String): Function = Function(functionName, this)
}