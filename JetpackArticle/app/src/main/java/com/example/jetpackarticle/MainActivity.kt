package com.example.jetpackarticle

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackarticle.ui.theme.JetpackArticleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackArticleTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                }
            }
        }
    }
}

@Composable
fun Display(title: String, paragraphs: Array<String>, image: Int, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
    ) {
        Banner(image)
        Article(title, paragraphs)
    }
}

@Composable
fun Banner(image: Int) {
    Image(
        painter = painterResource(id = image),
        contentDescription = null,
        contentScale = ContentScale.FillWidth,
        modifier = Modifier
    )
}

@Composable
fun Article(title: String, paragraphs: Array<String>) {
    Text(
        text = title,
        fontSize = 24.sp,
        modifier = Modifier.padding(16.dp)
    )
    for (paragraph in paragraphs) {
        Text(
            text = paragraph,
            textAlign = TextAlign.Justify,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JetpackArticleTheme {
        val title: String = stringResource(R.string.title)
        val paragraphs: Array<String> = arrayOf(stringResource(R.string.paragraph_1), stringResource(R.string.paragraph_2))
        val image: Int = R.drawable.bg_compose_background
        Display(
            title,
            paragraphs,
            image,
            Modifier.fillMaxSize()
        )
    }
}