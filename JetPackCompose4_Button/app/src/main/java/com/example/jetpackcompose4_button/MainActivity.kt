package com.example.jetpackcompose4_button

import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcompose4_button.ui.theme.JetPackCompose4_ButtonTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetPackCompose4_ButtonTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    // Gọi HomeScreen() và truyền innerPadding để đảm bảo layout không bị che mất
                    HomeScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}


@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(24.dp)) {
      SimpleButton()
        CommonSpace()
        DisableButton()
        CommonSpace()
        RoundedConerButton()
        CommonSpace()
        BorderButton()
        CommonSpace()
        ElevationButton()
        TextButton(onClick = {}) {
            Text("Text button")
        }
        IconButton(onClick = {}) {
            Icon(imageVector = Icons.Default.Person, contentDescription = null)
        }
    }
}

// có thể dùng clickable modifier cho coloumn, texy, vv cho các trường hợp cần thiết
// xử lý các sự kiện double tap, long press ngoài onClick

@Composable
fun SimpleButton() {
    Button(
        onClick = {},
        colors = ButtonDefaults.buttonColors(
            contentColor = Color.DarkGray,
            containerColor = Color.Red,
            // Thay vì backgroundColor, dùng containerColor (Material3)

        )
    ) {
        Column {
            Icon(Icons.Default.Settings, contentDescription = "")
            Text("Click here")
        }
    }
}

@Composable
fun RoundedConerButton(){
    Button(
        onClick = {},
        shape = RoundedCornerShape(24.dp),
        colors = ButtonDefaults.buttonColors(
            contentColor = Color.DarkGray,
            containerColor = Color.Red
        )
    ){
        Text(text = "Button is bo góc")
    }
}

@Composable
fun BorderButton(){
    Button(
        onClick = {},
        shape = RoundedCornerShape(24.dp),
        border = BorderStroke(2.dp, Color.Blue)
    ){
        Text(text = "check out", color = Color.Blue)
    }
}

fun Button(onClick: () -> Unit, shape: Unit, function: () -> Unit) {

}

@Composable
fun DisableButton(){
    Button(
        onClick = {},
        colors = ButtonDefaults.buttonColors(
            disabledContentColor = Color.White,
        ),
        enabled = false
    ) {
        Text("Disabled Button")
    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Composable
fun ElevationButton() {
    Button(
        onClick = {},
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White, // Sửa lỗi thiếu tên tham số
            contentColor = Color.Black // Để chữ dễ nhìn hơn
        ),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 10.dp,
            pressedElevation = 15.dp,
            disabledElevation = 0.dp
        )
    ) {
        Text("Text")
    }
}

@Composable
fun CommonSpace() {
    Spacer(modifier = Modifier.height(12.dp))
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetPackCompose4_ButtonTheme {
        Greeting("Android")
    }
}