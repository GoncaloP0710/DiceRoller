package com.example.diceroller

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.diceroller.navigation.Screens

@Composable
fun TwoDies(navController: NavController, resultShow1: Int, resultShow2: Int , modifier: Modifier =
    Modifier.fillMaxSize().wrapContentSize(
        Alignment.Center)) {

    var oldValue by remember { mutableStateOf(resultShow1) }
    var newValue by remember { mutableStateOf(resultShow2) }

    val imageResource1 = when (oldValue) {
        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        else -> R.drawable.dice_6
    }

    val imageResource2 = when (newValue) {
        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        else -> R.drawable.dice_6
    }

    Column (modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Row {
            Image(
                painter = painterResource(imageResource1),
                contentDescription = resultShow1.toString()
            )
            Spacer(modifier = Modifier.width(16.dp)) // Add some space between the images
            Image(
                painter = painterResource(imageResource2),
                contentDescription = resultShow2.toString()
            )
        }
        Button(
            onClick = {
                navController.navigate("${Screens.Roll.route}?result=${newValue}")
            },
        ) {
            Text(text = stringResource(R.string.back), fontSize = 24.sp)
        }
    }
}