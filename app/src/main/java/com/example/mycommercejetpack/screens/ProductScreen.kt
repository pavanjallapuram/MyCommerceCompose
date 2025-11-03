package com.example.mycommercejetpack.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.mycommercejetpack.R
import com.example.mycommercejetpack.data.Product
import com.example.mycommercejetpack.ui.theme.BgGrey
import com.example.mycommercejetpack.ui.theme.BlueCustom
import com.example.mycommercejetpack.viewmodels.ProductsViewModel


@Composable
fun ProductsHomeScreen(
    onProductSelected: (Product) -> Unit ={}, onSettingsSelected: () -> Unit = {},
    onWishListSelected: () -> Unit = {},onCartListSelected: () -> Unit = {},
    myViewModel: ProductsViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit) {
        myViewModel.loadProducts()
    }

    val products by myViewModel.products.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxWidth().statusBarsPadding()) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(65.dp).padding(10.dp,2.dp),
                verticalAlignment = Alignment.CenterVertically // Centers all items vertically
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_profile),
                    contentDescription = "avatar",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(45.dp)
                        .clip(CircleShape)
                        .border(2.dp, BgGrey, CircleShape)
                )

                Box(
                    modifier = Modifier
                        .padding(start = 8.dp) // optional spacing
                        .background(
                            color = BlueCustom,
                            shape = RoundedCornerShape(16.dp)
                        )
                        .padding(horizontal = 15.dp, vertical = 8.dp) // inner padding for text
                ) {
                    Text(
                        text = "MyActivity",
                        color = Color.White,
                        fontSize = 16.sp
                    )
                }


                Spacer(modifier = Modifier.weight(1f)) // Pushes settings icon to end

                Row(verticalAlignment = Alignment.CenterVertically){

                    Image(
                        painter = painterResource(R.drawable.ic_like_wishlist),
                        contentDescription = "settings",
                        modifier = Modifier
                            .size(40.dp)       // Icon size // Center icon inside Box
                            .padding(7.dp,2.dp)     // Padding inside background, only for icon
                            .clickable{
                                onWishListSelected()
                            }
                    )

                    Image(
                        painter = painterResource(R.drawable.ic_cart),
                        contentDescription = "settings",
                        modifier = Modifier
                            .size(40.dp)       // Icon size // Center icon inside Box
                            .padding(7.dp,2.dp)    // Padding inside background, only for icon
                            .clickable{
                                onCartListSelected()
                            }
                    )

                    Image(
                        painter = painterResource(R.drawable.ic_settings),
                        contentDescription = "settings",
                        modifier = Modifier
                            .size(40.dp)
                            .padding(7.dp,2.dp)// Icon size // Center icon inside Box
                            .clickable{
                                onSettingsSelected()
                            }
                    )

                }


            }


            Text(
                "Hello Romania!",
                modifier = Modifier.fillMaxWidth().wrapContentHeight(),
                fontSize = 35.sp
            )
            ProductGridScreen(products,onProductSelected)
        }
    }
}

@Composable
fun ProductGridScreen(products: List<Product>, onProductSelected: (Product) -> Unit) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(products.size) { index ->
            ProductItemScreen(products[index],onProductSelected)
        }
    }
}


@Composable
fun ProductItemScreen(product: Product, onProductSelected: (Product) -> Unit) {
    Column(modifier = Modifier.fillMaxWidth().height(280.dp).clickable{
        onProductSelected(product)
    }) {

        AsyncImage(
            model = product.image,
            contentDescription = "avatar",
            contentScale = ContentScale.Inside,
            modifier = Modifier
                .fillMaxWidth().height(220.dp)
                .clip(RoundedCornerShape(12.dp))
                .border(6.dp, BgGrey, RoundedCornerShape(15.dp))
        )

        Text(product.title, modifier = Modifier.fillMaxWidth().padding(0.dp,1.dp), fontSize = 10.sp, maxLines = 1)

        Text("$"+product.price.toString(), modifier = Modifier.fillMaxWidth().padding(0.dp,2.dp), fontSize = 20.sp, maxLines = 1)

    }
}










