package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArtSpaceTheme {
                ArtSpaceApp()
            }
        }
    }
}

@Preview ( showBackground = true )
@Composable
fun Preview() {
    ArtSpaceApp()
}

@Composable
fun ArtSpaceApp(modifier: Modifier = Modifier) {
    var imagePainterId by remember { mutableIntStateOf(R.drawable.art_1) }
    var imagePainter = painterResource(imagePainterId)
    var title = when (imagePainterId) {
        R.drawable.art_1 -> stringResource(id = R.string.art_1_title)
        R.drawable.art_2 -> stringResource(id = R.string.art_2_title)
        R.drawable.art_3 -> stringResource(id = R.string.art_3_title)
        R.drawable.art_4 -> stringResource(id = R.string.art_4_title)
        R.drawable.art_5 -> stringResource(id = R.string.art_5_title)
        R.drawable.art_6 -> stringResource(id = R.string.art_6_title)
        R.drawable.art_7 -> stringResource(id = R.string.art_7_title)
        R.drawable.art_8 -> stringResource(id = R.string.art_8_title)
        R.drawable.art_9 -> stringResource(id = R.string.art_9_title)
        else -> stringResource(R.string.error)
    }
    var info = when (imagePainterId) {
        R.drawable.art_1 -> stringResource(id = R.string.art_1_info)
        R.drawable.art_2 -> stringResource(id = R.string.art_2_info)
        R.drawable.art_3 -> stringResource(id = R.string.art_3_info)
        R.drawable.art_4 -> stringResource(id = R.string.art_4_info)
        R.drawable.art_5 -> stringResource(id = R.string.art_5_info)
        R.drawable.art_6 -> stringResource(id = R.string.art_6_info)
        R.drawable.art_7 -> stringResource(id = R.string.art_7_info)
        R.drawable.art_8 -> stringResource(id = R.string.art_8_info)
        R.drawable.art_9 -> stringResource(id = R.string.art_9_info)
        else -> stringResource(R.string.error)
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Image(
            painter = imagePainter,
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .width(320.dp)
                .height(380.dp)
                .padding(16.dp)
                .shadow(8.dp, )
        )
        Spacer(modifier = Modifier.height(16.dp))
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .background(colorResource(id = R.color.background))
                .padding(16.dp)
                .width(320.dp)
        ) {
            Text(
                text = title,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = info
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row (
            horizontalArrangement = Arrangement.SpaceBetween
            ,modifier = Modifier.width(280.dp)
        ) {
            Button(
                content = {Text( text = stringResource(R.string.previous))},
                onClick = {
                    imagePainterId = when (imagePainterId) {
                        R.drawable.art_9 -> R.drawable.art_8
                        R.drawable.art_8 -> R.drawable.art_7
                        R.drawable.art_7 -> R.drawable.art_6
                        R.drawable.art_6 -> R.drawable.art_5
                        R.drawable.art_5 -> R.drawable.art_4
                        R.drawable.art_4 -> R.drawable.art_3
                        R.drawable.art_3 -> R.drawable.art_2
                        R.drawable.art_2 -> R.drawable.art_1
                        R.drawable.art_1 -> R.drawable.art_9
                        else -> 0
                    }
                },
                colors = ButtonDefaults. buttonColors(containerColor = colorResource(id = R.color.black)),
                modifier = Modifier.width(120.dp)
            )
            Button(
                content = { Text( text = stringResource(R.string.next)) },
                onClick = {
                    imagePainterId = when (imagePainterId) {
                        R.drawable.art_1 -> R.drawable.art_2
                        R.drawable.art_2 -> R.drawable.art_3
                        R.drawable.art_3 -> R.drawable.art_4
                        R.drawable.art_4 -> R.drawable.art_5
                        R.drawable.art_5 -> R.drawable.art_6
                        R.drawable.art_6 -> R.drawable.art_7
                        R.drawable.art_7 -> R.drawable.art_8
                        R.drawable.art_8 -> R.drawable.art_9
                        R.drawable.art_9 -> R.drawable.art_1
                        else -> 0
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.black)),
                modifier = Modifier.width(120.dp)
            )
        }
    }
}