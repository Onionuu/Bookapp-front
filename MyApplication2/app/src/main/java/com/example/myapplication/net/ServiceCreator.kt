package com.example.myapplication.net

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceCreator {//127.0.0.110.0.2.2
    private const val BASE_URL="http://10.0.2.2:8080/app_war_exploded/"
    private val retrofit=Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    fun <T> create(serviceClass: Class<T>):T= retrofit.create(serviceClass)
}