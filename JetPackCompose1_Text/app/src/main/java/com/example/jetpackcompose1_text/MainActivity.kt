package com.example.jetpackcompose1_text

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackcompose1_text.ui.theme.JetPackCompose1_TextTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetPackCompose1_TextTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    HomeScreen(modifier = Modifier.padding(innerPadding)) // ✅ Sửa lỗi
                }
            }
        }
    }
}

@Composable
fun HomeScreen(modifier: Modifier = Modifier) { // ✅ Thêm modifier
    Column() {
        Greeting()
        Spacer(modifier = Modifier.height(12.dp))
        MultipleStylesText()
    }
}

@Composable
fun Greeting( modifier: Modifier = Modifier) {
    Text(
        color = Color.Magenta,
        fontSize = 30.sp,
        fontStyle = FontStyle.Italic,
        fontWeight = FontWeight.ExtraBold,
        text = stringResource(R.string.dump_text),
        modifier = modifier,
        textAlign = TextAlign.Justify,
        fontFamily = FontFamily.Cursive,
        //style = MaterialTheme.typography.bodyLarge
        textDecoration = TextDecoration.Underline,
        maxLines = 2,
        overflow = TextOverflow.Ellipsis
    )
}

@Composable
fun MultipleStylesText(){
    Text(text = buildAnnotatedString {
        withStyle(style = SpanStyle(color = Color.Blue,
            textDecoration = TextDecoration.Underline)){
            append("H")
        }
        append("ello!")
        withStyle(style = SpanStyle(color = Color.Red)){
            append("F")
        }
        append("rank")
    })
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JetPackCompose1_TextTheme {
        HomeScreen()
    }
}
