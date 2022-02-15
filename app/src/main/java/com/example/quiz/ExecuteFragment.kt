package com.example.quiz

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import okhttp3.Call
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okio.IOException
import java.util.concurrent.Callable
import java.util.concurrent.Executors
import java.util.concurrent.Future

class ExecuteFragment : Fragment(R.layout.fragment_execute) {

    private val http by lazy { OkHttpClient() }

    private val executor by lazy { Executors.newSingleThreadExecutor() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tv = view.findViewById<TextView>(R.id.executeTV)

        val request = Request.Builder()
            .url("https://picsum.photos/seed/picsum/200/300")
            .build()

        try {
            val future: Future<String?> = executor.submit(object : Callable<String?> {
                override fun call(): String? {
                    val newCall : Call = http.newCall(request)
                    val response: Response = newCall.execute()
                    return response.body?.string()
                }

            })
            val data: String? = future.get()
            tv.text = data
        } catch (e: IOException){
            tv.text = e.message
        }
    }

}