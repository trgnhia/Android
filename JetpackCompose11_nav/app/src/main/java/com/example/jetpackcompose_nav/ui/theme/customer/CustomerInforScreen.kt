package com.example.jetpackcompose_nav.ui.theme.customer
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CustomerInfoScreen(onClickBack: () -> Unit){
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(24.dp)) {
        Text(text = "Customer Information")
        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = {

        }) {
            Text("Back")
        }
    }
}
