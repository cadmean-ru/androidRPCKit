package ru.cadmean.androidrpckit

public class DefaultFunctionUrlProvider : FunctionUrlProvider {

    public var prefix: String = "api/rpc"

    override fun getUrl(f: Function): String {
        return "$prefix/${f.name}"
    }
}