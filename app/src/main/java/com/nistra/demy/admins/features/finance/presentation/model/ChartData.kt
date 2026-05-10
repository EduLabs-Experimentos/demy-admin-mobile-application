package com.nistra.demy.admins.features.finance.presentation.model

import com.nistra.demy.admins.features.finance.domain.model.Transaction
import java.text.SimpleDateFormat
import java.util.Locale

/**
 * Data model for chart visualization.
 */
data class ChartData(
    val lineChartData: LineChartData,
    val pieChartData: PieChartData
)

data class LineChartData(
    val incomeData: List<DateAmountPair>,
    val expenseData: List<DateAmountPair>
)

data class DateAmountPair(
    val date: String,
    val amount: Float,
    val timestamp: Long
)

data class PieChartData(
    val categories: List<CategoryAmount>
)

data class CategoryAmount(
    val category: String,
    val amount: Float,
    val percentage: Float
)

/**
 * Processes transactions into chart-ready data.
 */
object ChartDataProcessor {

    private val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

    fun processTransactions(transactions: List<Transaction>): ChartData {
        val income = processIncomeExpenseData(transactions, "Income")
        val expense = processIncomeExpenseData(transactions, "Expense")
        val expenseCategories = processExpenseCategories(transactions)

        return ChartData(
            lineChartData = LineChartData(
                incomeData = income,
                expenseData = expense
            ),
            pieChartData = PieChartData(
                categories = expenseCategories
            )
        )
    }

    private fun processIncomeExpenseData(
        transactions: List<Transaction>,
        type: String
    ): List<DateAmountPair> {
        return transactions
            .filter { it.type.equals(type, ignoreCase = true) }
            .groupBy { it.date }
            .map { (date, trans) ->
                val totalAmount = trans.sumOf { (it.amount.toFloatOrNull() ?: 0f).toDouble() }.toFloat()
                val timestamp = dateFormat.parse(date)?.time ?: 0L
                DateAmountPair(date, totalAmount, timestamp)
            }
            .sortedBy { it.timestamp }
    }

    private fun processExpenseCategories(transactions: List<Transaction>): List<CategoryAmount> {
        val expenseTransactions = transactions.filter {
            it.type.equals("Expense", ignoreCase = true)
        }

        val totalExpense = expenseTransactions.sumOf {
            (it.amount.toFloatOrNull() ?: 0f).toDouble()
        }.toFloat()

        if (totalExpense == 0f) return emptyList()

        return expenseTransactions
            .groupBy { it.category }
            .map { (category, trans) ->
                val amount = trans.sumOf { (it.amount.toFloatOrNull() ?: 0f).toDouble() }.toFloat()
                val percentage = (amount / totalExpense) * 100
                CategoryAmount(category, amount, percentage)
            }
            .sortedByDescending { it.amount }
            .take(5) // Top 5 categories
    }
}

