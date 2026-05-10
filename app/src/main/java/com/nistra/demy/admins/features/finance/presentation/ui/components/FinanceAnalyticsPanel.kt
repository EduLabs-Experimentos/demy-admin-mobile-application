package com.nistra.demy.admins.features.finance.presentation.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.nistra.demy.admins.R
import com.nistra.demy.admins.core.designsystem.components.forms.FormCard
import com.nistra.demy.admins.features.finance.presentation.model.ChartData

/**
 * Panel displaying financial analytics charts.
 *
 * Shows a line chart for income/expense trends and a pie chart for expense categories.
 *
 * @param chartData The processed chart data to display.
 * @param isLoading Whether the data is currently loading.
 * @param modifier Optional [Modifier] for the panel.
 * @author Salim Ramirez
 */
@Composable
fun FinanceAnalyticsPanel(
    chartData: ChartData?,
    isLoading: Boolean,
    modifier: Modifier = Modifier
) {
    FormCard(
        title = stringResource(R.string.finance_analytics_title),
        description = stringResource(R.string.finance_analytics_description),
        headerBackgroundColor = MaterialTheme.colorScheme.tertiaryContainer,
        headerTextColor = MaterialTheme.colorScheme.onTertiaryContainer,
        modifier = modifier
    ) {
        if (isLoading) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(600.dp)
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(R.string.finance_loading_data),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        } else if (chartData != null) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                // Income vs Expense Line Chart
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(R.string.finance_income_expense_trend),
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    IncomeExpenseLineChart(
                        data = chartData.lineChartData,
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                // Divider between charts
                HorizontalDivider(
                    modifier = Modifier.padding(vertical = 8.dp),
                    thickness = 1.dp,
                    color = MaterialTheme.colorScheme.outlineVariant
                )

                // Expense Categories Pie Chart
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(R.string.finance_expense_categories),
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    ExpenseCategoriesPieChart(
                        data = chartData.pieChartData,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(600.dp)
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(R.string.finance_no_data),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

