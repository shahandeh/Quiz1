package com.example.quiz

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import okhttp3.*
import okio.IOException

class EnqueueFragment : Fragment(R.layout.fragment_enqueue) {

    private val http by lazy { OkHttpClient() }

    lateinit var tv: TextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tv = view.findViewById(R.id.enqueueTV)

        val request = Request.Builder()
            .url("https://picsum.photos/seed/picsum/200/300")
            .build()

        val newCall: Call = http.newCall(request)
        newCall.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                setResult(e.message)
            }

            override fun onResponse(call: Call, response: Response) {
                setResult(response.body?.string())
            }
        })
    }
    private fun setResult(data: String?) {
        Handler(Looper.getMainLooper()).post {
            tv.text = data
        }
    }
}