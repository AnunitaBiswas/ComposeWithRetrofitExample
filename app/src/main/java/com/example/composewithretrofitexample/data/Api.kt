package com.example.composewithretrofitexample.data

import com.ahmedapps.retrofit.data.model.Products
import retrofit2.http.GET

interface Api {

    @GET("products")
    suspend fun getProductList():Products

    companion object{
        const val BASE_URL = "https://dummyjson.com/"
    }
}