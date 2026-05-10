package com.nistra.demy.admins.features.dashboard.presentation.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.TrendingUp
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.nistra.demy.admins.R
import com.nistra.demy.admins.features.dashboard.presentation.model.IncomeExpenseLineData
import com.nistra.demy.admins.features.finance.presentation.ui.components.IncomeExpenseLineChart

/**
 * Card displaying income vs expense line chart.
 *
 * @param data The line chart data to display.
 * @param isLoading Whether the data is currently loading.
 * @param modifier Optional [Modifier] for the card.
 * @author Salim Ramirez
 */
@Composable
fun DashboardIncomeExpenseCard(
    data: IncomeExpenseLineData?,
    isLoading: Boolean,
    modifier: Modifier = Modifier
) {
    OutlinedCard(
        modifier = modifier,
        colors = CardDefaults.outlinedCardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        shape = MaterialTheme.shapes.medium
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.TrendingUp,
                    contentDescription = null,
                    modifier = Modifier.size(24.dp),
                    tint = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = stringResource(R.string.dashboard_income_expense_trend),
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            if (isLoading) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(330.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = stringResource(R.string.dashboard_loading),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            } else if (data != null && (data.incomePoints.isNotEmpty() || data.expensePoints.isNotEmpty())) {
                IncomeExpenseLineChart(
                    data = com.nistra.demy.admins.features.finance.presentation.model.LineChartData(
                        incomeData = data.incomePoints.map {
                            com.nistra.demy.admins.features.finance.presentation.model.DateAmountPair(
                                date = it.date,
                                amount = it.amount,
                                timestamp = it.timestamp
                            )
                        },
                        expenseData = data.expensePoints.map {
                            com.nistra.demy.admins.features.finance.presentation.model.DateAmountPair(
                                date = it.date,
                                amount = it.amount,
                                timestamp = it.timestamp
                            )
                        }
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(330.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = stringResource(R.string.dashboard_no_data),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}

