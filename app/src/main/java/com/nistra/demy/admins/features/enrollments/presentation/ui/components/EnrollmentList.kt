package com.nistra.demy.admins.features.enrollments.presentation.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.nistra.demy.admins.R
import com.nistra.demy.admins.features.enrollments.domain.model.Enrollment

@Composable
fun EnrollmentList(
    modifier: Modifier = Modifier,
    enrollments: List<Enrollment>,
    onEnrollmentSelected: (Enrollment) -> Unit,
    onDeleteEnrollment: (Enrollment) -> Unit,
    onSearchQueryChange: (String) -> Unit,
    searchQuery: String
) {
    Card (
        modifier = modifier.fillMaxHeight(),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column (modifier = Modifier.padding(16.dp)) {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = onSearchQueryChange,
                label = { Text(stringResource(R.string.enrollments_search_label)) },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = stringResource(R.string.enrollments_search_cd)) },
                modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
            )

            LazyColumn (
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(enrollments, key = { it.id }) { enrollment ->
                    EnrollmentListItem(
                        enrollment = enrollment,
                        onEditClick = { onEnrollmentSelected(enrollment) },
                        onDeleteClick = { onDeleteEnrollment(enrollment) }
                    )
                }
            }
        }
    }
}
