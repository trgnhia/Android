
package com.example.mypickerapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.chargemap.compose.numberpicker.NumberPicker
import com.example.mypickerapp.ui.theme.MyPickerAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyPickerAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PickerScreen()
                }
            }
        }
    }
}

@Composable
fun PickerScreen() {
    var name by remember { mutableStateOf("") }
    var selectedHour by remember { mutableStateOf(8) }
    var selectedWeight by remember { mutableStateOf(60) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Nhập tên của bạn") },
            modifier = Modifier.fillMaxWidth()
        )

        Text(text = "Chọn giờ thông báo", style = MaterialTheme.typography.titleMedium)
        NumberPicker(
            value = selectedHour,
            range = 0..23,
            onValueChange = { selectedHour = it }
        )

        Text(text = "Chọn cân nặng", style = MaterialTheme.typography.titleMedium)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(100) { index ->
                    val weight = 30 + index
                    Button(
                        onClick = { selectedWeight = weight },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (selectedWeight == weight) MaterialTheme.colorScheme.primary
                            else MaterialTheme.colorScheme.surfaceVariant
                        )
                    ) {
                        Text("$weight kg")
                    }
                }
            }
        }

        Text("👤 $name - 🕗 $selectedHour giờ - ⚖️ $selectedWeight kg")
    }
}
