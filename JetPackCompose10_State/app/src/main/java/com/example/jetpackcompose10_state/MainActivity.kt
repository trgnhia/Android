package com.example.jetpackcompose10_state

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.jetpackcompose10_state.ui.theme.JetPackCompose10_StateTheme

// Màn hình chính của ứng dụng
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Kích hoạt full màn hình không giới hạn layout
        enableEdgeToEdge()

        // Giao diện Compose được hiển thị ở đây
        setContent {
            JetPackCompose10_StateTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LoginScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

// Màn hình đăng nhập chính
@Composable
fun LoginScreen(modifier: Modifier = Modifier) {
    // Tạo ViewModel để lưu và quản lý state
    val loginViewModel: LoginViewModel = viewModel()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Hiển thị tiêu đề
        Log.e("hehe", "login start!!")
        Welcome()
        Log.e("hehe", "login end!!")

        // TextField nhập email (dữ liệu được lưu trong ViewModel)
        EmailField(loginViewModel.email.value) {
            loginViewModel.updateEmail(it)
        }

        Space()

        // TextField nhập password (cũng lưu trong ViewModel)
        PasswordField(loginViewModel.password.value) {
            loginViewModel.updatePassword(it)
        }

        Space()

        // Nút Login (hiện đang chỉ in Log dữ liệu)
        Button(onClick = {
            Log.d("Login", "Email: ${loginViewModel.email.value}, Password: ${loginViewModel.password.value}")
        }) {
            Text("Login")
        }
    }
}

// TextField cho email (stateless)
@Composable
fun EmailField(email: String, onEmailChange: (String) -> Unit) {
    OutlinedTextField(
        value = email,
        onValueChange = onEmailChange,
        label = { Text("Email") }
    )
}

// TextField cho password (stateless)
@Composable
fun PasswordField(password: String, onPasswordChange: (String) -> Unit) {
    OutlinedTextField(
        value = password,
        onValueChange = onPasswordChange,
        label = { Text("Password") }
    )
}

// Dòng chữ chào mừng
@Composable
fun Welcome() {
    Log.e("hehe", "welcome start")
    Text(text = "Login to your account")
    Log.e("hehe", "welcome end")
}

// Tạo khoảng cách giữa các thành phần
@Composable
fun Space() {
    Spacer(modifier = Modifier.height(24.dp))
}

// ViewModel quản lý state email và password
class LoginViewModel : ViewModel() {
    // Biến private giữ giá trị
    private val _email = mutableStateOf("")
    val email: State<String> get() = _email

    private val _password = mutableStateOf("")
    val password: State<String> get() = _password

    // Cập nhật email
    fun updateEmail(newEmail: String) {
        _email.value = newEmail
    }

    // Cập nhật password
    fun updatePassword(newPassword: String) {
        _password.value = newPassword
    }
}

// Preview UI khi chưa chạy app (dành cho Android Studio xem trước)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    JetPackCompose10_StateTheme {
        LoginScreen()
    }
}
