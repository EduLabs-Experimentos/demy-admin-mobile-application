package com.nistra.demy.admins.features.dashboard.presentation.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.TrendingDown
import androidx.compose.material.icons.automirrored.filled.TrendingUp
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.School
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.nistra.demy.admins.R
import com.nistra.demy.admins.core.designsystem.components.cards.SmallCard
import com.nistra.demy.admins.core.designsystem.theme.extendedColors
import com.nistra.demy.admins.features.dashboard.domain.model.DashboardStats

/**
 * Top row displaying key dashboard statistics.
 *
 * Shows academy name, total income, total expense, and balance
 * in a horizontal row of small cards with Material You design.
 *
 * @param stats Dashboard statistics to display.
 * @param formatMoney Function to format monetary values.
 * @param modifier Modifier to be applied to the row.
 * @author Salim Ramirez
 */
@Composable
fun DashboardStatsRow(
    stats: DashboardStats,
    formatMoney: (Double) -> String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Academy Name Card
        SmallCard(
            label = stringResource(R.string.dashboard_academy_label),
            value = stats.academyName.ifEmpty { stringResource(R.string.dashboard_loading) },
            icon = Icons.Default.School,
            modifier = Modifier.weight(1f),
            containerColor = MaterialTheme.colorScheme.onPrimaryContainer,
            contentColor = MaterialTheme.colorScheme.primaryContainer
        )

        // Total Income Card
        SmallCard(
            label = stringResource(R.string.dashboard_total_income_label),
            value = formatMoney(stats.totalIncome),
            icon = Icons.AutoMirrored.Filled.TrendingUp,
            modifier = Modifier.weight(1f),
            containerColor = MaterialTheme.extendedColors.success.onColorContainer,
            contentColor = MaterialTheme.extendedColors.success.colorContainer
        )

        // Total Expense Card
        SmallCard(
            label = stringResource(R.string.dashboard_total_expenses_label),
            value = formatMoney(stats.totalExpense),
            icon = Icons.AutoMirrored.Filled.TrendingDown,
            modifier = Modifier.weight(1f),
            containerColor = MaterialTheme.colorScheme.onErrorContainer,
            contentColor = MaterialTheme.colorScheme.errorContainer
        )

        // Balance Card
        SmallCard(
            label = stringResource(R.string.dashboard_total_balance_label),
            value = formatMoney(stats.balance),
            icon = Icons.Default.AccountBalance,
            modifier = Modifier.weight(1f),
            containerColor = MaterialTheme.colorScheme.onTertiaryContainer,
            contentColor = MaterialTheme.colorScheme.tertiaryContainer
        )
    }
}

