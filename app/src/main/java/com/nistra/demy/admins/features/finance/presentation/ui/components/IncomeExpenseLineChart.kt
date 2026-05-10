package com.nistra.demy.admins.features.finance.presentation.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.nistra.demy.admins.R
import com.patrykandpatrick.vico.compose.axis.horizontal.rememberBottomAxis
import com.patrykandpatrick.vico.compose.axis.vertical.rememberStartAxis
import com.patrykandpatrick.vico.compose.chart.Chart
import com.patrykandpatrick.vico.compose.chart.line.lineChart
import com.patrykandpatrick.vico.compose.style.ProvideChartStyle
import com.patrykandpatrick.vico.core.axis.AxisPosition
import com.patrykandpatrick.vico.core.axis.formatter.AxisValueFormatter
import com.patrykandpatrick.vico.core.chart.line.LineChart
import com.patrykandpatrick.vico.core.entry.ChartEntryModelProducer
import com.patrykandpatrick.vico.core.entry.FloatEntry
import com.nistra.demy.admins.features.finance.presentation.model.LineChartData

/**
 * Line chart component showing income and expense trends over time.
 *
 * @param data The line chart data containing income and expense points.
 * @param modifier Optional [Modifier] for the chart.
 * @author Salim Ramirez
 */
@Composable
fun IncomeExpenseLineChart(
    data: LineChartData,
    modifier: Modifier = Modifier
) {
    val incomeColor = Color(0xFF4CAF50) // Green
    val expenseColor = Color(0xFFF44336) // Red

    // Combine and sort all unique dates from both datasets
    val allDates = remember(data) {
        val dates = mutableSetOf<String>()
        data.incomeData.forEach { dates.add(it.date) }
        data.expenseData.forEach { dates.add(it.date) }

        // Sort dates chronologically (oldest to newest)
        dates.sortedBy { dateStr ->
            try {
                val parts = dateStr.split("-")
                if (parts.size == 3) {
                    // Convert yyyy-MM-dd to comparable long
                    val year = parts[0].toInt()
                    val month = parts[1].toInt()
                    val day = parts[2].toInt()
                    year * 10000L + month * 100L + day
                } else {
                    0L
                }
            } catch (_: Exception) {
                0L
            }
        }
    }

    // Create maps for quick lookup
    val incomeMap = remember(data) {
        data.incomeData.associate { it.date to it.amount }
    }
    val expenseMap = remember(data) {
        data.expenseData.associate { it.date to it.amount }
    }

    // Format dates for display
    val dateLabels = remember(allDates) {
        allDates.map { dateStr ->
            val parts = dateStr.split("-")
            if (parts.size == 3) "${parts[1]}/${parts[2]}" else dateStr
        }
    }

    // Create chart entry model producer with aligned data
    val chartEntryModelProducer = remember(data) {
        ChartEntryModelProducer(
            if (allDates.isEmpty()) {
                emptyList()
            } else {
                listOf(
                    // Income series - aligned with all dates
                    allDates.mapIndexed { index, date ->
                        FloatEntry(index.toFloat(), incomeMap[date] ?: 0f)
                    },
                    // Expense series - aligned with all dates
                    allDates.mapIndexed { index, date ->
                        FloatEntry(index.toFloat(), expenseMap[date] ?: 0f)
                    }
                )
            }
        )
    }

    // Formatter for X axis (dates)
    val bottomAxisValueFormatter = AxisValueFormatter<AxisPosition.Horizontal.Bottom> { value, _ ->
        val index = value.toInt()
        if (index >= 0 && index < dateLabels.size) {
            dateLabels[index]
        } else {
            ""
        }
    }

    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        ProvideChartStyle {
            Chart(
                chart = lineChart(
                    lines = listOf(
                        LineChart.LineSpec(
                            lineColor = incomeColor.hashCode(),
                            lineBackgroundShader = null
                        ),
                        LineChart.LineSpec(
                            lineColor = expenseColor.hashCode(),
                            lineBackgroundShader = null
                        )
                    )
                ),
                chartModelProducer = chartEntryModelProducer,
                startAxis = rememberStartAxis(),
                bottomAxis = rememberBottomAxis(
                    valueFormatter = bottomAxisValueFormatter
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(196.dp)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Legend
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    modifier = Modifier.size(12.dp),
                    shape = CircleShape,
                    color = incomeColor
                ) {}

                Spacer(modifier = Modifier.width(4.dp))

                Text(
                    text = stringResource(R.string.finance_legend_income),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    modifier = Modifier.size(12.dp),
                    shape = CircleShape,
                    color = expenseColor
                ) {}

                Spacer(modifier = Modifier.width(4.dp))

                Text(
                    text = stringResource(R.string.finance_legend_expense),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}

