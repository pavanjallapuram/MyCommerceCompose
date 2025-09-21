package com.example.mycommercejetpack

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mycommercejetpack.data.User
import com.example.mycommercejetpack.screens.CreateAccountScreen
import com.example.mycommercejetpack.screens.GetStartedScreen
import com.example.mycommercejetpack.screens.LoginScreen
import com.example.mycommercejetpack.screens.OnBoardingPage
import com.example.mycommercejetpack.ui.theme.MycommerceJetpackTheme
import com.example.mycommercejetpack.utils.Util
import androidx.compose.runtime.getValue
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.mycommercejetpack.data.Product
import com.example.mycommercejetpack.screens.ProductDetailScreen
import com.example.mycommercejetpack.screens.ProductItemScreen
import com.example.mycommercejetpack.screens.ProductsHomeScreen
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        setContent {
            MycommerceJetpackTheme {
                enableEdgeToEdge()
                AppNavigation()
            }
        }
    }

}

@Composable
fun AppNavigation() {









    val navController = rememberNavController()

    val onBoardingPage = false

    var startDestination = "getStarted"


    val context = LocalContext.current

    val isLoggedIn by Util.getLoginStatus(context)
        .collectAsState(initial = false)


    if (!isLoggedIn){
        startDestination = "onboarding"
    }




    NavHost(navController = navController, startDestination = startDestination) {

        composable(route = "productDetailScreen/{productJson}",
            arguments = listOf(navArgument("productJson") { type = NavType.StringType })){
                backStackEntry ->
            val productJson = backStackEntry.arguments?.getString("productJson")
            val product = Gson().fromJson(productJson, Product::class.java)
            product?.let { ProductDetailScreen(it) }
        }
        composable("home"){
            ProductsHomeScreen(onProductSelected = {
                    product ->
                val productJson = Uri.encode(Gson().toJson(product))
                navController.navigate("productDetailScreen/$productJson")
            })
        }
        composable("onboarding"){
            OnBoardingPage(onBoardingEndded = {
                val  scope = CoroutineScope(Dispatchers.IO)

                scope.launch {
                    Util.saveLoginStatus(context,true)
                }


                navController.navigate("exsistingUser")
            })
        }
        composable("getStarted") {
            GetStartedScreen(onViewCreateClick = {
                navController.navigate("createAccount")
            }, onViewLoginScreen = {
                navController.navigate("exsistingUser")
            } )
        }
        composable("createAccount") {
            CreateAccountScreen()
        }

        composable("exsistingUser"){
            LoginScreen(onLoginRedirect = {
                navController.navigate("createAccount")
            })
        }


    }
}

