package com.example.mortgagecalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mortgagecalculator.ui.theme.MortgagecalculatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MortgagecalculatorTheme {
                val navController = rememberNavController()
                SetupNavGraph(navController = navController)                }
            }
        }
    }

@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "calculator_screen") {
        composable("calculator_screen") {
            MortgageCalculatorScreen(navController = navController)
        }
        composable("result_screen/{monthlyPayment}") { backStackEntry ->
            val monthlyPayment = backStackEntry.arguments?.getString("monthlyPayment")?.toDoubleOrNull() ?: 0.0
            ResultScreen(navController = navController, monthlyPayment = monthlyPayment)
        }
    }
}
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MortgagecalculatorTheme {
        Greeting("Android")
    }
}