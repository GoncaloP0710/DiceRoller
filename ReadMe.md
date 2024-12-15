# Dice Roller

Dice Roller is an Android application that simulates rolling a dice. The app is built using Kotlin and Jetpack Compose.

## Features

- Roll a dice and get a random result.
- View the result of the dice roll.
- Increment the dice result manually.
- Navigate between different screens.

## Installation

1. Clone the repository:
    ```sh
    git clone https://github.com/GoncaloP0710/DiceRoller.git
    ```
2. Open the project in Android Studio.
3. Build and run the project on an emulator or physical device.

## Project Structure

- `app/src/main/java/com/example/diceroller/`: Contains the main Kotlin source files.
  - [`MainActivity.kt`](app/src/main/java/com/example/diceroller/MainActivity.kt): The main activity of the app.
  - [`DiceResult.kt`](app/src/main/java/com/example/diceroller/DiceResult.kt): Composable function to display the dice result.
  - [`DiceResultInc.kt`](app/src/main/java/com/example/diceroller/DiceResultInc.kt): Composable function to display and increment the dice result.
  - [`Profile.kt`](app/src/main/java/com/example/diceroller/Profile.kt): Composable function to display the profile screen.
- `app/src/main/res/`: Contains the resource files.
  - `drawable/`: Contains the drawable resources for the dice images.
    - [`dice_1.xml`](app/src/main/res/drawable/dice_1.xml)
    - [`dice_2.xml`](app/src/main/res/drawable/dice_2.xml)
    - [`dice_3.xml`](app/src/main/res/drawable/dice_3.xml)
    - [`dice_5.xml`](app/src/main/res/drawable/dice_5.xml)
  - `values/`: Contains the values resources.
    - [`strings.xml`](app/src/main/res/values/strings.xml): Contains the string resources.
    - [`themes.xml`](app/src/main/res/values/themes.xml): Contains the theme resources.
  - `xml/`: Contains the XML resources.
    - [`backup_rules.xml`](app/src/main/res/xml/backup_rules.xml): Contains the backup rules.