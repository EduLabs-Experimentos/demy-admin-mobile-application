package com.nistra.demy.admins.features.dashboard.presentation.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nistra.demy.admins.core.designsystem.components.cards.TwoColumnInfoCard
import com.nistra.demy.admins.core.designsystem.theme.extendedColors

/**
 * Row displaying financial comparison and expense categories.
 *
 * Cards have consistent height using IntrinsicSize.
 *
 * @param incomes Total incomes value.
 * @param expenses Total expenses value.
 * @param formatMoney Function to format monetary values.
 * @param modifier Modifier to be applied to the row.
 * @author Salim Ramirez
 */
@Composable
fun DashboardFinancialRow(
    incomes: Int,
    expenses: Int,
    formatMoney: (Int) -> String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        TwoColumnInfoCard(
            title = "Incomes vs Expenses",
            leftLabel = "Incomes",
            leftValue = formatMoney(incomes),
            rightLabel = "Expenses",
            rightValue = formatMoney(expenses),
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight(),
            containerColor = MaterialTheme.extendedColors.info.colorContainer.copy(alpha = 0.3f)
        )
        TopExpenseCategoriesCard(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
        )
    }
}

