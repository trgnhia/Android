package com.example.btth4_002.theme

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val dbHelper = remember { DatabaseHelper(context) }
    var name by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var contacts by remember { mutableStateOf<List<Contact>>(emptyList()) }
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    @Composable
    fun ContactItem(contact: Contact, onDelete: () -> Unit) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            elevation = CardDefaults.cardElevation(4.dp),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(text = "Tên: ${contact.name}", style = MaterialTheme.typography.bodyLarge)
                    Text(text = "SĐT: ${contact.phone}", style = MaterialTheme.typography.bodyMedium)
                }
                IconButton(onClick = onDelete) {
                    Icon(Icons.Default.Delete, contentDescription = "Xóa", tint = Color.Red)
                }
            }
        }
    }


    fun loadContacts() {
        coroutineScope.launch {
            val result = withContext(Dispatchers.IO) {
                dbHelper.getAllContacts()
            }
            contacts = result
        }
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {

        Text("Quản lý danh bạ", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Tên") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = phone,
            onValueChange = { phone = it },
            label = { Text("Số điện thoại") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp)
        )
        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = {
                    if (name.isNotBlank() && phone.isNotBlank()) {
                        coroutineScope.launch {
                            val success = withContext(Dispatchers.IO) {
                                dbHelper.insertContact(name, phone)
                            }
                            if (success) {
                                name = ""
                                phone = ""
                                loadContacts()
                                snackbarHostState.showSnackbar("Đã lưu liên hệ")
                            } else {
                                snackbarHostState.showSnackbar("Lỗi khi lưu dữ liệu")
                            }
                        }
                    } else {
                        coroutineScope.launch {
                            snackbarHostState.showSnackbar("Vui lòng nhập đầy đủ thông tin")
                        }
                    }
                }
            ) { Text("Lưu") }

            Button(onClick = {
                loadContacts()
            }) {
                Text("Hiển thị")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (contacts.isNotEmpty()) {
            Text("Danh sách liên hệ:", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))
            LazyColumn {
                items(contacts) { contact ->
                    ContactItem(contact = contact, onDelete = {
                        coroutineScope.launch {
                            val deleted = withContext(Dispatchers.IO) {
                                dbHelper.deleteContactById(contact.id)
                            }
                            if (deleted) {
                                loadContacts()
                                snackbarHostState.showSnackbar("Đã xóa liên hệ")
                            } else {
                                snackbarHostState.showSnackbar("Không thể xóa liên hệ")
                            }
                        }
                    })
                }
            }
        }

        SnackbarHost(hostState = snackbarHostState)
    }
}
