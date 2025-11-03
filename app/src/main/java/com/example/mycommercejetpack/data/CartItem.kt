package com.example.mycommercejetpack.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CartItem(var numOfItems:Int,val product: Product ) : Parcelable


@Parcelize
data class WishListItem(val product: Product) : Parcelable