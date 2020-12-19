package com.example.myapplication

import android.app.Application
import android.content.Context
import android.net.Uri
import java.net.URI

class MyApplication :Application(){
    companion object {
        lateinit var context:Context
        lateinit var client: JWebSocketClient
    }

    override fun onCreate() {
        super.onCreate()
        context=applicationContext
        client= JWebSocketClient(URI("ws://localhost:8888"))
        client.connectBlocking()
    }

}