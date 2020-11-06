# cadRPC client library for Android

[![](https://jitpack.io/v/cadmean-ru/androidRPCKit.svg)](https://jitpack.io/#cadmean-ru/androidRPCKit)

cadRPC is an easy-to-use RPC technology. It's goal is to simplify the communication with your web API, hiding all the HTTP and JSON poppycock.

## Installation 

Step 1. Add it in your root build.gradle at the end of repositories:

```groovy
allprojects {
    repositories {
        // ...
        maven { url 'https://jitpack.io' }
    }
}
```
	
Step 2. Add the dependency

```groovy
dependencies {
        implementation 'com.github.cadmean-ru:androidRPCKit:v0.1.1'
}
```

## How to use

An example is worth a thousand words.

```kotlin
val rpc = RpcClient("http://testrpc.cadmean.ru")

rpc.f("sum").call<Int>(1, 68) { res, err ->
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
In this case we are calling the "sum" function at testrpc.cadmean.ru, that takes two integers and returns an integer.
Note, that the [example rpc server](https://github.com/cadmean-ru/ExampleRpcServer) is running at testrpc.cadmean.ru.
You can also use your custom classes as function arguments and return type.

## Known issues

* Date deserialization not working. You should convert it from string manually.

## To be implemented

* Streaming, e. g. web sockets

## Contributing

Feel free to submit issues or create pull requests following fork-and-pull git workflow.

## See also

* [C# client and server library](https://github.com/cadmean-ru/Cadmean.RPC)
* [Python client library](https://github.com/cadmean-ru/pythonRPCKit)
* [Example server](https://github.com/cadmean-ru/ExampleRpcServer)
