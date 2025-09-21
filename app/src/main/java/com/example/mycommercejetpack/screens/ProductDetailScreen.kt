package com.example.mycommercejetpack.screens

import android.graphics.Color
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.mycommercejetpack.R
import com.example.mycommercejetpack.data.Product
import com.example.mycommercejetpack.ui.theme.BgGrey
import com.example.mycommercejetpack.ui.theme.BlueCustom
import com.example.mycommercejetpack.ui.theme.ColorBgColor
import com.example.mycommercejetpack.ui.theme.DiscountColor
import com.example.mycommercejetpack.ui.theme.PinkCustom
import com.example.mycommercejetpack.ui.theme.RedCustom
import com.example.mycommercejetpack.ui.theme.TextColor



@Composable
fun ProductDetailScreen(product: Product){

    val ralewayBold = FontFamily(
        Font(R.font.raleway_bold, FontWeight.Bold)
    )


    Box(modifier = Modifier.fillMaxSize().background(BgGrey)){
        Column(modifier = Modifier.fillMaxSize()){
            AsyncImage(model = product.image, contentDescription = "",
                modifier = Modifier.fillMaxWidth().fillMaxHeight(0.5f).background(BlueCustom), contentScale = ContentScale.Crop)

            Column(modifier = Modifier.fillMaxSize().padding(25.dp,10.dp)){

                Row(modifier = Modifier.fillMaxWidth().height(35.dp),verticalAlignment = Alignment.CenterVertically){

                    Text(product.price.toString(), modifier = Modifier.fillMaxWidth(), color = TextColor, fontSize = 20.sp, fontFamily = ralewayBold)

                    Spacer(modifier = Modifier.weight(1f))


                    Box(
                        modifier = Modifier
                            .size(24.dp) // Total size including background
                            .background(PinkCustom, CircleShape) // Circular background
                    ) {
                        Image(
                            painter = painterResource(R.drawable.ic_share_red),
                            contentDescription = "settings",
                            modifier = Modifier
                                .size(26.dp)       // Icon size
                                .align(Alignment.Center) // Center icon inside Box
                                .padding(5.dp)     // Padding inside background, only for icon
                                .clip(CircleShape)
                        )
                    }

                }

                Row(modifier = Modifier.fillMaxWidth().wrapContentHeight(),verticalAlignment = Alignment.CenterVertically){

                    Text(3000.toString()+"  ", color = DiscountColor)

                    Box(
                        modifier = Modifier
                            .background(
                                color = RedCustom,
                                shape = RoundedCornerShape(topStart = 5.dp, topEnd = 5.dp, bottomStart = 5.dp)
                            )
                            .padding(5.dp,2.dp).padding(5.dp,0.dp)
                    ) {
                        Text(
                            text = "-20%",
                            color = TextColor,
                            style = MaterialTheme.typography.labelMedium
                        )
                    }


                }

                Text(
                    text = product.description,
                    modifier = Modifier.padding(0.dp,10.dp),
                    color = TextColor,
                    style = MaterialTheme.typography.labelMedium,
                    maxLines = 3
                )


                Row(modifier = Modifier.fillMaxWidth().wrapContentHeight(),verticalAlignment = Alignment.CenterVertically){
                    Text(
                        text = "Variations",
                        color = TextColor,
                        lineHeight = 1.3.em,
                        style = TextStyle(
                            fontSize = 20.sp,
                            letterSpacing = (-0.2).sp
                        ))

                    Box(
                        modifier = Modifier
                            .requiredWidth(width = 54.dp)
                            .requiredHeight(height = 25.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .clip(shape = MaterialTheme.shapes.small)
                                .background(ColorBgColor))
                        Text(
                            text = "Pink",
                            color = TextColor,
                            textAlign = TextAlign.Center,
                            lineHeight = 1.29.em,
                            style = TextStyle(
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Medium,
                                letterSpacing = (-0.14).sp),
                            modifier = Modifier
                                .align(alignment = Alignment.CenterStart)
                                .offset(x = 0.dp,
                                    y = 1.5.dp)
                                .fillMaxWidth())
                    }

                    Box(
                        modifier = Modifier
                            .requiredWidth(width = 54.dp)
                            .requiredHeight(height = 25.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .clip(shape = MaterialTheme.shapes.small)
                                .background(ColorBgColor))
                        Text(
                            text = "M",
                            color = TextColor,
                            textAlign = TextAlign.Center,
                            lineHeight = 1.29.em,
                            style = TextStyle(
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Medium,
                                letterSpacing = (-0.14).sp),
                            modifier = Modifier
                                .align(alignment = Alignment.CenterStart)
                                .offset(x = 0.dp,
                                    y = 1.5.dp)
                                .fillMaxWidth())
                    }
                }

                val items = listOf("Item 1", "Item 2", "Item 3")

                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(1.dp) // Space between items
                ) {
                    items(items.size) { item ->
                        Box(
                            modifier = Modifier
                                .padding(3.dp,5.dp)
                        ) {
                            Image(painterResource(R.drawable.ic_sample_onboard), contentDescription = "", modifier = Modifier.size(100.dp).clip(RoundedCornerShape(8.dp)), contentScale = ContentScale.Crop)
                        }
                    }
                }


                Row(modifier = Modifier.fillMaxWidth().height(75.dp).padding(0.dp,5.dp),
                    verticalAlignment = Alignment.CenterVertically ){

                    Box(modifier = Modifier.fillMaxWidth(0.24f).fillMaxHeight(0.65f).padding(8.dp,1.dp).background(PinkCustom,RoundedCornerShape(10.dp)
                       ).align(Alignment.CenterVertically), contentAlignment = Alignment.Center){

                        Image(painterResource(R.drawable.ic_like_vector), contentDescription = "uvhdkhg", modifier = Modifier.size(25.dp))

                    }

                    TextButton(
                        onClick = { },
                        colors = ButtonDefaults.buttonColors(containerColor = androidx.compose.ui.graphics.Color.Transparent),
                        modifier = Modifier
                            .fillMaxWidth(0.5f)
                            .requiredHeight(height = 40.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .requiredHeight(height = 40.dp).clip(RoundedCornerShape(10.dp))
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(color = TextColor))
                            Text(
                                text = "Add to cart",
                                color = androidx.compose.ui.graphics.Color.White,
                                textAlign = TextAlign.Center,
                                style = TextStyle(
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Light),
                                modifier = Modifier
                                    .align(alignment = Alignment.CenterStart)
                                    .fillMaxWidth())
                        }
                    }

                    TextButton(
                        onClick = { },
                        colors = ButtonDefaults.buttonColors(containerColor = androidx.compose.ui.graphics.Color.Transparent),
                        modifier = Modifier
                            .fillMaxWidth()
                            .requiredHeight(height = 40.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .requiredHeight(height = 40.dp).clip(RoundedCornerShape(10.dp))
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(color = BlueCustom))
                            Text(
                                text = "Add to cart",
                                color = androidx.compose.ui.graphics.Color.White,
                                textAlign = TextAlign.Center,
                                style = TextStyle(
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Light),
                                modifier = Modifier
                                    .align(alignment = Alignment.CenterStart)
                                    .fillMaxWidth())
                        }
                    }



                }






            }
        }



    }
}




