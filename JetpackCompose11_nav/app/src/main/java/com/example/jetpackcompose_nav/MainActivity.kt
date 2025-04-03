package com.example.jetpackcompose_nav

import android.app.FragmentManager.BackStackEntry
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.jetpackcompose_nav.ui.theme.JetpackCompose_navTheme
import com.example.jetpackcompose_nav.ui.theme.catalog.category.CategoryScreen
import com.example.jetpackcompose_nav.ui.theme.catalog.product.ProductDetailScreen
import com.example.jetpackcompose_nav.ui.theme.home.HomeScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {
            MainApp()
        }
    }
}


@Composable
fun MainApp() {
    val navController = rememberNavController()
    JetpackCompose_navTheme {
        NavHost(navController = navController, startDestination = "category"){
            composable("home"){
                HomeScreen(openCategoryAction = {
                    navController.navigate("category")
                },
                    openMyAccountScreen = {},
                    editCustomerInfo = {}
                    )
                }

            // route category
            composable("category"){
                CategoryScreen(openProductDetail =
                {
                    productId ->
                    navController.navigate("productDetail/$productId")
                })
            }
            // route product detail
            composable("productDetail/{productId}", arguments = listOf(
                navArgument("productId"){
                    type = NavType.StringType
                }
            )){
                backStackEntry -> val productId = backStackEntry.arguments?.getString("productId")
                requireNotNull(productId)
                ProductDetailScreen(productId = productId, checkout = {
                    cartId, customerId ->
                })
            }
        }
    }
}
