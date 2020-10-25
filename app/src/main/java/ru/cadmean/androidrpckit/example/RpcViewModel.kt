package ru.cadmean.androidrpckit.example

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.cadmean.androidrpckit.RpcClient

class RpcViewModel : ViewModel() {

    private val rpc = RpcClient("http://testrpc.cadmean.ru")

    private val _result: MutableLiveData<Double> = MutableLiveData(0.0)
    val result: LiveData<Double> = _result

    private val _error: MutableLiveData<Int> = MutableLiveData(0)
    val error: LiveData<Int> = _error

    fun call(a: Int, b: Int) {
        rpc.f("sum").call<Double>(a, b) { res, err ->
            Log.d("Main", "Call finished: $res $err")

            _result.postValue(res)
            _error.postValue(err)
        }
    }
}