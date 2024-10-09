package com.example.superheroes

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.superheroes.model.Hero

// This creates a simple card
@Composable
fun HeroCard(
    @StringRes labelRes: Int,
    @StringRes descriptionRes: Int,
    @DrawableRes painterRes: Int,
    modifier: Modifier = Modifier
) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        // The clip modifier changes the shape of the composable without modifying its
        // actual size
        modifier = modifier.clip(MaterialTheme.shapes.medium)
    ) {
        Row(
            modifier = Modifier.padding(16.dp)
        ) {
            Column(
                // The weight modifier tells this object to take up the remaining space
                // when used in this way, however if two sibling objects have a weight of 1
                // they will both take up half of the available space.
                // 1 + 1 = 2
                // 1 / 2 = half
                Modifier.weight(1f)
            ) {
                Text(
                    text = stringResource(labelRes),
                    style = MaterialTheme.typography.displaySmall
                )
                Text(
                    text = stringResource(descriptionRes),
                    style = MaterialTheme.typography.bodyLarge,
                )
            }
            Spacer(Modifier.width(16.dp))
            Image(
                painter = painterResource(painterRes),
                contentDescription = null,
                Modifier
                    .size(72.dp)
                    .clip(MaterialTheme.shapes.small)
            )
        }
    }
}

@Composable
fun HeroList(
    heroList: List<Hero>,
    modifier: Modifier = Modifier
) {
    // In this case a lazy column is not required but in a larger program with a more
    // significant list that needs to be displayed, the lazy column becomes important because
    // it only loads the items it contains that are actually on screen (Not the ones that are
    // off screen due to factors such as scrolling)
    LazyColumn(modifier = modifier) {
        // The to add items into the column we can specify them using the items function
        // the integer passed into the items declares how many items there are, so then the
        // items function will run the code inside as many times as there are items, and each
        // time the code runs it passes in an invisible variable because technically it is a
        // lambda function, which defaults to the name it. The variable's value is an integer
        // that goes up by one each time it runs, so it can be used to access the next
        // item in an array as seen here each time it runs.
        items(heroList.size) {
            HeroCard(
                labelRes = heroList[it].nameRes,
                descriptionRes = heroList[it].descriptionRes,
                painterRes = heroList[it].imageRes,
                Modifier.padding(start = 16.dp, end = 16.dp, bottom = 8.dp)
            )
        }
    }
}