package ru.cadmean.androidrpckit.example

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ContextAmbient
import androidx.compose.ui.platform.setContent
import androidx.ui.tooling.preview.Preview
import ru.cadmean.androidrpckit.RpcClient
import ru.cadmean.androidrpckit.TransportException
import ru.cadmean.androidrpckit.example.ui.AndroidRPCKitExampleTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainView()
        }
    }
}

@Composable fun MainView() {
    AndroidRPCKitExampleTheme {
        // A surface container using the 'background' color from the theme
        Surface(color = MaterialTheme.colors.background) {
            Column {
                Greeting("Android")
                RpcView()
            }
        }
    }
}

@Composable fun RpcView() {
    val rpc = RpcClient("http://testrpc.cadmean.ru")
    Button(onClick = {
        rpc.f("sum").call<Double>(1, 68) { res, err ->
            Log.d("Main", "Call finished: $res $err")
        }
    }) {
        Text("RPC taim")
    }
}

@Composable fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable fun DefaultPreview() {
    MainView()
}