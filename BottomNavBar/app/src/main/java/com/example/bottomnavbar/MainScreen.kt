package com.example.bottomnavbar

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.bottomnavbar.data.NavItem
import com.example.bottomnavbar.ui.screens.HomeScreen
import com.example.bottomnavbar.ui.screens.ProfileScreen
import com.example.bottomnavbar.ui.screens.SearchScreen
import com.example.bottomnavbar.ui.screens.SettingsScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    val navItems = listOf(
        NavItem(stringResource(R.string.home), Icons.Default.Home),
        NavItem(stringResource(R.string.search), Icons.Default.Search),
        NavItem(stringResource(R.string.profile), Icons.Default.Person),
        NavItem(stringResource(R.string.settings), Icons.Default.Settings)
    )
    var selectedIndex by remember{ mutableIntStateOf(0) }
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = when(selectedIndex) {
                            0 -> stringResource(R.string.home)
                            1 -> stringResource(R.string.search)
                            2 -> stringResource(R.string.profile)
                            3 -> stringResource(R.string.settings)
                            else -> stringResource(R.string.empty)
                        },
                        fontSize = 40.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
            )
        },
        bottomBar = {
            NavigationBar {
                navItems.forEachIndexed() {index, navItem ->
                    NavigationBarItem(
                        selected = index == selectedIndex,
                        onClick = { selectedIndex = index },
                        icon = { Icon(imageVector = navItem.icon, contentDescription = null) },
                        label = { Text(navItem.label) }
                    )
                }
            }
        }
    ) { paddingValues ->
        ContentScreen(selectedIndex, modifier.padding(paddingValues))
    }
}

@Composable
fun ContentScreen(index: Int, modifier: Modifier = Modifier) {
    when(index) {
        0 -> HomeScreen(modifier)
        1 -> SearchScreen(modifier)
        2 -> ProfileScreen(modifier)
        3 -> SettingsScreen(modifier)
    }
}

@Preview
@Composable
fun MainScreenPreview() {
    MainScreen()
}