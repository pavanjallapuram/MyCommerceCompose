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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mycommercejetpack.R
import com.example.mycommercejetpack.ui.theme.BgGrey
import com.example.mycommercejetpack.ui.theme.BlueCustom
import com.example.mycommercejetpack.viewmodels.AuthViewModel

@Preview
@Composable
fun CreateAccountScreen(onResgisterSuccess: () -> Unit = {},onNavigateLogInScreen: () -> Unit = {},viewModel: AuthViewModel = viewModel()){

    val state by viewModel.authState.collectAsState()



    val ralewayBold = FontFamily(Font(R.font.raleway_bold, FontWeight.Bold))
    val nunitosans = FontFamily(Font(R.font.nunitosanslight, FontWeight.Light))

    var imageUri by remember { mutableStateOf<Uri?>(null) }

    // Launcher to pick image from system gallery
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        imageUri = uri // Store the selected image URI
    }

    var password by remember { mutableStateOf("") }

    var username by remember { mutableStateOf("") }
    var number by remember { mutableStateOf("") }

    Box(modifier = Modifier.fillMaxSize().background(Color.White)) {

        Image(
            painter = painterResource(R.drawable.ic_create_bg),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop // or FillBounds, depending on your need
        )


        Column(modifier = Modifier.fillMaxWidth(0.7f).wrapContentHeight().align(Alignment.Center)){
            Text(
                text = "Create \nAccount",
                color = Color(0xff202020),
                lineHeight = 1.08.em,
                style = TextStyle(
                    fontSize = 45.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = ralewayBold,
                    letterSpacing = (-0.5).sp
                )
            )







            TextField(
                value = username,
                onValueChange = { username = it },
                placeholder = { Text("Enter text") },
                modifier = Modifier
                    .fillMaxWidth().padding(0.dp,10.dp),
                shape = RoundedCornerShape(20.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = BgGrey,
                    unfocusedContainerColor = BgGrey,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    cursorColor = BlueCustom
                ),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                )
            )


            var isPasswordVisible by remember { mutableStateOf(false) }

            TextField(
                value = password,
                onValueChange = { password = it },
                placeholder = { Text("Enter password") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 10.dp),
                shape = RoundedCornerShape(20.dp),
                visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                trailingIcon = {
                    val icon = if (isPasswordVisible) Icons.Default.VisibilityOff else Icons.Default.VisibilityOff
                    IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                        Icon(imageVector = icon, contentDescription = null)
                    }
                },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = BgGrey,
                    unfocusedContainerColor = BgGrey, // Use your defined color
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    cursorColor = BlueCustom
                )
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(15.81.dp, Alignment.Start),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .requiredHeight(height = 60.dp)
                    .clip(shape = RoundedCornerShape(20.dp))
                    .background(color = BgGrey)
                    .padding(horizontal = 10.dp,
                        vertical = 5.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(15.81.dp, Alignment.Start),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(7.91.dp, Alignment.Start),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .requiredWidth(width = 24.dp)
                                .requiredHeight(height = 18.dp)
                                .clip(shape = RoundedCornerShape(1.9764012098312378.dp))
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_england),
                                contentDescription = "England",
                                modifier = Modifier
                                    .fillMaxSize()
                                    .clip(shape = RoundedCornerShape(3.9528024196624756.dp)))
                            Box(
                                modifier = Modifier
                                    .requiredWidth(width = 32.dp)
                                    .requiredHeight(height = 24.dp))
                        }
                        Image(
                            painter = painterResource(id = R.drawable.arrowdown),
                            contentDescription = "arrow-down",
                            modifier = Modifier
                                .requiredSize(size = 16.dp))

                        Divider(
                            modifier = Modifier
                                .requiredWidth(width = 15.dp)
                                .rotate(degrees = -90f))
                    }
                }
                TextField(
                    value = username,
                    onValueChange = { username = it },
                    placeholder = { Text("Enter text") },
                    modifier = Modifier
                        .fillMaxWidth().fillMaxWidth(),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = BgGrey,
                        unfocusedContainerColor = BgGrey,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        cursorColor = BlueCustom
                    )
                )
            }

            Button(onClick = {viewModel.register(username,password)},
                modifier = Modifier.fillMaxWidth(1.0f).height(100.dp).align(Alignment.CenterHorizontally).padding(0.dp,10.dp).padding(0.dp,10.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = BlueCustom, // Background color
                    contentColor = Color.White   // Text/Icon color
                ) ) {
                Text("Let's get started", fontSize = 20.sp,
                    fontFamily = nunitosans,
                    color = Color.White, textAlign = TextAlign.Center)

            }

            Box(modifier = Modifier.fillMaxWidth().clickable{
                onNavigateLogInScreen()
            }, contentAlignment = Alignment.Center) {
                Text("Already Existing User Please Login", modifier = Modifier.wrapContentSize())
            }


        }

        when {
            state.isLoading -> Log.e("password","hello")
            state.message != null -> Log.e("password",state.message!!)
        }

        // Other composables go here, layered on top of the background image
    }

}