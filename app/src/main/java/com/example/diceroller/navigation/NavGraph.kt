package com.example.diceroller.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.diceroller.DiceResult
import com.example.diceroller.DiceWithButtonAndImage
import com.example.diceroller.DiceResultInc
import com.example.diceroller.DiceResultTextField
import com.example.diceroller.Profile
import com.example.diceroller.Menu
import com.example.diceroller.TwoDies

@Composable
fun NavGraph (navController: NavHostController){
    NavHost(
        navController = navController,
        startDestination = Screens.Roll.route)
    {
        composable(
            route = Screens.Roll.route + "?result={result}"
        ) { navBackStack ->
            val result: Int = navBackStack.arguments?.getString("result")?.toIntOrNull() ?: 1
            DiceWithButtonAndImage(navController = navController, result = result)
        }

        // Show
        composable(route = Screens.DiceResult.route+ "?result={result}"){ navBackStack ->
            //extracting the argument
            var resultShow: Int = navBackStack.arguments?.getString("result")?.toIntOrNull()?:1
            DiceResult(navController = navController, resultShow = resultShow)
        }

        // inc
        composable(route = Screens.DiceResultInc.route+ "?result={result}"){ navBackStack ->
            //extracting the argument
            var resultShow: Int = navBackStack.arguments?.getString("result")?.toIntOrNull()?:1
            DiceResultInc(navController = navController, resultShow = resultShow)
        }

        // Txt Field
        composable(route = Screens.DiceResultTextField.route+ "?result={result}"){ navBackStack ->
            //extracting the argument
            var resultShow: Int = navBackStack.arguments?.getString("result")?.toIntOrNull()?:1
            DiceResultTextField(navController = navController, resultShow = resultShow)
        }

        // Profile
        composable(route = Screens.Profile.route){ navBackStack ->
            Profile(navController = navController, "Gonçalo Pinto", 969833445.toString(), "email@gmail.com")
        }

        // Menu
        composable(route = Screens.Menu.route+ "?result={result}"){ navBackStack ->
            //extracting the argument
            var resultShow: Int = navBackStack.arguments?.getString("result")?.toIntOrNull()?:1
            Menu(navController = navController, resultShow = resultShow)
        }

        // Two Dies
        composable(
            route = Screens.TwoDies.route + "?result1={result1}&result2={result2}"
        ) { navBackStack ->
            val resultShow1: Int = navBackStack.arguments?.getString("result1")?.toIntOrNull() ?: 1
            val resultShow2: Int = navBackStack.arguments?.getString("result2")?.toIntOrNull() ?: 1
            TwoDies(navController = navController, resultShow1 = resultShow1, resultShow2 = resultShow2)
        }
    }
}
