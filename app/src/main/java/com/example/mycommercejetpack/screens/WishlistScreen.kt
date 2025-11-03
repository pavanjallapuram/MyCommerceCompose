package com.example.mycommercejetpack.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.mycommercejetpack.data.Product
import com.example.mycommercejetpack.data.WishListItem
import com.example.mycommercejetpack.ui.theme.ralewayBold
import com.example.mycommercejetpack.viewmodels.ProfileViewModel

@Preview
@Composable
fun WishListScreen(profileViewModel: ProfileViewModel = hiltViewModel(),onProductSelected:(Product) -> Unit = {}){

    val wishListItems = profileViewModel.getUserWishList().collectAsState()

    Box(modifier = Modifier.fillMaxSize().statusBarsPadding()){
        Column(modifier = Modifier.fillMaxSize()){

            Text(text = "Recently WishListed",
                modifier = Modifier.wrapContentSize().padding(10.dp,10.dp),
                fontFamily = ralewayBold, color = Color.Black, fontSize = 28.sp
            )

            LazyVerticalGrid(columns = GridCells.Fixed(2),modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)){

                items(wishListItems.value.size){ item ->

                    WishListItemPreview(wishListItems.value[item],onProductSelected)

                }

            }

        }
    }

}



@Composable
fun WishListItemPreview(wishListItem: WishListItem, onProductSelected: (Product) -> Unit){

    val product = wishListItem.product

    Box(modifier = Modifier.fillMaxWidth().height(250.dp).clickable{
        onProductSelected(product)
    }){

        Box(modifier = Modifier.fillMaxSize()
            .clip(shape = RoundedCornerShape(9.dp))
            .background(color = Color.White))

        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .padding(5.dp)
                .fillMaxWidth()
        ) {

            Column(modifier = Modifier.fillMaxSize()){

                AsyncImage(
                    model = product.image,
                    contentDescription = "AC197A2D-B9C1-40EE-9527-E57EAB9608A2",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth().requiredHeight(190.dp)
                        .clip(shape = RoundedCornerShape(5.dp)))

                Text(
                    text = product.title,
                    color = Color.Black,
                    lineHeight = 1.33.em,
                    style = TextStyle(
                        fontSize = 13.sp
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .requiredHeight(height = 20.dp))

                Text(
                    text = product.price.toString(),
                    color = Color.Black,
                    style = TextStyle(
                        fontSize = 17.sp,
                        fontFamily = ralewayBold),
                    modifier = Modifier
                        .fillMaxWidth())

            }


        }

    }

}


