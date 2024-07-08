package com.example.mortgagecalculator
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun MortgageCalculatorScreen(navController: NavController) {
    val calculator = MortgageCalculator()

    var principal by remember { mutableStateOf(100000f) }
    var interestRate by remember { mutableStateOf(3.5f) }
    var term by remember { mutableStateOf(30f) }
    Scaffold (
        topBar = {
            TopAppBar(title = { Text("Mortgage Calculator") })
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(16.dp)
                    .fillMaxSize()
            ) {
                Text(text = "Principal: $${principal.toInt()}")
                Slider(
                    value = principal,
                    onValueChange = { principal = it },
                    valueRange = 50000f..1000000f,
                    steps = 19,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))


                Text(text = "Interest Rate: ${String.format("%.2f", interestRate)}%")
                Slider(
                    value = interestRate,
                    onValueChange = { interestRate = (it * 2).roundToInt() / 2f },
                    valueRange = 0.5f..10f,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))


                Text(text = "Term: ${term.toInt()} years")
                Slider(
                    value = term,
                    onValueChange = { term = it },
                    valueRange = 5f..30f,
                    steps = 24,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))


                Button(
                    onClick = {
                        val monthlyPayment = calculator.calculateMonthlyPayment(
                            principal.toDouble(),
                            interestRate.toDouble(),
                            term.toDouble()
                        )
                        navController.navigate("result_screen/${monthlyPayment}")
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Calculate")
                }
            }
        })
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MortgageCalculatorScreen(navController = rememberNavController())
}


