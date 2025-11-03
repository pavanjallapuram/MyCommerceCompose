package com.example.mycommercejetpack

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.mycommercejetpack.data.Product
import com.example.mycommercejetpack.data.Rating
import com.example.mycommercejetpack.listeners.ProductApiService
import com.example.mycommercejetpack.repository.ProductRepository
import com.google.gson.Gson
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject


@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class ProductRepositoryTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var apiService: ProductApiService
    @Inject lateinit var mockWebServer: MockWebServer

    private lateinit var repository: ProductRepository

    @Before
    fun setup() {
        hiltRule.inject()
        repository = ProductRepository(apiService)
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }

    @Test
    fun fetchProducts_returnsParsedList() = runBlocking {
        val mockProducts = listOf(
            Product(
                id = 1,
                title = "Laptop",
                price = 1200.0,
                description = "High-end laptop",
                category = "Electronics",
                image = "https://example.com/laptop.png",
                rating = Rating(4.5, 100)
            )
        )

        val mockJson = Gson().toJson(mockProducts)
        mockWebServer.enqueue(MockResponse().setBody(mockJson).setResponseCode(200))

        val result = repository.fetchProducts()

        Assert.assertEquals(1, result.size-19)

    }
}