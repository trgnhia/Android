package com.example.jetpackcompose6_txtfield

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackcompose6_txtfield.ui.theme.JetPackCompose6_TxtFieldTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetPackCompose6_TxtFieldTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    HomeScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun CommonSpace(){
    Spacer(modifier = Modifier.height(24.dp))
}


@Composable
fun TextFielDemo(){
    var firstName by remember {
        mutableStateOf("")
    }
    val keyBoardController = LocalSoftwareKeyboardController.current
    TextField(
        value = firstName,
        onValueChange = {
            newValue -> firstName = newValue
        },
        textStyle = TextStyle(
            color = Color.Blue,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        ),
        label = {Text("firstName")},
        placeholder = {Text(text = "Enter your firstName")},
        leadingIcon = {Icon(Icons.Default.Person, contentDescription = "firstName")},
        trailingIcon = {
            IconButton(onClick = {
                firstName = ""
            }) {
                Icon(Icons.Default.Close, contentDescription = null)
            }
        },
        colors = TextFieldDefaults.colors(
            focusedTextColor = Color.Blue,
            unfocusedTextColor = Color.Gray,
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.LightGray,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            cursorColor = Color.Yellow,
            unfocusedTrailingIconColor = Color.Magenta,
            unfocusedLeadingIconColor = Color.Magenta
        ),
        shape = RoundedCornerShape(16.dp),
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done,
           // keyboardType = KeyboardType.Phone =>chi hien ban phim so
            capitalization = KeyboardCapitalization.Words // nhap ten du dong viet hoa chu cai dau
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                keyBoardController?.hide()
            }
        )
    )
}

@Composable
fun OutlineTextField(){
    var email by remember {
        mutableStateOf("")
    }
    OutlinedTextField(
        value = email,
        onValueChange = { newValue ->
            email = newValue
        },
        placeholder = {Text("username/email")},
        label = { Text(text = "username/email") },
        leadingIcon = {Icon(Icons.Default.Email, contentDescription = "null")},
    )
}

@Composable
fun PassWord(){
    var password by remember {
        mutableStateOf("")
    }
    var isShow by remember {
        mutableStateOf(false)
    }
    OutlinedTextField(
        value = password,
        onValueChange = {
            password = it
        },
        placeholder = {Text("Enter pass")},
        label = { Text(text = "username/email") },
        leadingIcon = {Icon(Icons.Default.Lock, contentDescription = "null")},
        trailingIcon = {
            IconButton(
                onClick = {
                isShow = !isShow
            } ){
                Icon(
                    if(isShow) Icons.Default.Info else Icons.Default.Done,
                    contentDescription = null
                )
            }
        },
        visualTransformation = if(isShow) VisualTransformation.None else PasswordVisualTransformation()
    )
}

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Column(modifier = Modifier.padding(24.dp)) {
        TextFielDemo()
        CommonSpace()
        OutlineTextField()
        CommonSpace()
        PassWord()
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    JetPackCompose6_TxtFieldTheme {
        HomeScreen()
    }
}