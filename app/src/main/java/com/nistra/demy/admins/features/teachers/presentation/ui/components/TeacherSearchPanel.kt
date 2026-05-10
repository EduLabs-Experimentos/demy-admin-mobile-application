package com.nistra.demy.admins.features.teachers.presentation.ui.components

import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.nistra.demy.admins.R
import com.nistra.demy.admins.core.designsystem.components.text.IconLabelRow
import com.nistra.demy.admins.core.designsystem.components.cards.ListItemCard
import com.nistra.demy.admins.core.designsystem.components.cards.SearchableListCard
import com.nistra.demy.admins.features.teachers.domain.model.Teacher

/**
 * Search panel component for teachers.
 *
 * Provides a searchable list of teachers with filtering capabilities.
 * Uses the generic SearchableListCard component for consistent styling.
 *
 * @param searchQuery The current search query string.
 * @param onSearchQueryChange Callback invoked when the search query changes.
 * @param teachers The list of teachers to display.
 * @param modifier Optional [Modifier] for the panel.
 * @param isLoading Whether the list is currently loading.
 * @param onEditTeacher Callback invoked when a teacher edit is requested.
 * @param onDeleteTeacher Callback invoked when a teacher deletion is requested.
 * @author Salim Ramirez
 */
@Composable
fun TeacherSearchPanel(
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    teachers: List<Teacher>,
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
    onEditTeacher: (Teacher) -> Unit = {},
    onDeleteTeacher: (Teacher) -> Unit = {}
) {
    SearchableListCard(
        title = stringResource(R.string.teachers_search_title),
        description = stringResource(R.string.teachers_search_description),
        searchQuery = searchQuery,
        onSearchQueryChange = onSearchQueryChange,
        searchPlaceholder = stringResource(R.string.teachers_search_input_placeholder),
        searchLabel = stringResource(R.string.teachers_search_placeholder),
        isLoading = isLoading,
        emptyMessage = stringResource(R.string.teachers_not_found),
        itemCount = teachers.size,
        itemCountLabel = stringResource(R.string.teachers_found_count),
        modifier = modifier,
        searchLeadingIcon = {
            Icon(Icons.Default.Search, contentDescription = stringResource(R.string.teacher_search))
        }
    ) {
        items(teachers) { teacher ->
            TeacherListItem(
                teacher = teacher,
                onEdit = { onEditTeacher(teacher) },
                onDelete = { onDeleteTeacher(teacher) }
            )
        }
    }
}

/**
 * List item component for displaying teacher information.
 *
 * Shows teacher details including name, email, and phone in a card layout
 * with edit and delete action buttons.
 *
 * @param teacher The teacher data to display.
 * @param onEdit Callback invoked when the edit button is clicked.
 * @param onDelete Callback invoked when the delete button is clicked.
 * @param modifier Optional [Modifier] for the item.
 * @author Salim Ramirez
 */
@Composable
private fun TeacherListItem(
    teacher: Teacher,
    onEdit: () -> Unit,
    onDelete: () -> Unit,
    modifier: Modifier = Modifier
) {
    ListItemCard(
        onClick = { /* TODO: Implement click action */ },
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.tertiaryContainer.copy(alpha = 0.3f),
        mainContent = {
            IconLabelRow(
                icon = Icons.Default.Person,
                text = "${teacher.firstName} ${teacher.lastName}",
                iconSize = 20.dp,
                iconColor = MaterialTheme.colorScheme.tertiary,
                textStyle = MaterialTheme.typography.titleMedium,
                textColor = MaterialTheme.colorScheme.onSurface
            )

            IconLabelRow(
                icon = Icons.Default.Email,
                text = teacher.email,
                iconColor = MaterialTheme.colorScheme.tertiary.copy(alpha = 0.7f)
            )

            if (teacher.phone.isNotBlank()) {
                IconLabelRow(
                    icon = Icons.Default.Phone,
                    text = "${teacher.countryCode} ${teacher.phone}",
                    iconColor = MaterialTheme.colorScheme.tertiary.copy(alpha = 0.7f)
                )
            }
        },
        actions = {
            IconButton(onClick = onEdit) {
                Icon(
                    Icons.Default.Edit,
                    contentDescription = stringResource(R.string.teacher_edit),
                    tint = MaterialTheme.colorScheme.tertiary
                )
            }

            IconButton(onClick = onDelete) {
                Icon(
                    Icons.Default.Delete,
                    contentDescription = stringResource(R.string.teacher_delete),
                    tint = MaterialTheme.colorScheme.error
                )
            }
        }
    )
}
