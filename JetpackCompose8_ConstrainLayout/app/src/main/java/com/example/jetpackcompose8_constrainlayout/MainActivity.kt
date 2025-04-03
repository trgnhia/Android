package com.example.jetpackcompose8_constrainlayout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.jetpackcompose8_constrainlayout.ui.theme.JetpackCompose8_ConstrainLayoutTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackCompose8_ConstrainLayoutTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    HomeScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun HomeScreen(modifier: Modifier = Modifier){

}

@Composable
fun Ingredients(modifier: Modifier = Modifier){
    ConstraintLayout() {
        Text(text = "Ingredients", style = TextStyle(
            fontWeight = FontWeight.SemiBold,
            fontSize = 28.sp,
            lineHeight = 14.sp,
            color = colorResource(id = R.color.textColor)
        ))
    }
}

@Composable
fun Ingredients(
    @DrawableRes icon : Int,
    value : Int,
    unit : String?,
    name : String,
    modifier: Modifier){
    ConstraintLayout (
        modifier = modifier
            .background(color = colorResource(id = R.color.backgroundColor), shape = CircleShape)
            .border(BorderStroke(width = 1.dp, color = colorResource(id = R.color.borderColor)))
    ) {
        val horizontalGuideLine50 = createGuidelineFromTop(0.5f)
        val imgIcon = createRef()
        Image(painter = painterResource(id = icon) ,
            contentDescription = null,
            modifier = Modifier.constrainAs(imgIcon){
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(horizontalGuideLine50)
                height = Dimension.fillToConstraints
            },
            contentScale = ContentScale.FillHeight
            )
        val (tvValue, tvUnit, tvName) = createRefs()
        val verticalGuideLine = createGuidelineFromStart(0.5f)
        Text(text = value.toString(), style = TextStyle(
            fontWeight = FontWeight.SemiBold,
            fontSize = 28.sp,
            lineHeight = 14.sp,
            color = colorResource(id = R.color.textColor)
        ),
            modifier = modifier.constrainAs(tvValue){
                top.linkTo(horizontalGuideLine50, margin = 2.dp)
                end.linkTo(verticalGuideLine, margin = 2.dp)

            }
            )
        unit?.let {
            Text(text = unit, style = TextStyle(
                fontWeight = FontWeight.SemiBold,
                fontSize = 12.sp,
                lineHeight = 14.sp,
                color = colorResource(id = R.color.textColor)
            ),
                modifier = modifier.constrainAs(tvUnit){
                    top.linkTo(tvValue.bottom, margin = 2.dp)
                    end.linkTo(tvValue.end)

                }
            )
        }
        val endGuideLine20 = createGuidelineFromEnd(0.2f)
        val Barier = createBottomBarrier(tvValue,tvUnit)
        Text(text = name, style = TextStyle(
            fontWeight = FontWeight.SemiBold,
            fontSize = 12.sp,
            lineHeight = 14.sp,
            color = colorResource(id = R.color.textColor)
        ),
            modifier = modifier.constrainAs(tvName){
                start.linkTo(verticalGuideLine)
                bottom.linkTo(Barier)
                top.linkTo(tvValue.top)
                end.linkTo(endGuideLine20)
                width = Dimension.fillToConstraints
            },
            maxLines = 2,
            textAlign = TextAlign.Start
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun IngredientPreview() {
    Row(){
        Ingredients(
            icon = R.drawable.image_lemon,
            value = 30,
            unit = "ml",
            name = "lemon juice",
            modifier = Modifier.size((150.dp)))
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ĐefaultPreview() {
    JetpackCompose8_ConstrainLayoutTheme {
        HomeScreen()
    }
}