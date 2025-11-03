package com.example.mycommercejetpack.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    val id: Int = 0,
    val email: String? = null,
    val password: String? = null,
    var cartItems: MutableList<CartItem> = mutableListOf(),
    var wishList: MutableList<WishListItem> = mutableListOf()
) : Parcelable
