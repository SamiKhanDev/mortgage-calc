package com.example.mortgagecalculator

import kotlin.math.pow

class MortgageCalculator {
    fun calculateMonthlyPayment(principal: Double, annualRate: Double, years: Double): Double {
        if (principal <= 0 || annualRate <= 0 || years <= 0) return 0.0
        val monthlyRate = annualRate / 100 / 12
        val numberOfPayments = years * 12
        return (principal * monthlyRate) / (1 - (1 + monthlyRate).pow(-numberOfPayments))
    }
}