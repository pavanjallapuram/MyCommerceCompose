package com.example.mycommercejetpack

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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
import com.example.mycommercejetpack.screens.CartScreen
import com.example.mycommercejetpack.screens.ProductDetailScreen
import com.example.mycommercejetpack.screens.ProductsHomeScreen
import com.example.mycommercejetpack.screens.SettingsScreen
import com.example.mycommercejetpack.screens.WishListScreen
import com.google.firebase.auth.FirebaseAuth
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
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



    val firebaseUser = FirebaseAuth.getInstance().currentUser

    if (firebaseUser != null) {

         startDestination = "home"



    } else {
        if (!isLoggedIn){
            startDestination = "onboarding"
        }

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
            }, onSettingsSelected = {
                navController.navigate("settings")
            }, onWishListSelected = {
                navController.navigate("wishlist")
            }, onCartListSelected = {
                navController.navigate("carlist")
            })
        }
        composable("carlist"){
            CartScreen(onItemSelectedShow = {
                product -> {
                    val productJson = Uri.encode(Gson().toJson(product))
                    navController.navigate("productDetailScreen/$productJson")
            }
            })
        }
        composable("wishlist"){
            WishListScreen(onProductSelected = {
                    product -> {
                val productJson = Uri.encode(Gson().toJson(product))
                navController.navigate("productDetailScreen/$productJson")
            }

            })
        }
        composable("settings"){
            SettingsScreen()
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
            CreateAccountScreen(onResgisterSuccess = {
                navController.navigate("home")
            }, onNavigateLogInScreen = {
                navController.navigate("home")
            })
        }

        composable("exsistingUser"){
            LoginScreen(onLoginRedirect = {
                navController.navigate("createAccount")
            }, onLogInSuccess = {
                navController.navigate("home")
            })
        }


    }
}

