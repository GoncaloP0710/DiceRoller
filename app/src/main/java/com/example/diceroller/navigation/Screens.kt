package com.example.diceroller.navigation

sealed class Screens(val route: String) {
    object Roll : Screens("roll_screen")
    object DiceResult : Screens("result_screen/{result}")
    object DiceResultInc : Screens("result_screen_inc/{result}")
    object DiceResultTextField : Screens("result_screen_text_field/{result}")
    object Profile : Screens("profile")
    object Menu : Screens("menu/{result}")
    object TwoDies : Screens("menu/{result1}/{result2}")
}
