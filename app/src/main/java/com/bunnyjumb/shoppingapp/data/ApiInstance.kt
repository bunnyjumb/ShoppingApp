package com.bunnyjumb.shoppingapp.data

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.jvm.java

object ApiInstance {
    private val client = OkHttpClient.Builder().build()
    val api: ApiService = Retrofit.Builder().baseUrl(ApiService.BASE_URL).client(
        client
    ).addConverterFactory(GsonConverterFactory.create()).build().create(ApiService::class.java)
}