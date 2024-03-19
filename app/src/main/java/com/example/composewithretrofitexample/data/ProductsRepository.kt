package com.example.composewithretrofitexample.data

import com.ahmedapps.retrofit.data.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductsRepository {
    suspend fun getProductList():Flow<ApiResult<List<Product>>>
}