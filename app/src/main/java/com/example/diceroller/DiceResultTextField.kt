package com.example.diceroller

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.TextField
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization


@Composable
fun DiceResultTextField(navController: NavController, resultShow: Int = 1 , modifier: Modifier =
    Modifier.fillMaxSize().wrapContentSize(
        Alignment.Center)) {

    var result by remember { mutableStateOf(resultShow) }
    var inputValue by remember { mutableStateOf(result.toString()) }

    val imageResource = when (result) {
        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        else -> R.drawable.dice_6
    }
    Column (modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Image(painter = painterResource(imageResource), contentDescription = result.toString())

        Button(
            onClick = {
                navController.navigate("${Screens.Roll.route}?result=${result}")
            },
        ) {
            Text(text = stringResource(R.string.back), fontSize = 24.sp)
        }

        // TextField for number input
        TextField(
            value = inputValue,
            onValueChange = { newValue ->
                // Allow only digits and make sure value is between 1 and 6
                if (newValue.isNotEmpty() && newValue.toIntOrNull() != null && newValue.toInt() in 1..6) {
                    inputValue = newValue
                    result = newValue.toInt()
                } else if (newValue.isEmpty()) {
                    inputValue = newValue
                }
            },
            label = { Text("Enter a number (1-6)") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number, // This ensures number keyboard
                imeAction = ImeAction.Done
            ),
            modifier = Modifier.padding(16.dp)
        )

    }
}