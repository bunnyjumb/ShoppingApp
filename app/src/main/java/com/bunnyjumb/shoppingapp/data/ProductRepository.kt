package com.bunnyjumb.shoppingapp.data

import com.bunnyjumb.shoppingapp.data.Model.Product
import com.bunnyjumb.shoppingapp.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ProductRepository(
    private val apiService: ApiService

) {
    suspend fun getProducts(): Flow<Result<List<Product>>> {
        return flow{
            val productResponse =
                try {
                    apiService.getProducts()
                }catch (e: Exception){
                    e.printStackTrace()
                    emit(Result.Error(message = "Failed to fetch data"))
                    return@flow
                }
            emit(Result.Success(productResponse.products))
        }
    }
}
