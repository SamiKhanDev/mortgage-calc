package com.example.mortgagecalculator


import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResultScreen(navController: NavController, monthlyPayment: Double) {
    val maxPayment = 5000.0
    val targetProgress = (monthlyPayment / maxPayment).toFloat()
    var progress = (monthlyPayment / maxPayment).toFloat()
    var animatedPayment by remember { mutableStateOf(0.0) }
    LaunchedEffect(targetProgress) {
        val duration = 1000L
        val steps = 100
        val delayPerStep = duration / steps
        for (i in 0..steps) {
            progress = targetProgress * (i / steps.toFloat())
            animatedPayment = monthlyPayment * (i / steps.toFloat())
            delay(delayPerStep)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(title = {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Result",
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }, navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_back),
                            contentDescription = "Back",
                            modifier = Modifier.size(30.dp)
                        )
                    }
                },
            )},
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(16.dp)
                    .fillMaxSize(),
                contentAlignment = Alignment.Center

            ) {

                CustomCircularProgressIndicator(
                    currentValue = 75, // Example value
                    maxValue = 100, // Example value
                    progressBackgroundColor = Color.Gray,
                    progressIndicatorColor = Color.Blue,
                    completedColor = Color.Green,
                    modifier = Modifier
                        .padding(16.dp)
                        .size(200.dp)
                   )
                Text(
                    text = "$${String.format("%.2f", animatedPayment)}",
                    style = MaterialTheme.typography.headlineLarge,
                    modifier = Modifier.align(alignment = Alignment.Center)
                )
                Button(onClick = { navController.navigate("calculator_screen") }, modifier = Modifier.align(
                    Alignment.BottomCenter)) {
                    Text("Recalculate")
                }
            }


        }
    )
}

@Preview(showBackground = true)
@Composable
fun ResultScreenPreview() {
    ResultScreen(navController = rememberNavController(), monthlyPayment = 1234.56)
}