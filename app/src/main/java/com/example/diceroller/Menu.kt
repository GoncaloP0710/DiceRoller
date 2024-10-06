package com.example.diceroller

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.diceroller.navigation.Screens

@Composable
fun Menu(navController: NavController, resultShow: Int = 1, modifier: Modifier =
    Modifier.fillMaxSize().wrapContentSize(
        Alignment.Center)) {

    var result by remember { mutableStateOf(resultShow) }


    Column (modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Button(
            onClick = {
                navController.navigate("${Screens.Roll.route}?result=${result}")
            },
        ) {
            Text(text = stringResource(R.string.back), fontSize = 24.sp)
        }

        Button( // Result_Screen
            onClick = {
                navController.navigate(
                    Screens.DiceResult.route//Just modify your route accordingly
                        .replace(
                            oldValue = "{result}",
                            newValue = result.toString()
                        )
                )
            },
        ) {
            Text(text = stringResource(R.string.result_screen), fontSize = 24.sp)
        }

        Button( // Result_Screen_Inc
            onClick = {
                navController.navigate(
                    Screens.DiceResultInc.route//Just modify your route accordingly
                        .replace(
                            oldValue = "{result}",
                            newValue = result.toString()
                        )
                )
            },
        ) {
            Text(text = stringResource(R.string.result_screen_inc), fontSize = 24.sp)
        }

        Button( // Result_Screen_Txt_Field
            onClick = {
                navController.navigate(
                    Screens.DiceResultTextField.route//Just modify your route accordingly
                        .replace(
                            oldValue = "{result}",
                            newValue = result.toString()
                        )
                )
            },
        ) {
            Text(text = stringResource(R.string.result_screen_text_field), fontSize = 24.sp)
        }

        Button( // Profile
            onClick = {
                navController.navigate(Screens.Profile.route)//Just modify your route accordingly
            },
        ) {
            Text(text = stringResource(R.string.profile), fontSize = 24.sp)
        }

        Button( // TwoDies
            onClick = {
                navController.navigate(
                    Screens.TwoDies.route
                        .replace(
                            oldValue = "{result1}",
                            newValue = result.toString()
                        )
                        .replace(
                            oldValue = "{result2}",
                            newValue = 6.toString()
                        )
                )
            },
        ) {
            Text(text = stringResource(R.string.two_dies), fontSize = 24.sp)
        }

    }
}