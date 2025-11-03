package com.example.mycommercejetpack.screens

import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.example.mycommercejetpack.R
import com.example.mycommercejetpack.ui.theme.BlueCustom
import org.w3c.dom.Text
import java.nio.file.WatchEvent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.navigation.NavController

import com.example.mycommercejetpack.ui.theme.BgGrey
import com.example.mycommercejetpack.ui.theme.TextColor


@Composable
fun SplashSCreen(){
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Column {

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.size(100.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_circle_bg),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize()
                )
                Image(
                    painter = painterResource(R.drawable.ic_app_icon),
                    contentDescription = "App Icon",
                    modifier = Modifier.size(60.dp)
                )
            }

            val ralewayBold = FontFamily(Font(R.font.raleway_bold, FontWeight.Bold))

            Text(
                text = "Shoppe",
                fontSize = 32.sp,
                fontFamily = ralewayBold,
                fontWeight = FontWeight.Bold,
                color = Color.Blue
            )


        }

        // You can add Image or Lottie animation here
    }
}


@Composable
fun GetStartedScreen(onViewCreateClick: () -> Unit = {},onViewLoginScreen: () -> Unit){



    Box(modifier = Modifier.fillMaxSize().background(Color.White)){

        val ralewayBold = FontFamily(Font(R.font.raleway_bold, FontWeight.Bold))
        val nunitosans = FontFamily(Font(R.font.nunitosanslight, FontWeight.Light))

        Column(modifier = Modifier.wrapContentSize().align(Alignment.Center)) {

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.wrapContentSize().align(Alignment.CenterHorizontally)
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_circle_bg),
                    contentDescription = null,
                    modifier = Modifier.requiredWidth(150.dp)
                )
                Image(
                    painter = painterResource(R.drawable.ic_app_icon),
                    contentDescription = "App Icon",
                    modifier = Modifier.size(75.dp)
                )
            }



            Text(
                text = "Shoppe",
                modifier = Modifier.wrapContentSize().align(Alignment.CenterHorizontally),
                fontSize = 45.sp,
                fontFamily = ralewayBold,
                fontWeight = FontWeight.Bold,
                color = TextColor, textAlign = TextAlign.Center
            )

            Text("Beautiful eCommerce UI Kit for your online store",
                modifier = Modifier.wrapContentSize().padding(45.dp,10.dp),
                fontSize = 20.sp, fontFamily = nunitosans,
                color = TextColor, textAlign = TextAlign.Center
                )

        }

        Column(modifier = Modifier.wrapContentSize().align(Alignment.BottomCenter).padding(0.dp,20.dp)) {

            Button(onClick = {
                onViewCreateClick()
                             }, modifier = Modifier.fillMaxWidth(0.8f).height(60.dp).align(Alignment.CenterHorizontally),shape = RoundedCornerShape(16.dp), colors = ButtonDefaults.buttonColors(
                containerColor = BlueCustom, // Background color
                contentColor = Color.White   // Text/Icon color
            ) ) {
                Text("Let's get started", fontSize = 20.sp,
                    fontFamily = nunitosans,
                    color = Color.White, textAlign = TextAlign.Center)

            }

            Row(modifier = Modifier.wrapContentSize().height(60.dp).align(Alignment.CenterHorizontally).clickable{
                onViewLoginScreen()
            }){

                Text("I already have an account",
                    modifier = Modifier.wrapContentSize().padding(10.dp).align(Alignment.CenterVertically),
                    fontSize = 20.sp, fontFamily = nunitosans,
                    color = TextColor, textAlign = TextAlign.Center
                )

                Image(painterResource(R.drawable.ic_bg_right), contentDescription = "", modifier = Modifier.size(35.dp).align(Alignment.CenterVertically))

            }

        }


    }



}











