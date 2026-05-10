package com.nistra.demy.admins.features.dashboard.presentation.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.nistra.demy.admins.core.designsystem.components.cards.InfoCard

/**
 * Data class representing an expense category.
 *
 * @param name Name of the expense category.
 * @param icon Icon or emoji representing the category.
 * @param amount Amount spent in this category (optional for future use).
 * @author Salim Ramirez
 */
data class ExpenseCategory(
    val name: String,
    val icon: String,
    val amount: Int? = null
)

/**
 * Card displaying top expense categories.
 *
 * Shows a list of expense categories with their icons.
 * Ready for integration with real data from the backend.
 *
 * @param categories List of expense categories to display.
 * @param modifier Modifier to be applied to the card.
 * @author Salim Ramirez
 */
@Composable
fun TopExpenseCategoriesCard(
    modifier: Modifier = Modifier,
    categories: List<ExpenseCategory> = getMockExpenseCategories()
) {
    InfoCard(
        title = "Top Expense Categories",
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.tertiaryContainer.copy(alpha = 0.4f)
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            categories.forEach { category ->
                ExpenseCategoryItem(
                    icon = category.icon,
                    name = category.name
                )
            }
        }
    }
}

/**
 * Individual expense category item.
 */
@Composable
private fun ExpenseCategoryItem(
    icon: String,
    name: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = icon,
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            text = name,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

/**
 * Mock data for expense categories.
 * TODO: Replace with real data from repository/API.
 */
private fun getMockExpenseCategories() = listOf(
    ExpenseCategory("Office Supplies", "🖇️"),
    ExpenseCategory("Utilities", "💡"),
    ExpenseCategory("Travel", "✈️"),
    ExpenseCategory("Catering", "🍽️"),
    ExpenseCategory("Maintenance", "🧰")
)
