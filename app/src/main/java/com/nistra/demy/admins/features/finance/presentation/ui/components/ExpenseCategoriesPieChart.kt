package com.nistra.demy.admins.features.finance.presentation.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.nistra.demy.admins.features.finance.presentation.model.PieChartData
import ir.ehsannarmani.compose_charts.PieChart
import ir.ehsannarmani.compose_charts.models.Pie

/**
 * Formats category name from backend format to readable format.
 * Converts "Student enrollment" or "STUDENT_ENROLLMENT" to "Student Enrollment"
 */
private fun formatCategoryName(name: String): String {
    return name
        .replace("_", " ")
        .split(" ")
        .joinToString(" ") { word ->
            word.lowercase().replaceFirstChar { it.uppercase() }
        }
}

/**
 * Expense categories chart component showing distribution as pie chart.
 *
 * @param data The pie chart data containing category amounts.
 * @param modifier Optional [Modifier] for the chart.
 * @author Salim Ramirez
 */
@Composable
fun ExpenseCategoriesPieChart(
    data: PieChartData,
    modifier: Modifier = Modifier
) {
    val categoryColors = listOf(
        Color(0xFFFFB74D), // Light Red (pastel)
        Color(0xFF9575CD), // Light Cyan (pastel)
        Color(0xFFDCE775), // Light Blue (pastel)
        Color(0xFF4FC3F7), // Light Green (pastel)
        Color(0xFFE57373), // Light Orange (pastel)
        Color(0xFF81C784), // Light Purple (pastel)
        Color(0xFFFFF176), // Light Yellow (pastel)
        Color(0xFF7986CB)  // Light Teal (pastel)
    )

    if (data.categories.isEmpty()) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .height(300.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "No expense data available",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        return
    }

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(24.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Pie Chart using ehsannarmani-charts
        PieChart(
            modifier = Modifier
                .size(220.dp)
                .padding(16.dp),
            data = data.categories.mapIndexed { index, category ->
                Pie(
                    label = formatCategoryName(category.category),
                    data = category.amount.toDouble(),
                    color = categoryColors.getOrElse(index) { categoryColors.last() },
                    selectedColor = categoryColors.getOrElse(index) { categoryColors.last() }
                )
            },
            onPieClick = { /* Handle click if needed */ },
            selectedScale = 1.2f,
            spaceDegree = 3f,
            style = Pie.Style.Fill
        )

        // Legend - now positioned on the right side
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            data.categories.forEachIndexed { index, category ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.weight(1f)
                    ) {
                        Surface(
                            modifier = Modifier.size(12.dp),
                            shape = CircleShape,
                            color = categoryColors.getOrElse(index) { categoryColors.last() }
                        ) {}

                        Spacer(modifier = Modifier.width(8.dp))

                        Text(
                            text = formatCategoryName(category.category),
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }

                    Text(
                        text = "${category.percentage.toInt()}%",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        }
    }
}

