package com.example.myapplication.api

import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @Multipart
    @POST("publishServlet")
    fun postPublishRequest(@PartMap partMap:@JvmSuppressWildcards Map<String,RequestBody>, @Part files:List<MultipartBody.Part>): Call<ResponseBody>

    @GET("SearchServlet")
    fun getSearchResult(@Query("words") words:String):Call<ResponseBody>
}