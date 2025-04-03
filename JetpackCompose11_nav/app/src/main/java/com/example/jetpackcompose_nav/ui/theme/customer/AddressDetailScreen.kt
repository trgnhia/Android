package com.example.jetpackcompose_nav.ui.theme.customer

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AddressDetailScreen(addressId:String?,saveAddressAndBack: (String?)->Unit){

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(24.dp)) {

        var savableAddressId by rememberSaveable {
            mutableStateOf(addressId)
        }

        if(addressId.isNullOrEmpty()){
            Text(text = "Add new Address")
        }
        else{
            Text(text = "Edit Address with id: $addressId")
        }
        Spacer(modifier = Modifier.height(12.dp))
        OutlinedTextField(value = savableAddressId ?: "", onValueChange = {
            savableAddressId = it
        }, enabled = (addressId.isNullOrEmpty()))
        Spacer(modifier = Modifier.height(12.dp))
        Button(onClick = {
            saveAddressAndBack(savableAddressId)
        }) {
            Text("Save")
        }
    }

}
