package com.example.funbeach

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.i(MainActivity::class.simpleName,"###on Create")

    }

    override fun onStart() {
        super.onStart()
        Toast.makeText(this, R.string.text_hello, Toast.LENGTH_LONG).show()
        Log.i(MainActivity::class.simpleName,"###on Start")
    }

    override fun onResume() {
        super.onResume()
        Log.i(MainActivity::class.simpleName,"###on Resume")
    }

    override fun onPause() {
        super.onPause()
        Log.i(MainActivity::class.simpleName,"###on Pause")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(MainActivity::class.simpleName,"###on Destroy")
    }
}