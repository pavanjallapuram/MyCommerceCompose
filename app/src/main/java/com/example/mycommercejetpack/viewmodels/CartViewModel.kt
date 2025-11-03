package com.example.mycommercejetpack.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mycommercejetpack.data.CartItem
import com.example.mycommercejetpack.singleton.UserSingleTon
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CartViewModel @Inject constructor(private val userSingleTon: UserSingleTon) : ViewModel() {

    val user get() = userSingleTon.currentUser

    private val _totalCartPrice = MutableStateFlow(getTotalCartPriceList())

    // Publicly exposed as immutable Flow
    val totalCartPrice: StateFlow<Double> = _totalCartPrice


     fun addCartItem(item: CartItem)  {
         viewModelScope.launch {
             userSingleTon.addToCart(item)
             _totalCartPrice.value = getTotalCartPriceList()
         }
    }

     fun numberOfItems(item: CartItem): Int? {
        return userSingleTon.currentUser!!.cartItems
            .find { it.product.id == item.product.id }?.numOfItems // or some Int value
    }

    fun removeCartItem(item: CartItem) {
        viewModelScope.launch {
            userSingleTon.removeFromCart(item)
            _totalCartPrice.value = getTotalCartPriceList()
        }
    }

    fun getCartListItems() = userSingleTon.cartFlow

    fun getTotalCartPriceList(): Double{

        return user?.cartItems!!.sumOf {
            (it.product.price?.toDouble() ?: 0.0) * it.numOfItems.toDouble()
        }

    }


}