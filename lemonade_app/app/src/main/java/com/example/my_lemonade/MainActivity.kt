package com.example.my_lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.my_lemonade.ui.theme.My_lemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            My_lemonadeTheme {
                AppScreen()
            }
        }
    }
}

// Teksta bloks - Lemonade
@Composable
fun YellowBlock(modifier: Modifier = Modifier) {
    Text(
        modifier = Modifier
            .background(color = Color.Yellow)
            .fillMaxWidth()
            .wrapContentSize(Alignment.Center)
            .padding(
                top = 40.dp,
                bottom = 10.dp),
        text = stringResource(R.string.header),
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp
    )
}

// Programma
@Composable
fun AppScreen(modifier: Modifier = Modifier) {

    /* Pārzīmē komponenti un saglabā datus ārpus rendera
    *
    * Šeit izxveidoti 3 mainīgie, kas saglabā datus ārpus
    * komponenšu rendera.
    *
    * screenNumber = programmas lapa, ko redz lietotājs.
    * userTap = lietotāja nospiedumu skaits
    * randomTap = nejauši uzģenerēts skaitlis, kas nosaka,
    * cik reižu lietotājam jānospiež uz citrona.
    **/
    var screenNumber by remember { mutableStateOf(0) }
    var userTap by remember { mutableStateOf(0) }
    var randomTap by remember { mutableStateOf(0) }

    // Mainīgais, kas satur attēlu,
    // atkarībā no ekrāna
    val appImage = when(screenNumber) {
        0 -> R.drawable.lemon_tree
        1 -> R.drawable.lemon_squeeze
        2 -> R.drawable.lemon_drink
        3 -> R.drawable.lemon_restart
        else -> R.drawable.lemon_tree
    }

    /* Mainīgais, kas satur attēla aprakstu,
    * atkarībā no ekrāna*/
    val imageText = when(screenNumber) {
        0 -> stringResource(R.string.imgOne)
        1 -> stringResource(R.string.imgTwo)
        2 -> stringResource(R.string.imgThree)
        3 -> stringResource(R.string.imgFour)
        else -> stringResource(R.string.app_name)
    }

    /*
    * Mainīgais, kas satur tekstu,
    * atkarībā no ekrāna*/
    val appText = when(screenNumber) {
        0 -> stringResource(R.string.msgOne)
        1 -> stringResource(R.string.msgTwo)
        2 -> stringResource(R.string.msgThree)
        3 -> stringResource(R.string.msgFour)
        else -> stringResource(R.string.app_name)
    }

    Column (
        modifier = Modifier
            .fillMaxSize()
    ) {
        // Galvene
        YellowBlock()
        // Bloks, kas satur attēlu un tekstu
        Column(
            modifier = Modifier
                .wrapContentSize(Alignment.Center)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        )
        {
            Button(onClick = {

                /* Logika, kas atbilst par ekrānu maiņu
                *
                * Pirmajā ekrānā uzģenerē randomTap skaitu,
                * cik reižu būs nepieciešams nospiest uz citronu,
                * lai pāriet uz nākamo ekrānu.
                *
                * Kad userTap sasniedz nepieciešamo skaitu,
                * lietotājs tiek pārvirzīts uz nākamo ekrānu.
                **/

                when (screenNumber) {
                    0 -> {
                        screenNumber = 1
                        randomTap = (2..4).random()
                        userTap = 0
                    }
                    1 -> {
                        userTap++

                        if (userTap == randomTap) {
                            screenNumber = 2
                        }
                    }
                    2 -> {
                        screenNumber = 3
                    }
                    else -> {
                        screenNumber = 0
                    }
                }},
                modifier = Modifier
                    .size(260.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color(0xFF90EE90)),

                /* Maina pogas stilu:
                * - krāsu
                **/

                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF90EE90)
                )
            )
            {
                Image(
                    painter = painterResource(appImage),
                    contentDescription = "${imageText}",
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "${appText}",
                fontSize = 18.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LemonadeApp() {
    AppScreen(modifier = Modifier.fillMaxSize())
}