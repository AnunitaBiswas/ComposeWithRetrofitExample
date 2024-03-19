package com.example.composewithretrofitexample.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahmedapps.retrofit.data.model.Product
import com.example.composewithretrofitexample.data.ApiResult
import com.example.composewithretrofitexample.data.ProductsRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProductViewModel(
    private val productsRepository: ProductsRepository
):ViewModel() {

    private val _products= MutableStateFlow<List<Product>>(emptyList())
    val products=_products.asStateFlow()

    private val _showErrorToastChannel=Channel<Boolean>()
    val showErrorToastChannel=_showErrorToastChannel.receiveAsFlow()

    init {
        viewModelScope.launch {
            productsRepository.getProductList().collectLatest { result->
            when(result){
                is ApiResult.Error ->{
                    _showErrorToastChannel.send(true)
                }
                is ApiResult.Success ->{
                    result.data?.let { products ->
                        _products.update { products }
                    }
                }
            }

            }
        }
    }







}