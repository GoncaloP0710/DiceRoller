package com.example.diceroller

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.diceroller.ui.theme.DiceRollerTheme

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.ui.res.stringResource
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.Spacer
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.diceroller.navigation.NavGraph
import com.example.diceroller.navigation.Screens

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DiceRollerTheme {
                val navController = rememberNavController()
                NavGraph(navController = navController)
            }
        }
    }
}

@Preview
@Composable
fun DiceRollerApp() {
    DiceWithButtonAndImage(navController = rememberNavController(), result = 1, modifier = Modifier.fillMaxSize().wrapContentSize(Alignment.Center))
}

@Composable
fun DiceWithButtonAndImage(navController: NavController, result: Int, modifier: Modifier = Modifier) {

    var oldValue by remember { mutableStateOf(result) }
    var newValue by remember { mutableStateOf(1) }

    var result by remember { mutableStateOf(result) }
    val imageResource = when (result) {
        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        else -> R.drawable.dice_6
    }
    Column (
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(imageResource),
            contentDescription = result.toString()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            result = (1..6).random()
            newValue = result
            val nigga = when (result) {
                1 ->navController.navigate(Screens.Profile.route)
                2 ->navController.navigate(Screens.DiceResult.route.replace(oldValue = "{result}", newValue = result.toString()))
                3 ->navController.navigate(Screens.DiceResultInc.route.replace(oldValue = "{result}",newValue = result.toString()))
                4 ->navController.navigate(Screens.Menu.route.replace(oldValue = "{result}", newValue = result.toString()))
                5 ->navController.navigate(Screens.DiceResultTextField.route.replace(oldValue = "{result}", newValue = result.toString()))
                else ->navController.navigate(Screens.TwoDies.route.replace(oldValue = "{result1}", newValue = oldValue.toString()).replace(oldValue = "{result2}", newValue = newValue.toString()))
            }
        }) {
            Text(text = stringResource(R.string.roll), fontSize = 24.sp)
        }

        /*

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

        Button( // Menu
            onClick = {
                navController.navigate(
                    Screens.Menu.route//Just modify your route accordingly
                        .replace(
                            oldValue = "{result}",
                            newValue = result.toString()
                        )
                )
            },
        ) {
            Text(text = stringResource(R.string.menu), fontSize = 24.sp)
        }

        Button( // TwoDies
            onClick = {
                navController.navigate(
                    Screens.TwoDies.route
                        .replace(
                            oldValue = "{result1}",
                            newValue = oldValue.toString()
                        )
                        .replace(
                            oldValue = "{result2}",
                            newValue = newValue.toString()
                        )
                )
            },
        ) {
            Text(text = stringResource(R.string.two_dies), fontSize = 24.sp)
        }


         */

    }
}

