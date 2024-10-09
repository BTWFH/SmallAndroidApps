package com.example.superheroes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.superheroes.model.HeroesRepository
import com.example.superheroes.ui.theme.SuperheroesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SuperheroesApp()
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun SuperheroPreview() {
    SuperheroesApp()
}

// This lets the program know that you indeed want to use the
// experimental classes, it is important because some classes
// can have bugs before a stable final release
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SuperheroesApp(modifier: Modifier = Modifier) {
    SuperheroesTheme {
        // A scaffold allows you to dynamically display your content to the screen
        // that does not intrude on any built in parts of the devices screen or the status bar
        Scaffold(
            topBar = {
                // The center aligned top bar is an experimental composable that allows you
                // to create a simple top bar that is aligned to the center
                CenterAlignedTopAppBar(
                    title = {
                        Text(
                            text = stringResource(R.string.app_name),
                            style = MaterialTheme.typography.displayLarge,
                        )
                    },
                    modifier = modifier
                )
            },
            content = { paddingValues ->
                HeroList(HeroesRepository.heroes, modifier = Modifier.padding(paddingValues))
            },
            modifier = modifier
        )
    }
}