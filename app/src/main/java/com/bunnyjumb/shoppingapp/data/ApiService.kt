package com.bunnyjumb.shoppingapp.data

import com.bunnyjumb.shoppingapp.data.Model.Products
import retrofit2.http.GET

interface ApiService {
    //https://dummyjson.com/products
    @GET("products")
    suspend fun getProducts(): Products

    companion object{
        const val BASE_URL = "https://dummyjson.com/"
    }
}