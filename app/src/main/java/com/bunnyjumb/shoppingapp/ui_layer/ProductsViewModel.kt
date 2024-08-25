package com.bunnyjumb.shoppingapp.ui_layer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bunnyjumb.shoppingapp.data.Model.Product
import com.bunnyjumb.shoppingapp.data.ProductRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class ProductsViewModel(private val productRepository: ProductRepository): ViewModel() {
    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products = _products.asStateFlow()

    private val _showError = Channel<Boolean>()
    val showError = _showError.receiveAsFlow()

    init {
        viewModelScope.launch(){
            productRepository.getProducts().collectLatest {
                when (it){
                    is com.bunnyjumb.shoppingapp.Result.Error -> {

                    }
                    is com.bunnyjumb.shoppingapp.Result.Success -> {
                        it.data?.let {
                            _products.update {
                                it
                            }
                        }
                    }
                }
            }
        }
    }
}