package com.example.mycommercejetpack.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mycommercejetpack.R
import com.example.mycommercejetpack.ui.theme.BgGrey
import com.example.mycommercejetpack.ui.theme.BlueCustom
import com.example.mycommercejetpack.ui.theme.TextColor
import com.example.mycommercejetpack.viewmodels.AuthViewModel


@Composable
    fun LoginScreen(onLoginRedirect: () -> Unit = {},onLogInSuccess: () -> Unit = {},viewModel: AuthViewModel = hiltViewModel()){

    val state by viewModel.authState.collectAsState()

    Box(modifier = Modifier.fillMaxSize().background(Color.White)){

        val ralewayBold = FontFamily(Font(R.font.raleway_bold, FontWeight.Bold))
        val nunitosans = FontFamily(Font(R.font.nunitosanslight, FontWeight.Light))

        var username by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        Image(
            painter = painterResource(R.drawable.ic_create_bg),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop // or FillBounds, depending on your need
        )

        Column(modifier = Modifier.fillMaxWidth(0.75f).padding(0.dp,15.dp).align(Alignment.BottomCenter)) {

            Text(
                text = "Login",
                color = Color(0xff202020),
                lineHeight = 1.08.em,
                style = TextStyle(
                    fontSize = 55.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = ralewayBold,
                    letterSpacing = (-0.5).sp
                )
            )

            Text("Good to see you back!",
                modifier = Modifier.wrapContentSize().padding(5.dp,10.dp),
                fontSize = 20.sp, fontFamily = nunitosans,
                color = TextColor, textAlign = TextAlign.Start
            )

            TextField(
                value = username,
                onValueChange = { username = it },
                placeholder = { Text("Email") },
                modifier = Modifier
                    .fillMaxWidth().padding(0.dp,10.dp),
                shape = RoundedCornerShape(20.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = BgGrey,
                    unfocusedContainerColor = BgGrey,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    cursorColor = BlueCustom
                )
            )



            TextField(
                value = password,
                onValueChange = { password = it },
                placeholder = { Text("Password") },
                modifier = Modifier
                    .fillMaxWidth().padding(0.dp,10.dp),
                shape = RoundedCornerShape(20.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = BgGrey,
                    unfocusedContainerColor = BgGrey,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    cursorColor = BlueCustom
                )
            )


            Button(onClick = { viewModel.login(username, password) }, modifier = Modifier.fillMaxWidth().height(75.dp).padding(0.dp).padding(0.dp,10.dp).align(Alignment.CenterHorizontally),shape = RoundedCornerShape(16.dp), colors = ButtonDefaults.buttonColors(
                containerColor = BlueCustom, // Background color
                contentColor = Color.White   // Text/Icon color
            ) ) {
                Text("Next", fontSize = 20.sp,
                    fontFamily = nunitosans,
                    color = Color.White, textAlign = TextAlign.Center)
            }

            Text("New User Please Sign In",
                modifier = Modifier.wrapContentSize().padding(8.dp).align(Alignment.CenterHorizontally).clickable{
                    onLoginRedirect()
                },
                fontSize = 15.sp, fontFamily = nunitosans,
                color = TextColor, textAlign = TextAlign.Center
            )

            when {
                state.isLoading -> Log.e("status","hello")
                state.isSuccess == true -> {
                    onLogInSuccess()
                }
            }

        }
    }

}