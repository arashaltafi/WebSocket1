package com.arash.altafi.websocket1

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.arash.altafi.websocket1.databinding.ActivityMainBinding
import com.arash.altafi.websocket1.model.ResponseGson
import com.arash.altafi.websocket1.utils.WebSocketClient
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.squareup.moshi.Moshi

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private var webSocket: WebSocketClient? = null
    private val moshi = Moshi.Builder().build()
    private val gson = Gson()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        init()
    }

    private fun init() {
        webSocket = WebSocketClient(WEB_SOCKET_URL) { message, isSuccess ->
            runOnUiThread {
                // Update textview with incoming message
                if (isSuccess)
                    setUpBtcPriceText(message)
                else
                    showErrorMessage(message)
                Log.i(TAG, "message: $message")
            }
        }
        webSocket?.connect()
        sendMessage(webSocket)
    }

    private fun sendMessage(webSocket: WebSocketClient?) {
        webSocket?.send(
            "{\n" +
                    "  \"event\": \"bts:subscribe\",\n" +
                    "  \"data\": {\n" +
                    "    \"channel\": \"live_trades_btcusd\"\n" +
                    "  }\n" +
                    "}"
        )
    }

    @SuppressLint("SetTextI18n")
    private fun setUpBtcPriceText(message: String?) {
        message?.let {
            //Moshi
//            val adapter: JsonAdapter<ResponseMoshi> = moshi.adapter(ResponseMoshi::class.java)
//            val bitcoinMoshi = adapter.fromJson(message)

            //Gson
            val bitcoinGson =
                gson.fromJson<ResponseGson>(message, object : TypeToken<ResponseGson>() {}.type)

            //show in ui
            runOnUiThread {
//                binding.tvBtc.text = "1 BTC: ${bitcoinMoshi?.data?.price}"
                binding.tvBtc.text = "1 BTC: ${bitcoinGson?.data?.price}"
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun showErrorMessage(message: String?) {
        runOnUiThread {
            binding.tvBtc.text = "Failure: $message"
        }
    }

    override fun onPause() {
        super.onPause()
        webSocket?.disconnect()
    }

    companion object {
        const val WEB_SOCKET_URL = "wss://ws.bitstamp.net"
        const val TAG = "WebSocketClient"
    }

}