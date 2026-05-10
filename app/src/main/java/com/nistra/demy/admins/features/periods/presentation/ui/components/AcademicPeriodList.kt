package com.nistra.demy.admins.features.periods.presentation.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.nistra.demy.admins.R
import com.nistra.demy.admins.features.periods.domain.model.AcademicPeriod

@Composable
fun AcademicPeriodList(
    modifier: Modifier = Modifier,
    periods: List<AcademicPeriod>,
    onPeriodSelected: (AcademicPeriod) -> Unit,
    onDeletePeriod: (AcademicPeriod) -> Unit,
    onSearchQueryChange: (String) -> Unit,
    searchQuery: String
) {
    Card(
        modifier = modifier.fillMaxHeight(),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            OutlinedTextField(
                value = searchQuery,
                onValueChange = onSearchQueryChange,
                label = { Text(stringResource(R.string.periods_search_placeholder)) },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = stringResource(R.string.period_search))},
                modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
            )

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(periods, key = { it.id }) { academicPeriod ->
                    AcademicPeriodListItem(
                        academicPeriod = academicPeriod,
                        onEditClick = { onPeriodSelected(academicPeriod) },
                        onDeleteClick = { onDeletePeriod(academicPeriod) }
                    )
                }
            }
        }
    }
}
