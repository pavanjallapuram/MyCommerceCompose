package com.example.mycommercejetpack.listeners

import com.example.mycommercejetpack.data.Product
import retrofit2.http.GET

interface ProductApiService {

    @GET("products")
    suspend fun getProducts(): List<Product>
}
