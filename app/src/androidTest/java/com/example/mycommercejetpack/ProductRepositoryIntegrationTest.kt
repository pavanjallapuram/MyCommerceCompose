package com.example.mycommercejetpack

import android.util.Log
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.mycommercejetpack.listeners.ProductApiService
import com.example.mycommercejetpack.repository.ProductRepository
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class ProductRepositoryIntegrationTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var apiService: ProductApiService

    private lateinit var repository: ProductRepository

    @Before
    fun setup() {
        hiltRule.inject()
        repository = ProductRepository(apiService)
    }

    @Test
    fun fetchProducts_returnsNonEmptyList() = runBlocking {
        val products = repository.fetchProducts()

        // Check that the API actually returned some products
        Assert.assertTrue(products.isNotEmpty())
        Assert.assertEquals(20,products.size)
        Log.e("listSize",products.size.toString())
        println("Fetched ${products.size} products from fakestoreapi.com")
    }
}