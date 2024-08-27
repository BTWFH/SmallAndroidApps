package com.example.businesscard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.businesscard.ui.theme.BusinessCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BusinessCardTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceAround,
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Column ( horizontalAlignment = Alignment.CenterHorizontally ) {
                            Body(
                                imageID = R.drawable.portrait,
                                imageSize = 355,
                                nameID = R.string.full_name,
                                titleID = R.string.title,
                            )
                        }
                        Column {
                            ContactInfo(
                                emailImageID = R.drawable.email_icon,
                                emailID = R.string.email,
                                socialImageID = R.drawable.share_icon,
                                socialID = R.string.social,
                                phoneImageID = R.drawable.phone_icon,
                                phoneID = R.string.phone,
                                modifier = Modifier.padding(vertical = 4.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BusinessCardPreview() {
    BusinessCardTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceAround,
                modifier = Modifier.padding(16.dp)
            ) {
                Column ( horizontalAlignment = Alignment.CenterHorizontally ) {
                    Body(
                        imageID = R.drawable.portrait,
                        imageSize = 355,
                        nameID = R.string.full_name,
                        titleID = R.string.title,
                    )
                }
                Column {
                    ContactInfo(
                        emailImageID = R.drawable.email_icon,
                        emailID = R.string.email,
                        socialImageID = R.drawable.share_icon,
                        socialID = R.string.social,
                        phoneImageID = R.drawable.phone_icon,
                        phoneID = R.string.phone,
                        modifier = Modifier.padding(vertical = 4.dp)
                    )
                }
            }
        }
    }
}


@Composable
fun Body(
    imageID: Int,
    imageSize: Int,
    nameID: Int,
    titleID: Int,
    modifier: Modifier = Modifier
) {
        Image(
            painter = painterResource(imageID),
            contentDescription = null,
            modifier = modifier.size(imageSize.dp)
        )
        Text(
            text = stringResource(nameID),
            fontSize = 48.sp,
            modifier = modifier
        )
        Text(
            text = stringResource(titleID),
            modifier = modifier
        )
}

@Composable
fun ContactInfo(
    emailImageID: Int, emailID: Int,
    socialImageID:Int, socialID: Int,
    phoneImageID: Int, phoneID: Int,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
    ) {
        Image(
            painter = painterResource(emailImageID),
            contentDescription = null
        )
        Text(
            text = " " + stringResource(emailID)
        )

    }
    Row(
        modifier = modifier
    ) {
        Image(
            painter = painterResource(socialImageID),
            contentDescription = null
        )
        Text(
            text = " " + stringResource(socialID)
        )
    }
    Row(
        modifier = modifier
    ) {
        Image(
            painter = painterResource(phoneImageID),
            contentDescription = null
        )
        Text(
            text = " " + stringResource(phoneID)
        )
    }
}
