package com.example.mycommercejetpack.singleton

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import com.example.mycommercejetpack.data.CartItem
import com.example.mycommercejetpack.data.User
import com.example.mycommercejetpack.data.WishListItem
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton
import com.google.gson.Gson
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@Singleton
class UserSingleTon @Inject constructor(private val dataStore: DataStore<Preferences>) {

    private val USER_JSON = stringPreferencesKey("user_json")

    val gson = Gson()

     var currentUser: User? = null
        private set

    private val _wishlistFlow = MutableStateFlow<List<WishListItem>>(emptyList())
    val wishlistFlow: StateFlow<List<WishListItem>> get() = _wishlistFlow

    fun checkWishListedItem(productId: Int): Boolean {
        return _wishlistFlow.value.any { it.product.id == productId }
    }

    private val _cartFlow = MutableStateFlow< List<CartItem>>(emptyList())

    val cartFlow: StateFlow<List<CartItem>> get() = _cartFlow





    suspend fun initialize() = withContext(Dispatchers.IO) {
        val userJson = dataStore.data.map { it[USER_JSON] }.first()
        userJson?.let {
            currentUser = gson.fromJson(it, User::class.java)
        }
    }

    // ✅ Save user (to memory + DataStore)
    suspend fun saveUser(user: User) = withContext(Dispatchers.IO) {
        currentUser = user
        val userJson = gson.toJson(user)
        dataStore.edit { it[USER_JSON] = userJson }
    }


    suspend fun addToCart(item: CartItem) {
        currentUser?.let { user ->
            val updatedCart = user.cartItems.toMutableList()
            val existingItem = updatedCart.find { it.product.id == item.product.id }

            if (existingItem != null) {
                existingItem.numOfItems = item.numOfItems+1
            } else {
                updatedCart.add(item)
            }

            currentUser = user.copy(cartItems = updatedCart)
            saveUser(currentUser!!)
        }
    }



    // ✅ Update cart


    suspend fun removeFromCart(item: CartItem) {
        currentUser?.let { user ->
            val updatedCart = user.cartItems.toMutableList()
            val existingItem = updatedCart.find { it.product.id == item.product.id }

            if (existingItem != null && existingItem.numOfItems > 1) {
                existingItem.numOfItems = item.numOfItems-1
            } else {
                if (existingItem != null) {
                    updatedCart.remove(item)
                }
            }

            currentUser = user.copy(cartItems = updatedCart)
            saveUser(currentUser!!)
        }
    }

    // ✅ Update wishlist
    suspend fun addToWishlist(item: WishListItem) {

        currentUser?.let {

            val findedWishListItem = it.wishList.find { it.product.id == item.product.id }

            if (findedWishListItem != null) {
                it.wishList.remove(findedWishListItem)

            }
            else {
                it.wishList.add(item)

            }
            saveUser(it)

        }

    }



    suspend fun removeFromWishlist(productId: Int) {
        currentUser?.wishList?.removeIf { it.product.id == productId }
        currentUser?.let { saveUser(it) }
    }

    // ✅ Clear user on logout
    suspend fun clearUser() = withContext(Dispatchers.IO) {
        dataStore.edit { it.clear() }
        currentUser = null
    }

    fun isLoggedIn(): Boolean = currentUser != null
}