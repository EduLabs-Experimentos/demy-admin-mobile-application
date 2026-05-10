package com.nistra.demy.admins.features.billing.presentation.ui.components

import androidx.compose.animation.core.copy
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.nistra.demy.admins.core.designsystem.components.cards.SearchableListCard
import com.nistra.demy.admins.features.billing.domain.model.BillingAccount
import com.nistra.demy.admins.R
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.semantics.error
import androidx.compose.ui.unit.dp
import com.nistra.demy.admins.core.designsystem.components.cards.ListItemCard
import com.nistra.demy.admins.core.designsystem.components.text.IconLabelRow

@Composable
fun BillingSearchPanel(
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    billingAccounts: List<BillingAccount>,
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
    onItemClick: (String) -> Unit,
    onEditBilling: (BillingAccount) -> Unit = {},
    onDeleteBilling: (BillingAccount) -> Unit = {}
) {
    SearchableListCard(
        title = stringResource(R.string.billing_search_title),
        description = stringResource(R.string.billing_search_description),
        searchQuery = searchQuery,
        onSearchQueryChange = onSearchQueryChange,
        searchPlaceholder = stringResource(R.string.billing_search_placeholder),
        searchLabel = stringResource(R.string.billing_search_placeholder),
        isLoading = isLoading,
        emptyMessage = stringResource(R.string.billing_not_found),
        itemCount = billingAccounts.size,
        itemCountLabel = stringResource(id = R.string.billing_found_count, billingAccounts.size),
        modifier = modifier,
        searchLeadingIcon = {
            Icon(Icons.Default.Search, contentDescription = stringResource(R.string.billing_search_title))
        }
    ) {
        items(billingAccounts) { account ->
            BillingListItem(
                account = account,
                onClick = { onItemClick(account.id) },
                onEdit = { onEditBilling(account) },
                onDelete = { onDeleteBilling(account) }
            )
        }
    }

}


@Composable
private fun BillingListItem(
    account: BillingAccount,
    onClick: () -> Unit,
    onEdit: () -> Unit,
    onDelete: () -> Unit,
    modifier: Modifier = Modifier
) {
    ListItemCard(
        onClick = onClick,
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.3f),
        mainContent = {
            IconLabelRow(
                icon = Icons.Default.Person,
                text = stringResource(id = R.string.billing_dni_number_label, account.dniNumber),
                iconSize = 20.dp,
                iconColor = MaterialTheme.colorScheme.secondary,
                textStyle = MaterialTheme.typography.titleMedium,
                textColor = MaterialTheme.colorScheme.onSurface
            )


            /*IconLabelRow(
                icon = Icons.Default.AccountBalanceWallet,
                text = stringResource(id = R.string.billing_academy_id_label, account.academyId),
                iconColor = MaterialTheme.colorScheme.secondary.copy(alpha = 0.7f)
            ) */
        },
        actions = {
            /*IconButton(onClick = onEdit) {
                Icon(
                    Icons.Default.Edit,
                    contentDescription = stringResource(R.string.billing_edit_action_description),
                    tint = MaterialTheme.colorScheme.secondary
                )
            } */

           /* IconButton(onClick = onDelete) {
                Icon(
                    Icons.Default.Delete,
                    contentDescription = stringResource(R.string.billing_delete_action_description),
                    tint = MaterialTheme.colorScheme.error
                )
            } */
        }
    )
}
