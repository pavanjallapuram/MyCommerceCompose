package com.example.mycommercejetpack.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.mycommercejetpack.R
import com.example.mycommercejetpack.data.CartItem
import com.example.mycommercejetpack.data.Product
import com.example.mycommercejetpack.ui.theme.BgGrey
import com.example.mycommercejetpack.ui.theme.BlueCustom
import com.example.mycommercejetpack.ui.theme.SecondaryBackgroundColor
import com.example.mycommercejetpack.ui.theme.black
import com.example.mycommercejetpack.ui.theme.nunitosansregular
import com.example.mycommercejetpack.ui.theme.ralewayBold
import com.example.mycommercejetpack.ui.theme.ralewayMedium
import com.example.mycommercejetpack.viewmodels.CartViewModel
import com.example.mycommercejetpack.viewmodels.ProfileViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

@Composable
fun CartScreen(profileViewModel: ProfileViewModel = hiltViewModel(),cartViewModel: CartViewModel = hiltViewModel(),onItemSelectedShow:(Product) -> Unit = {}){

    val totalPrice = cartViewModel.totalCartPrice.collectAsState()

    val cartListItems = profileViewModel.getUserCartListItems().collectAsState()


    Box(modifier = Modifier.fillMaxSize().statusBarsPadding()){

        Column(modifier = Modifier.fillMaxWidth().wrapContentHeight()){
            Text(text = "Cart",
                modifier = Modifier.wrapContentSize().padding(10.dp,10.dp),
                fontFamily = ralewayBold, color = Color.Black, fontSize = 28.sp
            )

            ShippingAddress(modifier = Modifier.padding(10.dp,5.dp))

            LazyColumn(modifier = Modifier.fillMaxWidth().wrapContentHeight()){

                items(cartListItems.value.size){
                    index ->
                    CartItem(cartItem = cartListItems.value[index],cartViewModel,onItemSelectedShow)
                }

            }
        }

        Box(modifier = Modifier.fillMaxWidth().requiredHeight(70.dp).align(Alignment.BottomCenter).background(
            SecondaryBackgroundColor
        )){



            Text(text = "Total  $"+ totalPrice.value ,
                color = Color.Black,
                lineHeight = 1.3.em,
                style = TextStyle(
                    fontSize = 20.sp,
                    fontFamily = ralewayBold,
                    letterSpacing = (-0.2).sp),modifier = Modifier.align(Alignment.CenterStart).padding(10.dp,0.dp))


            Button(
                onClick = {  },
                modifier = Modifier.wrapContentWidth().height(55.dp).padding(10.dp,0.dp).padding(0.dp, 5.dp).align(
                    Alignment.CenterEnd),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = BlueCustom, // Background color
                    contentColor = Color.White   // Text/Icon color
                )
            ) {
                Text(
                    "CheckOut", fontSize = 20.sp,
                    fontFamily = nunitosansregular,
                    color = Color.White, textAlign = TextAlign.Center
                )
            }



        }

    }

}


@Composable
fun ShippingAddress(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .requiredHeight(height = 80.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(shape = RoundedCornerShape(15.dp))
                .background(BgGrey))
        Text(
            text = "Shipping Address",
            color = black,

            style = TextStyle(
                fontSize = 14.sp,
                fontFamily = ralewayBold,
                letterSpacing = (-0.14).sp
            ),
            modifier = Modifier
                .align(alignment = Alignment.CenterStart)
                .offset(x = 10.dp,
                    y = (-17).dp)
                .fillMaxWidth())
        Button(modifier = Modifier.align(alignment = Alignment.BottomEnd).padding(5.dp,5.dp))
        Text(
            text = "26, Duong So 2, Thao Dien Ward, An Phu, District 2, Ho Chi Minh ",
            color = black,
            style = TextStyle(
                fontSize = 10.sp, fontFamily = nunitosansregular
            ),
            modifier = Modifier
                .align(alignment = Alignment.CenterStart)
                .offset(x = 10.dp,
                    y = 11.5.dp)
                .fillMaxWidth()
                .requiredHeight(height = 29.dp))
    }
}

@Composable
fun Button(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .size(35.dp)

    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_cart_edit),
            contentDescription = "Ellipse 149",
            modifier = Modifier
                .fillMaxSize())

    }
}



@Composable
fun CartItem(cartItem: CartItem, cartViewModel: CartViewModel,onItemSelectedShow:(Product) -> Unit = {}){


    var numberOfItems = rememberSaveable { mutableStateOf(cartItem.numOfItems) }

    Box(modifier = Modifier.fillMaxWidth().height(125.dp)){

        Box(modifier = Modifier.fillMaxWidth().height(125.dp).clickable{
            onItemSelectedShow(cartItem.product)
        }){

            Row(modifier = Modifier.fillMaxSize().padding(5.dp)){

                Box(modifier = Modifier.fillMaxWidth(0.3f).fillMaxHeight()
                    .clip(shape = RoundedCornerShape(9.dp))
                    .background(color = Color.White), contentAlignment = Alignment.Center){

                    AsyncImage(
                        model = cartItem.product.image,
                        contentDescription = "AC197A2D-B9C1-40EE-9527-E57EAB9608A2",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth().fillMaxHeight().padding(5.dp)
                            .clip(shape = RoundedCornerShape(9.dp)))


                }

                Column(modifier = Modifier.fillMaxSize().padding(5.dp)){

                    Text(
                        text = "Lorem ipsum dolor sit amet consectetur.",
                        color = black,
                        lineHeight = 1.33.em,
                        fontFamily = nunitosansregular,
                        style = TextStyle(
                            fontSize = 12.sp))

                    Row(){
                        Text(
                            text = "Pink, Size M",
                            color = Color.Black,
                            lineHeight = 1.29.em,
                            style = TextStyle(
                                fontSize = 14.sp,
                                fontFamily = ralewayMedium,
                                letterSpacing = (-0.14).sp))
                    }

                    Spacer(modifier = Modifier.weight(1f))


                    Row(verticalAlignment = Alignment.CenterVertically){

                        Text(
                            text = (cartItem.product.price * numberOfItems.value).toString(),
                            color = black,
                            lineHeight = 1.22.em,
                            style = TextStyle(
                                fontSize = 22.sp,
                                fontFamily = ralewayBold,
                                letterSpacing = (-0.18).sp))

                        Spacer(modifier = Modifier.weight(1f))

                        Row(verticalAlignment = Alignment.CenterVertically){

                            Image(
                                painter = painterResource(R.drawable.ic_minus_cart_item),
                                contentDescription = "settings",
                                modifier = Modifier
                                    .size(35.dp)       // Icon size // Center icon inside Box
                                    .padding(7.dp,2.dp).clickable{
                                        cartViewModel.removeCartItem(cartItem)
                                        numberOfItems.value = cartViewModel.numberOfItems(cartItem)!!
                                    }     // Padding inside background, only for icon
                            )

                            Box(
                                modifier = Modifier
                                    .size(40.dp)       // Icon size // Center icon inside Box
                                    .padding(7.dp,2.dp) ,
                                contentAlignment = Alignment.Center// Padding inside background, only for icon
                            ){
                                Box(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .clip(shape = RoundedCornerShape(7.dp))
                                        .background(color = Color(0xffe5ebfc)))
                                Text(
                                    text = numberOfItems.value.toString(),
                                    color = Color.Black,
                                    style = TextStyle(
                                        fontSize = 20.sp,
                                        fontFamily = ralewayMedium),
                                    modifier = Modifier.wrapContentSize(),
                                    textAlign = TextAlign.Center)
                            }

                            Image(
                                painter = painterResource(R.drawable.ic_add_more_item),
                                contentDescription = "settings",
                                modifier = Modifier
                                    .size(35.dp)
                                    .padding(7.dp,2.dp).clickable{

                                        cartViewModel.addCartItem(cartItem)
                                        numberOfItems.value = cartViewModel.numberOfItems(cartItem)!!

                                    }
                            )

                        }

                    }


                }



            }

        }

    }



}