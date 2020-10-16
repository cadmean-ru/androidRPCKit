# cadRPC client library for Android

cadRPC is an easy-to-use RPC technology.

## How to use

```kotlin
val rpc = RpcClient("http://testrpc.cadmean.ru")

rpc.f("sum").call<Double>(1, 68) { res, err ->
    Log.d("RPC", "Call finished: $res $err")
}
```
First you need to create an instence of `RpcClient(serverUrl: String)` with the url of your RPC 
server. Then you use the `f(functionName: String)` function to obtain a reference of the 
specific function at your server. Finally, you use 
`<TResult> call(vararg functionArguments: Any, onComplete: (TResult?, Int) -> Unit)`  
to call the specified function. The generic argument is the expected result type of the RPC function. 
`functionArguments` are the arguments you pass to the RPC function. 
`onCompplete` is the callback, that is called when the call is finished. 
It's first argument is the result, and the second is the int error code. 

## Known issues

* Numeric results always returned as double. So, for now use `Double` as result type.