package com.example.jetpackcompose5_radiobtn_cbox

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcompose5_radiobtn_cbox.ui.theme.JetPackCompose5_RadioBtn_CboxTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetPackCompose5_RadioBtn_CboxTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    HomeScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun HomeScreen(modifier: Modifier = Modifier){
    Column(modifier = Modifier.padding(24.dp)) {
        RadioButton(selected = true, onClick = {}, colors = RadioButtonDefaults.colors(
            selectedColor = Color.Red,
            unselectedColor = Color.Green,
            disabledSelectedColor = Color.Gray
        ))
        RadioButton(selected = false, onClick = {}, colors = RadioButtonDefaults.colors(
            selectedColor = Color.Red,
            unselectedColor = Color.Green,
            disabledSelectedColor = Color.Gray
        ))
        RadioButton(
            enabled = false,
            selected = false, onClick = {}, colors = RadioButtonDefaults.colors(
            selectedColor = Color.Red,
            unselectedColor = Color.Green,
            disabledSelectedColor = Color.Gray
        ))
        CommonSpace()
        RadioButtonWithTile("male")
        CommonSpace()
        CustomRadioButton("hehe")
        CommonSpace()
        Checkbox(
            checked = true,
            onCheckedChange = {

            },
            colors = CheckboxDefaults.colors(
                checkedColor = Color.Red,
                uncheckedColor = Color.Green
            )
        )
        Checkbox(
            checked = false,
            onCheckedChange = {

            },
            colors = CheckboxDefaults.colors(
                checkedColor = Color.Red,
                uncheckedColor = Color.Green
            )
        )
        CommonSpace()
        CustomCheckBox("tomato")
        CommonSpace()
        CustomCheckBox2("potato")
    }
}

@Composable
fun RadioButtonWithTile(tile:String){
    var isSelected by remember {
        mutableStateOf(false)
    }
    Row(
        modifier = Modifier.selectable(
            selected = isSelected,
            onClick = {isSelected = !isSelected},
            role = Role.RadioButton
        )
    ){
        RadioButton(selected = isSelected, onClick = null,colors = RadioButtonDefaults.colors(
            selectedColor = Color.Red,
            unselectedColor = Color.Green,
            disabledSelectedColor = Color.Gray))
        Text(text = tile, modifier = Modifier.padding(16.dp))
    }
}

@Composable
fun CommonSpace(){
    Spacer(modifier = Modifier.height(20.dp))
}
// không thể thay đổi UI của radio nên tự custom nêếu cần icon
@Composable
fun CustomRadioButton(tile: String){
    var isSelected by remember {
        mutableStateOf(false)
    }
    Row(
        modifier = Modifier.selectable(
            selected = isSelected,
            onClick = {isSelected = !isSelected},
            role = Role.RadioButton
        )
    ){
        val iconRadio = if(isSelected) Icons.Default.CheckCircle else Icons.Default.Check
        Icon(iconRadio, contentDescription = null)
        Text(text = tile, modifier = Modifier.padding(16.dp))
    }
}

@Composable
fun CustomCheckBox(title : String){
    var isChecked by remember {
        mutableStateOf(false)
    }
    return Row(){
        Checkbox(
            checked = isChecked,
            onCheckedChange = {
                isChecked = it
            },
            colors = CheckboxDefaults.colors(
                checkedColor = Color.Red,
                uncheckedColor = Color.Green)
        )
        Text(text = title)
    }
}

@Composable
// xử lý sự kiện on click qua row để chon vào hàng vẫn tính thay vì bấm hẳn vào ô
fun CustomCheckBox2(tile: String){
    var isSelected by remember {
        mutableStateOf(false)
    }
    Row(
        modifier = Modifier.selectable(
            selected = isSelected,
            onClick = {isSelected = !isSelected},
            role = Role.Checkbox
        )
    ){
        val iconCheck = if(isSelected) Icons.Default.CheckCircle else Icons.Default.Check
        Icon(iconCheck, contentDescription = null)
        Text(text = tile, modifier = Modifier.padding(16.dp))
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    JetPackCompose5_RadioBtn_CboxTheme {
        HomeScreen()
    }
}