package com.example.jetpackcompose_nav.ui.theme.checkout
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CheckoutSuccessScreen(
    goHomeAction: () -> Unit,
    viewOrderDetailAction: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Text("Thank you for your order!", style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(24.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom,
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedButton(onClick = {
                goHomeAction()
            }, modifier = Modifier.weight(1f)) {
                Text(text = "Go home")
            }
            Button(onClick = {
                viewOrderDetailAction("123")
            }, modifier = Modifier.weight(1f)) {
                Text(text = "Check order")
            }
        }
    }
}
