package com.nistra.demy.admins.features.dashboard.presentation.model

import com.nistra.demy.admins.features.dashboard.data.remote.dto.TransactionResourceDto
import java.text.SimpleDateFormat
import java.util.Locale

/**
 * Data model for dashboard chart visualization.
 *
 * @author Salim Ramirez
 */
data class DashboardChartData(
    val lineChartData: IncomeExpenseLineData,
    val pieChartData: ExpenseCategoriesPieData
)

data class IncomeExpenseLineData(
    val incomePoints: List<DateAmountPoint>,
    val expensePoints: List<DateAmountPoint>
)

data class DateAmountPoint(
    val date: String,
    val amount: Float,
    val timestamp: Long
)

data class ExpenseCategoriesPieData(
    val categories: List<CategoryExpense>
)

data class CategoryExpense(
    val category: String,
    val amount: Float,
    val percentage: Float
)

/**
 * Processes transaction data into chart-ready format.
 */
object DashboardChartProcessor {

    private val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

    fun processTransactions(transactions: List<TransactionResourceDto>): DashboardChartData {
        val incomeData = processTransactionsByType(transactions, "INCOME")
        val expenseData = processTransactionsByType(transactions, "EXPENSE")
        val expenseCategories = processExpenseByCategory(transactions)

        return DashboardChartData(
            lineChartData = IncomeExpenseLineData(
                incomePoints = incomeData,
                expensePoints = expenseData
            ),
            pieChartData = ExpenseCategoriesPieData(
                categories = expenseCategories
            )
        )
    }

    private fun processTransactionsByType(
        transactions: List<TransactionResourceDto>,
        type: String
    ): List<DateAmountPoint> {
        return transactions
            .filter { it.type.uppercase() == type }
            .groupBy { it.date }
            .map { (date, trans) ->
                val totalAmount = trans.sumOf {
                    (it.amount.toDoubleOrNull() ?: 0.0)
                }.toFloat()
                val timestamp = try {
                    dateFormat.parse(date)?.time ?: 0L
                } catch (e: Exception) {
                    0L
                }
                DateAmountPoint(date, totalAmount, timestamp)
            }
            .sortedBy { it.timestamp }
    }

    private fun processExpenseByCategory(
        transactions: List<TransactionResourceDto>
    ): List<CategoryExpense> {
        val expenseTransactions = transactions.filter {
            it.type.uppercase() == "EXPENSE"
        }

        val totalExpense = expenseTransactions.sumOf {
            (it.amount.toDoubleOrNull() ?: 0.0)
        }.toFloat()

        if (totalExpense == 0f) return emptyList()

        return expenseTransactions
            .groupBy { it.category }
            .map { (category, trans) ->
                val amount = trans.sumOf {
                    (it.amount.toDoubleOrNull() ?: 0.0)
                }.toFloat()
                val percentage = (amount / totalExpense) * 100
                CategoryExpense(category, amount, percentage)
            }
            .sortedByDescending { it.amount }
            .take(5) // Top 5 categories
    }
}

