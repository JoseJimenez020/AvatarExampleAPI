package com.example.avatarapiexample

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.lifecycle.lifecycleScope
import io.appwrite.Client
import io.appwrite.services.Avatars
import kotlinx.coroutines.launch
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import java.net.URL

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val client = Client(this)
            .setEndpoint("https://cloud.appwrite.io/v1") // Tu endpoint de API
            .setProject("5df5acd0d48c2") // Tu ID de proyecto

        val avatars = Avatars(client)
        val icon = findViewById<ImageView>(R.id.icon)

        val scope = lifecycleScope

        scope.launch {
            try {
                val response = avatars.getBrowser(code = "aa")
                val bitmap = fetchImage(response["file"])
                withContext(Dispatchers.Main) {
                    icon.setImageBitmap(bitmap)
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    private suspend fun fetchImage(url: Byte): Bitmap? {
        return withContext(Dispatchers.IO) {
            try {
                val inputStream = URL(url.toString()).openStream()
                BitmapFactory.decodeStream(inputStream)
            } catch (e: IOException) {
                e.printStackTrace()
                null
            }
        }
    }
}


