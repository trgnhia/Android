package com.example.jetpackcompose3_image

import android.os.Bundle
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcompose3_image.ui.theme.JetPackCompose3_imageTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetPackCompose3_imageTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    // Gọi HomeScreen() và truyền innerPadding để đảm bảo layout không bị che mất
                    HomeScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(24.dp)) {
        Greeting(name = "Android") // Hiển thị tiêu đề
        BannerCompose(ContentScale.Crop) // Hiển thị hình ảnh
       Spacer(modifier = Modifier.height(12.dp))
        CircleAvatar()
//        BannerCompose(ContentScale.Fit)
//        Spacer(modifier = Modifier.height(12.dp))
//        BannerCompose(ContentScale.Inside)


//        VectorImageComponent()
//        Spacer(modifier = Modifier.height(12.dp))
//        CustomPainterImageCompose()
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Composable
fun BannerCompose(contentScale: ContentScale) {
    Image(
        painter = painterResource(id = R.drawable.banner),
        contentDescription = "banner hehe",
        modifier = Modifier.height(150.dp).fillMaxWidth()
            .shadow(
                elevation = 8.dp,
                shape = RoundedCornerShape(size = 8.dp)
            ).aspectRatio(2f),
        alignment = Alignment.TopEnd,
        contentScale = contentScale
    )
}

@Composable
fun CircleAvatar(){
   Image(
       painter = painterResource(id = R.drawable.avatar),
       contentDescription = "avartar",
       contentScale = ContentScale.Inside,
       modifier = Modifier
           .size(150.dp)
           .border(BorderStroke(2.dp, color = Color.Gray), shape = CircleShape)
   )
}

@Composable
fun CustomPainterImageCompose(){
    Image(
        painter = ColorPainter(Color.Red),
        contentDescription = null,
        modifier = Modifier.size(50.dp)
    )
}

@Composable
fun VectorImageComponent(){
    Image(
        imageVector = Icons.Filled.Person,
        contentDescription = "person"
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    JetPackCompose3_imageTheme {
        HomeScreen()
    }
}
