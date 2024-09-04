package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LemonadeTheme {
                LemonApp()
            }
        }
    }
}

@Composable
fun LemonApp() {
    var screen by remember { mutableIntStateOf(1) }
    var taps by remember { mutableIntStateOf(0) }
    var random by remember { mutableIntStateOf(0) }
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        when (screen) {
            1 -> {
                Screen(
                    imagePainter = painterResource(R.drawable.lemon_tree),
                    imageDescription = stringResource(R.string.lemon_tree_content_description),
                    text = stringResource(R.string.tap_tree),
                    modifier = Modifier
                        .wrapContentSize()
                        .clip(RoundedCornerShape(12.dp))
                        .background(colorResource(R.color.green))
                        .clickable { screen = 2; random = Random.nextInt(2, 4) }
                )
            }
            2 -> {
                Screen(
                    imagePainter = painterResource(R.drawable.lemon_squeeze),
                    imageDescription = stringResource(R.string.lemon_content_description),
                    text = stringResource(R.string.squeeze_lemon),
                    modifier = Modifier
                        .wrapContentSize()
                        .clip(RoundedCornerShape(12.dp))
                        .background(colorResource(R.color.green))
                        .clickable {
                            if (taps == random) {
                                taps = 0; screen = 3
                            } else {
                                taps += 1
                            }
                        }
                )
            }
            3 -> {
                Screen(
                    imagePainter = painterResource(R.drawable.lemon_drink),
                    imageDescription = stringResource(R.string.glass_content_description),
                    text = stringResource(R.string.drink_lemonade),
                    modifier = Modifier
                        .wrapContentSize()
                        .clip(RoundedCornerShape(12.dp))
                        .background(colorResource(R.color.green))
                        .clickable { screen = 4 }
                )
            }
            4 -> {
                Screen(
                    imagePainter = painterResource(R.drawable.lemon_restart),
                    imageDescription = stringResource(R.string.empty_glass_content_description),
                    text = stringResource(R.string.empty_glass),
                    modifier = Modifier
                        .wrapContentSize()
                        .clip(RoundedCornerShape(12.dp))
                        .background(colorResource(R.color.green))
                        .clickable { screen = 1 }
                )
            }
            else -> {
                Text(text = "Something went wrong!")
            }
        }
    }
}

@Composable
fun Screen(
    imagePainter: Painter,
    imageDescription: String,
    text: String,
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Surface(
            color = colorResource(R.color.off_yellow),
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
        ) {}
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .background(colorResource(R.color.yellow))
                .padding(16.dp)
        ) {
            Text(
                text = stringResource(R.string.lemonade),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = imagePainter,
                contentDescription = imageDescription,
                modifier = modifier
                    .height(180.dp)
                    .width(180.dp)
            )
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = text,
                fontSize = 18.sp,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LemonadeTheme {
        LemonApp()
    }
}