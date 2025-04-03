package com.example.btth04_01.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.btth04_01.helper.PreferenceHelper

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val preferenceHelper = remember { PreferenceHelper(context) }

    var userName by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }

    Column(modifier = modifier.padding(24.dp)) {
        TitleText("Username")
        UserText(userName) { userName = it }
        Spacer(modifier = Modifier.height(16.dp))
        TitleText("Password")
        PasswordText(password) { password = it }
        Spacer(modifier = Modifier.height(16.dp))
        AllButtons(
            onSave = {
                preferenceHelper.saveUserData(userName, password)
                message = "Dữ liệu đã được lưu!"
            },
            onClear = {
                preferenceHelper.clearUserData()
                userName = ""
                password = ""
                message = "Dữ liệu đã bị xóa!"
            },
            onShow = {
                val (savedUsername, savedPassword) = preferenceHelper.getUserData()
                userName = savedUsername ?: ""
                password = savedPassword ?: ""
                message = "Dữ liệu đã được tải!"
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        if (message.isNotEmpty()) {
            Text(text = message, color = Color.Red)
        }
    }
}


@Composable
fun TitleText(title: String) {
    Text(text = title, color = Color.DarkGray)
}

@Composable
fun UserText(userName: String, onTextChange: (String) -> Unit) {
    OutlinedTextField(
        value = userName,
        onValueChange = onTextChange,
        placeholder = { Text("Enter your username") },
        label = { Text("Tên đăng nhập") },
        leadingIcon = { Icon(Icons.Default.Person, contentDescription = null) }
    )
}

@Composable
fun PasswordText(password: String, onTextChange: (String) -> Unit) {
    var isShow by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = password,
        onValueChange = onTextChange,
        placeholder = { Text("Enter password") },
        label = { Text("Mật khẩu") },
        leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null) },
        trailingIcon = {
            IconButton(onClick = { isShow = !isShow }) {
                Icon(if (isShow) Icons.Default.Check else Icons.Default.Lock, contentDescription = null)
            }
        },
        visualTransformation = if (isShow) VisualTransformation.None else PasswordVisualTransformation()
    )
}

@Composable
fun AllButtons(onSave: () -> Unit, onClear: () -> Unit, onShow: () -> Unit) {
    Row(modifier = Modifier.padding(16.dp)) {
        Button(
            onClick = onSave,
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(contentColor = Color.White),
            modifier = Modifier.padding(end = 8.dp)
        ) { Text(text = "Lưu") }

        Button(
            onClick = onClear,
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(contentColor = Color.White),
            modifier = Modifier.padding(end = 8.dp)
        ) { Text(text = "Xóa") }

        Button(
            onClick = onShow,
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(contentColor = Color.White),
            modifier = Modifier.padding(end = 8.dp)
        ) { Text(text = "Hiển thị") }
    }
}
@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen() {
    HomeScreen()
}
