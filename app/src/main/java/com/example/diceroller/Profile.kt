package com.example.diceroller

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
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
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.diceroller.navigation.Screens

@Composable
fun Profile(navController: NavController, name: String, phoneNumber: String, email: String, modifier: Modifier = Modifier) {
    // Rotation state for the image
    var rotationState by remember { mutableStateOf(0f) }

    // Animate the rotation angle
    val animatedRotation by animateFloatAsState(
        targetValue = rotationState,
        animationSpec = androidx.compose.animation.core.tween(durationMillis = 1000) // Duration for 360 degree rotation
    )

    // State to track the position of the text
    var isAbove by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Black) // Set the background to black
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Moving text above or below the image
            if (isAbove) {
                // Place text above the image
                Text(
                    text = "$name!",
                    fontSize = 30.sp,
                    color = Color.White, // Text color changed to white
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .clickable { isAbove = !isAbove } // Toggle position on click
                )
                Spacer(modifier = Modifier.height(8.dp)) // Adjusted spacing
                Text(
                    text = "Phone: $phoneNumber",
                    fontSize = 20.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .clickable { isAbove = !isAbove } // Toggle position on click
                )
                Spacer(modifier = Modifier.height(4.dp)) // Adjusted spacing
                Text(
                    text = "Email: $email",
                    fontSize = 20.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .clickable { isAbove = !isAbove } // Toggle position on click
                )
            }

            // Rotating profile picture
            Image(
                painter = painterResource(id = R.drawable.topazicon),
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .size(150.dp)
                    .clip(CircleShape)
                    .rotate(animatedRotation) // Apply rotation to the image
                    .clickable {
                        // Rotate the image by 360 degrees when clicked
                        rotationState += 360f
                    }
                    .align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(40.dp)) // Space between image and text

            if (!isAbove) {
                // Place text below the image
                Text(
                    text = "$name!",
                    fontSize = 30.sp,
                    color = Color.White, // Text color changed to white
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .clickable { isAbove = !isAbove } // Toggle position on click
                )
                Spacer(modifier = Modifier.height(8.dp)) // Adjusted spacing
                Text(
                    text = "Phone: $phoneNumber",
                    fontSize = 20.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .clickable { isAbove = !isAbove } // Toggle position on click
                )
                Spacer(modifier = Modifier.height(1.dp)) // Adjusted spacing
                Text(
                    text = "Email: $email",
                    fontSize = 20.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .clickable { isAbove = !isAbove } // Toggle position on click
                )
            }

            Button(
                onClick = {
                    navController.navigate("${Screens.Roll.route}?result=${1}")
                },
            ) {
                Text(text = stringResource(R.string.back), fontSize = 24.sp)
            }

        }
    }
}
