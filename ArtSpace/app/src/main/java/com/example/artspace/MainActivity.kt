package com.example.artspace

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
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

    val titles = stringArrayResource(R.array.titles)
    val infos = stringArrayResource(R.array.infos) 

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

        Spacer(modifier = Modifier.height(32.dp))
        Painting(
            image,
            title,
            info,
            Modifier
                .width(320.dp)
                .height(380.dp)
                .background(colorResource(R.color.background))
                .padding(16.dp),
            Modifier
                .background(colorResource(R.color.background))
                .width(360.dp)
                .padding(16.dp),
        )
        Spacer(modifier = Modifier.height(16.dp))
        Buttons(
            { if (state < 8) { state += 1 } else { state = 0 } },
            { if (state > 0) { state -= 1 } else { state = 8 } },
            Modifier.width(320.dp)
        )
    }
}

@Composable
fun Painting(
    @DrawableRes image: Int,
    title: String,
    info: String,
    imageModifier: Modifier = Modifier,
    labelModifier: Modifier = Modifier
) {
    Surface(
        Modifier.wrapContentSize().shadow(8.dp)
    ) {
        Image(
            painter = painterResource(image),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = imageModifier
        )
    }
    Spacer(Modifier.height(32.dp))
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = labelModifier
    ) {
        Text(
            fontSize = 24.sp,
            text = title,
            fontWeight = FontWeight.Bold,
        )
        Text(
            text = info,
        )
    }
}

@Composable
fun Buttons(
    next: () -> Unit,
    previous: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row (
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
    ) {
        Button(
            content = {Text( text = stringResource(R.string.previous))},
            onClick = next,
            colors = ButtonDefaults. buttonColors(containerColor = colorResource(id = R.color.black)),
            modifier = Modifier.width(120.dp)
        )
        Button(
            content = { Text( text = stringResource(R.string.next)) },
            onClick = previous,
            colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.black)),
            modifier = Modifier.width(120.dp)
        )
    }
}