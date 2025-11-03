package com.example.mycommercejetpack.viewmodels

import androidx.lifecycle.ViewModel
import com.example.mycommercejetpack.data.Product
import com.example.mycommercejetpack.data.Rating
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class FakeProductsViewModel : ViewModel() {
    private val _products = MutableStateFlow(
        listOf(
            Product(
                id = 1,
                title = "Wireless Headphones",
                price = 199.99,
                description = "Noise-cancelling over-ear headphones.",
                category = "Electronics",
                image = "https://picsum.photos/200/300",
                rating = Rating(4.5, 120)
            ),
            Product(
                id = 2,
                title = "Smart Watch",
                price = 149.99,
                description = "Track fitness and notifications.",
                category = "Accessories",
                image = "https://picsum.photos/200/301",
                rating = Rating(4.2, 80)
            ),
            Product(
                id = 3,
                title = "Bluetooth Speaker",
                price = 89.99,
                description = "Portable speaker with deep bass.",
                category = "Audio",
                image = "https://picsum.photos/200/302",
                rating = Rating(4.6, 150)
            )
        )
    )
    val products: StateFlow<List<Product>> = _products

    fun loadProducts() {} // no-op for preview
}