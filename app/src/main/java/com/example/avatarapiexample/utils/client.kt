package com.example.avatarapiexample.utils

import android.content.Context
import io.appwrite.Client

object Client {
    lateinit var client : Client

    fun create(context: Context) {
        client = Client(context)
            // Replace with your own endpoint and project ID
            .setEndpoint("https://cloud.appwrite.io/v1")
            .setProject("6538106bab8555f99252")
    }
}