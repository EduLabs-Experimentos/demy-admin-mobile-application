package com.nistra.demy.admins.features.courses.presentation.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nistra.demy.admins.features.courses.domain.models.Course
import androidx.compose.ui.res.stringResource
import com.nistra.demy.admins.R

@Composable
fun CourseList(
    modifier: Modifier = Modifier,
    courses: List<Course>,
    onCourseSelected: (Course) -> Unit,
    onDeleteCourse: (Course) -> Unit,
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
                label = { Text(stringResource(R.string.courses_search_label)) },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = stringResource(R.string.courses_search_cd)) },
                modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
            )

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(courses, key = { it.id }) { course ->
                    CourseListItem(
                        course = course,
                        onEditClick = { onCourseSelected(course) },
                        onDeleteClick = { onDeleteCourse(course) }
                    )
                }
            }
        }
    }
}
