package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.StringRes
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

@Preview ( showBackground = true, showSystemUi = true )
@Composable
fun Preview() {
    ArtSpaceApp()
}

@Composable
fun ArtSpaceApp(modifier: Modifier = Modifier) {
    var state by remember { mutableIntStateOf(0) }

    val images = arrayOf(
        R.drawable.art_1,
        R.drawable.art_2,
        R.drawable.art_3,
        R.drawable.art_4,
        R.drawable.art_5,
        R.drawable.art_6,
        R.drawable.art_7,
        R.drawable.art_8,
        R.drawable.art_9
    )
    val titles = arrayOf(
        R.string.art_1_title,
        R.string.art_2_title,
        R.string.art_3_title,
        R.string.art_4_title,
        R.string.art_5_title,
        R.string.art_6_title,
        R.string.art_7_title,
        R.string.art_8_title,
        R.string.art_9_title
    )
    val infos = arrayOf(
        R.string.art_1_info,
        R.string.art_2_info,
        R.string.art_3_info,
        R.string.art_4_info,
        R.string.art_5_info,
        R.string.art_6_info,
        R.string.art_7_info,
        R.string.art_8_info,
        R.string.art_9_info
    )

    val image = images[state]
    val title = titles[state]
    val info = infos[state]

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Image(
            painter = painterResource(image),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .width(320.dp)
                .height(380.dp)
                .shadow(8.dp)
        )
        Spacer(modifier = Modifier.height(64.dp))
        Label(
            370,
            title,
            info
        )
        Spacer(modifier = Modifier.height(16.dp))
        Buttons(
            320,
            {
                if (state < 8) {
                    state += 1
                }
                else {
                    state = 0
                }
            },
            {
                if (state > 0) {
                    state -= 1
                }
                else {
                    state = 8
                }
            }
        )
    }
}

@Composable
fun Label(
    width: Int,
    @StringRes title: Int,
    @StringRes info: Int,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .width(width.dp)
            .background(colorResource(id = R.color.background))
            .padding(16.dp)
    ) {
        Text(
            fontSize = 24.sp,
            text = stringResource(title),
            fontWeight = FontWeight.Bold,
            modifier = modifier
        )
        Text(
            text = stringResource(info),
            modifier = modifier
        )
    }
}

@Composable
fun Buttons(
    width: Int,
    next: () -> Unit,
    previous: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row (
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier.width(width.dp)
    ) {
        Button(
            content = {Text( text = stringResource(R.string.previous))},
            onClick = next,
            colors = ButtonDefaults. buttonColors(containerColor = colorResource(id = R.color.black)),
            modifier = modifier.width(120.dp)
        )
        Button(
            content = { Text( text = stringResource(R.string.next)) },
            onClick = previous,
            colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.black)),
            modifier = modifier.width(120.dp)
        )
    }
}