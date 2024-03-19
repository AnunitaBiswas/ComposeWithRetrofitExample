package com.example.composewithretrofitexample.data

import com.ahmedapps.retrofit.data.model.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class ProductsRepositoryImpl(private val api:Api):ProductsRepository {
    override suspend fun getProductList(): Flow<ApiResult<List<Product>>> {
        return flow {
            val productsFromApi=try {
                api.getProductList()

            }catch (e:IOException){
                e.printStackTrace()
                emit(ApiResult.Error(message = "Error Loading products"))
                return@flow
            }catch (e:HttpException){
                e.printStackTrace()
                emit(ApiResult.Error(message = "Error Loading products"))
                return@flow
            }catch (e:Exception){
                e.printStackTrace()
                emit(ApiResult.Error(message = "Error Loading products"))
                return@flow
            }

           emit(ApiResult.Success(productsFromApi.products))
        }


    }
}