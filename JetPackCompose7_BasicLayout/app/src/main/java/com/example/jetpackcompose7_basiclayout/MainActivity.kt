package com.example.jetpackcompose7_basiclayout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.jetpackcompose7_basiclayout.ui.theme.JetPackCompose7_BasicLayoutTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetPackCompose7_BasicLayoutTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    HomeScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}
@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
//  Box(modifier = modifier.fillMaxSize()){
//      BoxItem( modifier = Modifier.align(Alignment.TopStart),color = Color.Blue, size = 200.dp)
//      BoxItem(modifier = Modifier.align(Alignment.TopEnd),color = Color.Red, size = 150.dp)
//      BoxItem(color = Color.Green,modifier = Modifier.align(Alignment.BottomStart))
//      BoxItem(color = Color.Yellow,modifier = Modifier.align(Alignment.BottomEnd))
//      BoxItem(color = Color.Magenta,modifier = Modifier.align(Alignment.Center))
//  }
    Column {
        Box(){
            // tương tụ với column
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.size(400.dp,300.dp).background(color = Color.Black),
                horizontalArrangement = Arrangement.SpaceEvenly
            ){
                BoxItem(color = Color.Red)
                BoxItem(color = Color.Blue)
                BoxItem(color = Color.Green)
            }
        }
        Space()
        Row(
            verticalAlignment = Alignment.CenterVertically
        ){
            OutlinedTextField(
                value = "",
                onValueChange = {},
                modifier = Modifier.weight(1f)
            )
            Icon(
                Icons.Default.Settings,
                contentDescription = "setting",
                Modifier.size(36.dp)
            )
        }
    }
}
@Composable
fun ForgotPassword(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        // Nội dung chính ở giữa
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth().align(Alignment.Center)
        ) {
            Text(text = "Code has been sent to 0909u009")

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = "",
                onValueChange = {},
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = "Resend code in 55s")
        }

        // Nút ở dưới cùng
        Button(
            onClick = { /* TODO */ },
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
        ) {
            Text("Verify")
        }
    }
}

@Composable
fun BoxItem(modifier: Modifier = Modifier,color:Color, size : Dp = 100.dp){
    Box(
        modifier = modifier
        .size(size)
        .background(color = color))
}

@Composable
fun ScrollBoxItem(){
    Box(){
        Column( // hoặc scroll theo chiều ngang => dùng Row
            modifier = Modifier.size(300.dp, 400.dp)
                .background(color = Color.Black)
                .verticalScroll(rememberScrollState())
        ) {
            BoxItem(color = Color.Blue)
            BoxItem(color = Color.Red)
            BoxItem(color = Color.Yellow)
            BoxItem(color = Color.Blue)
            BoxItem(color = Color.Red)
            BoxItem(color = Color.Yellow)
        }
    }
}

@Composable
fun Space(){
    Spacer(modifier = Modifier.height(36.dp))
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    JetPackCompose7_BasicLayoutTheme  {
        //HomeScreen()
        //ForgotPassword()
        ScrollBoxItem()
    }
}