package com.example.greetingcard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.greetingcard.ui.theme.GreetingCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GreetingCardTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Gonçalo Pinto",
                        phoneNumber = "696 833 442",
                        email = "goncalop@example.com",
                        modifier = Modifier
                            .padding(innerPadding)
                            .background(Color.Black) // Set the background to black
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, phoneNumber: String, email: String, modifier: Modifier = Modifier) {
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
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GreetingCardTheme {
        Greeting(
            name = "Android",
            phoneNumber = "123-456-7890",
            email = "android@example.com",
            modifier = Modifier
                .background(Color.Black) // Set background to black in preview as well
        )
    }
}
