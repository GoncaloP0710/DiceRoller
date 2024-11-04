package com.example.diceroller

import android.graphics.Color
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
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.offset
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.diceroller.navigation.NavGraph
import com.example.diceroller.navigation.Screens
import kotlin.math.roundToInt

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.widget.Toast
import android.util.Log
import androidx.compose.runtime.LaunchedEffect

var resu by mutableStateOf(0)

class MainActivity : ComponentActivity(), SensorEventListener {

    private var sensorManager: SensorManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager

        setContent {
            DiceRollerTheme {
                val navController = rememberNavController()
                NavGraph(navController = navController)
            }
        }
    }

    override fun onAccuracyChanged(s: Sensor?, i: Int) {
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event!!.sensor.type == Sensor.TYPE_ACCELEROMETER) {
            getAccelerometer(event)
        }
    }

    private fun getAccelerometer(event: SensorEvent) {
        // Movement
        val xVal = event.values[0]
        val yVal = event.values[1]
        val zVal = event.values[2]

        val accelerationSquareRoot = (xVal * xVal + yVal * yVal + zVal * zVal) / (SensorManager.GRAVITY_EARTH * SensorManager.GRAVITY_EARTH)

        if (accelerationSquareRoot >= 2) {
            Log.d("MainActivity", "-----------------------------------------------")
            Log.d("MainActivity", "result: $resu")
            Log.d("MainActivity", "Acceleration threshold met, resu set to 1")
            resu = 1
            Log.d("MainActivity", "result: $resu")
            Log.d("MainActivity", "-----------------------------------------------")
        }
    }

    override fun onResume() {
        super.onResume()
        sensorManager!!.registerListener(this, sensorManager!!.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onPause() {
        super.onPause()
        sensorManager!!.unregisterListener(this)
    }
}



@Preview
@Composable
fun DiceRollerApp() {
    DiceWithButtonAndImage(navController = rememberNavController(), result = 1, modifier = Modifier.fillMaxSize().wrapContentSize(Alignment.Center))
}

@Composable
fun DiceWithButtonAndImage(navController: NavController, result: Int, modifier: Modifier = Modifier.fillMaxSize().wrapContentSize(Alignment.Center)) {

    var res by remember { mutableStateOf(resu) }

    LaunchedEffect(resu) {
        res = resu
    }


    var oldValue by remember { mutableStateOf(result) }
    var newValue by remember { mutableStateOf(1) }

    var result by remember { mutableStateOf(result) }

    if (res == 1) {
        result = (1..6).random()
        Log.d("DiceWithButtonAndImage", "result: $result")
        newValue = result
        resu = 0
        val nigga = when (result) {
            1 ->navController.navigate(Screens.Profile.route)
            2 ->navController.navigate(Screens.DiceResult.route.replace(oldValue = "{result}", newValue = result.toString()))
            3 ->navController.navigate(Screens.DiceResultInc.route.replace(oldValue = "{result}",newValue = result.toString()))
            4 ->navController.navigate(Screens.Menu.route.replace(oldValue = "{result}", newValue = result.toString()))
            5 ->navController.navigate(Screens.DiceResultTextField.route.replace(oldValue = "{result}", newValue = result.toString()))
            else ->navController.navigate(Screens.TwoDies.route.replace(oldValue = "{result1}", newValue = oldValue.toString()).replace(oldValue = "{result2}", newValue = newValue.toString()))
        }
    }

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
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Box(modifier = Modifier.fillMaxSize()) {
            var offsetX by remember { mutableStateOf(350f) }
            var offsetY by remember { mutableStateOf(500f) }

            Image(
                painter = painterResource(imageResource),
                contentDescription = result.toString(),
                Modifier
                    .offset { IntOffset(offsetX.roundToInt(), offsetY.roundToInt()) }
                    .pointerInput(Unit) {
                        detectDragGestures { change, dragAmount ->
                            change.consume()
                            offsetX += dragAmount.x
                            offsetY += dragAmount.y
                        }
                    }
            )
        }
    }
}

