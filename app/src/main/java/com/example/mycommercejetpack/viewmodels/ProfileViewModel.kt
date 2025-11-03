package com.example.mycommercejetpack.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mycommercejetpack.data.CartItem
import com.example.mycommercejetpack.data.WishListItem
import com.example.mycommercejetpack.singleton.UserSingleTon
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ProfileViewModel @Inject constructor(private val userSingleTon: UserSingleTon) : ViewModel() {


    val user  = userSingleTon.currentUser

    fun addUserDetails(email:String,password:String,phone:String,id:Int){

        user?.let {
            var updated = it.copy(id = id)
            updated = it.copy(email = email)

            viewModelScope.launch {
                userSingleTon.saveUser(updated)
            }

        }

    }


    fun getUserWishList(): StateFlow<List<WishListItem>> {
        val wishlist = userSingleTon.currentUser?.wishList ?: emptyList()
        return MutableStateFlow(wishlist)
    }

    fun getUserCartListItems(): StateFlow<List<CartItem>> {
        val cartItems = user!!.cartItems.toList() ?: emptyList()
        return MutableStateFlow(cartItems)
    }

}