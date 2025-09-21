package com.example.mycommercejetpack.repository

import com.example.mycommercejetpack.listeners.ProductApiService
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val apiService: ProductApiService
) {
    suspend fun fetchProducts() = apiService.getProducts()
}