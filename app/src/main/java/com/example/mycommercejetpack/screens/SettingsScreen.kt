package com.example.mycommercejetpack.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mycommercejetpack.R
import com.example.mycommercejetpack.ui.theme.nunitoSansSemiBold
import com.example.mycommercejetpack.ui.theme.ralewayBold
import com.example.mycommercejetpack.ui.theme.ralewayExtraBold


@Preview
@Composable
fun SettingsScreen(){


    Box(modifier = Modifier
        .fillMaxSize()
        .statusBarsPadding()){


        Column(){

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(65.dp)
                    .padding(5.dp, 2.dp),
                verticalAlignment = Alignment.CenterVertically // Centers all items vertically
            ) {


                Box(
                    modifier = Modifier
                        .padding(start = 8.dp) // optional spacing
                        .padding(horizontal = 10.dp, vertical = 8.dp) // inner padding for text
                ) {
                    Text(
                        text = stringResource(R.string.settings),
                        color = Color.Black,
                        fontSize = 25.sp, fontFamily = ralewayBold
                    )
                }


                Spacer(modifier = Modifier.weight(1f)) // Pushes settings icon to end




            }


            Column(modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()){

                Box(
                    modifier = Modifier
                        .padding(start = 8.dp) // optional spacing
                        .padding(horizontal = 16.dp, vertical = 8.dp) // inner padding for text
                ) {
                    Text(
                        text = stringResource(R.string.personal),
                        color = Color.Black,
                        fontSize = 20.sp, fontFamily = ralewayExtraBold
                    )
                }


                Row(modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp), verticalAlignment = Alignment.CenterVertically){

                    Box(
                        modifier = Modifier
                            .padding(start = 8.dp) // optional spacing
                            .padding(horizontal = 16.dp, vertical = 8.dp) // inner padding for text
                    ) {
                        Text(
                            text = stringResource(R.string.profile),
                            color = Color.Black,
                            fontSize = 20.sp, fontFamily = nunitoSansSemiBold
                        )
                    }

                    Spacer(modifier = Modifier.weight(1f))

                    Image(
                        painter = painterResource(R.drawable.arrowdown),
                        contentDescription = "settings",
                        modifier = Modifier
                            .size(45.dp)       // Icon size // Center icon inside Box
                            .padding(10.dp, 0.dp)     // Padding inside background, only for icon
                            .clip(CircleShape)
                            .rotate(-90f)
                    )


                }

                Row(modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp), verticalAlignment = Alignment.CenterVertically){

                    Box(
                        modifier = Modifier
                            .padding(start = 8.dp) // optional spacing
                            .padding(horizontal = 16.dp, vertical = 8.dp) // inner padding for text
                    ) {
                        Text(
                            text = stringResource(R.string.shipping_addresses),
                            color = Color.Black,
                            fontSize = 20.sp, fontFamily = nunitoSansSemiBold
                        )
                    }

                    Spacer(modifier = Modifier.weight(1f))

                    Image(
                        painter = painterResource(R.drawable.arrowdown),
                        contentDescription = "settings",
                        modifier = Modifier
                            .size(45.dp)       // Icon size // Center icon inside Box
                            .padding(10.dp, 0.dp)     // Padding inside background, only for icon
                            .clip(CircleShape)
                            .rotate(-90f)
                    )


                }

                Row(modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp), verticalAlignment = Alignment.CenterVertically){

                    Box(
                        modifier = Modifier
                            .padding(start = 8.dp) // optional spacing
                            .padding(horizontal = 16.dp, vertical = 8.dp) // inner padding for text
                    ) {
                        Text(
                            text = stringResource(R.string.payment_methods),
                            color = Color.Black,
                            fontSize = 20.sp, fontFamily = nunitoSansSemiBold
                        )
                    }

                    Spacer(modifier = Modifier.weight(1f))

                    Image(
                        painter = painterResource(R.drawable.arrowdown),
                        contentDescription = "settings",
                        modifier = Modifier
                            .size(45.dp)       // Icon size // Center icon inside Box
                            .padding(10.dp, 0.dp)     // Padding inside background, only for icon
                            .clip(CircleShape)
                            .rotate(-90f)
                    )


                }

            }


            Column(modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()){

                Box(
                    modifier = Modifier
                        .padding(start = 8.dp) // optional spacing
                        .padding(horizontal = 16.dp, vertical = 8.dp) // inner padding for text
                ) {
                    Text(
                        text = stringResource(R.string.shop),
                        color = Color.Black,
                        fontSize = 20.sp, fontFamily = ralewayExtraBold
                    )
                }


                Row(modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp), verticalAlignment = Alignment.CenterVertically){

                    Box(
                        modifier = Modifier
                            .padding(start = 8.dp) // optional spacing
                            .padding(horizontal = 16.dp, vertical = 8.dp) // inner padding for text
                    ) {
                        Text(
                            text = stringResource(R.string.country),
                            color = Color.Black,
                            fontSize = 20.sp, fontFamily = nunitoSansSemiBold
                        )
                    }

                    Spacer(modifier = Modifier.weight(1f))

                    Image(
                        painter = painterResource(R.drawable.arrowdown),
                        contentDescription = "settings",
                        modifier = Modifier
                            .size(45.dp)       // Icon size // Center icon inside Box
                            .padding(10.dp, 0.dp)     // Padding inside background, only for icon
                            .clip(CircleShape)
                            .rotate(-90f)
                    )


                }

                Row(modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp), verticalAlignment = Alignment.CenterVertically){

                    Box(
                        modifier = Modifier
                            .padding(start = 8.dp) // optional spacing
                            .padding(horizontal = 16.dp, vertical = 8.dp) // inner padding for text
                    ) {
                        Text(
                            text = stringResource(R.string.currency),
                            color = Color.Black,
                            fontSize = 20.sp, fontFamily = nunitoSansSemiBold
                        )
                    }

                    Spacer(modifier = Modifier.weight(1f))

                    Image(
                        painter = painterResource(R.drawable.arrowdown),
                        contentDescription = "settings",
                        modifier = Modifier
                            .size(45.dp)       // Icon size // Center icon inside Box
                            .padding(10.dp, 0.dp)     // Padding inside background, only for icon
                            .clip(CircleShape)
                            .rotate(-90f)
                    )


                }

                Row(modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp), verticalAlignment = Alignment.CenterVertically){

                    Box(
                        modifier = Modifier
                            .padding(start = 8.dp) // optional spacing
                            .padding(horizontal = 16.dp, vertical = 8.dp) // inner padding for text
                    ) {
                        Text(
                            text = stringResource(R.string.sizes),
                            color = Color.Black,
                            fontSize = 20.sp, fontFamily = nunitoSansSemiBold
                        )
                    }

                    Spacer(modifier = Modifier.weight(1f))

                    Image(
                        painter = painterResource(R.drawable.arrowdown),
                        contentDescription = "settings",
                        modifier = Modifier
                            .size(45.dp)       // Icon size // Center icon inside Box
                            .padding(10.dp, 0.dp)     // Padding inside background, only for icon
                            .clip(CircleShape)
                            .rotate(-90f)
                    )


                }


                Row(modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp), verticalAlignment = Alignment.CenterVertically){

                    Box(
                        modifier = Modifier
                            .padding(start = 8.dp) // optional spacing
                            .padding(horizontal = 16.dp, vertical = 8.dp) // inner padding for text
                    ) {
                        Text(
                            text = stringResource(R.string.terms_and_conditions),
                            color = Color.Black,
                            fontSize = 20.sp, fontFamily = nunitoSansSemiBold
                        )
                    }

                    Spacer(modifier = Modifier.weight(1f))

                    Image(
                        painter = painterResource(R.drawable.arrowdown),
                        contentDescription = "settings",
                        modifier = Modifier
                            .size(45.dp)       // Icon size // Center icon inside Box
                            .padding(10.dp, 0.dp)     // Padding inside background, only for icon
                            .clip(CircleShape)
                            .rotate(-90f)
                    )


                }

            }

        }



    }

}