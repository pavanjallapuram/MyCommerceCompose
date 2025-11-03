package com.example.mycommercejetpack.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mycommercejetpack.data.WishListItem
import com.example.mycommercejetpack.singleton.UserSingleTon
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class WishListViewModel @Inject constructor(val userSingleTon: UserSingleTon) : ViewModel(){

    val user get() = userSingleTon.currentUser

    val wishlistFlow = userSingleTon.wishlistFlow


    fun checkWishListedItem(productId: Int): Boolean {
        return userSingleTon.checkWishListedItem(productId)
    }

    suspend fun toggleWishlist(item: WishListItem) {
        if (checkWishListedItem(item.product.id)) {
            userSingleTon.removeFromWishlist(item.product.id)
        } else {
            userSingleTon.addToWishlist(item)
        }
    }





    fun addWishListItem(item: WishListItem) {
        viewModelScope.launch {
            userSingleTon.addToWishlist(item)
        }
    }

    fun isItemWishListed(productId: Int): StateFlow<Boolean> {
        return userSingleTon.wishlistFlow
            .map { list -> list.any { it.product.id == productId } }
            .stateIn(viewModelScope, SharingStarted.Eagerly, false)
    }
}