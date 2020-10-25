package ru.cadmean.androidrpckit.example

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.NumberFormatException

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val rpcViewModel: RpcViewModel by viewModels()

        call_button.setOnClickListener {
            try {
                val a = edit_a.text.toString().toInt()
                val b = edit_b.text.toString().toInt()
                rpcViewModel.call(a, b)
            } catch (ex: NumberFormatException) {
                Toast.makeText(this, "bruh", Toast.LENGTH_LONG).show()
            }
        }

        rpcViewModel.result.observe(this) {
            call_result_text.text = getString(R.string.call_result, it)
        }

        rpcViewModel.error.observe(this) {
            call_error_text.text = getString(R.string.call_error, it)
        }
    }
}