package com.nistra.demy.admins.features.schedules.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.nistra.demy.admins.features.schedules.domain.models.ClassSession


@Composable
private fun ClassInfoRow(
    icon: ImageVector,
    label: String,
    textColor: Color,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = textColor.copy(alpha = 0.7f),
            modifier = Modifier.size(12.dp)
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            color = textColor.copy(alpha = 0.7f),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
private fun ClassCardContent(
    session: ClassSession,
    endHourFormatted: String,
    textColor: Color
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp, vertical = 4.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(
                text = session.course.name,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Medium,
                color = textColor,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = "${session.timeRange.startTime} - $endHourFormatted",
                style = MaterialTheme.typography.labelSmall,
                color = textColor.copy(alpha = 0.7f)
            )
        }

        Column(horizontalAlignment = Alignment.Start) {
            ClassInfoRow(
                icon = Icons.Default.LocationOn,
                label = "${session.classroom.code} - ${session.classroom.campus}",
                textColor = textColor
            )
            ClassInfoRow(
                icon = Icons.Default.Person,
                label = "${session.teacher.firstName} ${session.teacher.lastName}",
                textColor = textColor
            )
        }
    }
}

@Composable
fun ClassCard(session: ClassSession, modifier: Modifier = Modifier, eventColor: Color) {
    val density = LocalDensity.current

    val eventStartTotalMinutes = timeStringToMinutes(session.timeRange.startTime)
    val calendarStartTotalMinutes = START_HOUR * 60
    val offsetMinutes = eventStartTotalMinutes - calendarStartTotalMinutes

    val dpPerMinute = with(density) { HOUR_HEIGHT.toPx() / 60 }

    val offsetDp = with(density) { (offsetMinutes * dpPerMinute).toDp() }
    val heightDp = with(density) { (session.durationMinutes * dpPerMinute).toDp() }

    val endHourMinutes = eventStartTotalMinutes + session.durationMinutes
    val endHourFormatted = "${(endHourMinutes / 60).toInt().toString().padStart(2, '0')}:${(endHourMinutes % 60).toString().padStart(2, '0')}"

    val textColor = Color.Black.copy(alpha = 0.95f)

    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = eventColor.copy(alpha = 0.95f)
        ),
        shape = RoundedCornerShape(8.dp),
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp, vertical = 2.dp)
            .offset(y = offsetDp)
            .height(heightDp)
            .clip(RoundedCornerShape(8.dp))
    ) {
        Row(modifier = Modifier.fillMaxSize()) {
            Spacer(
                modifier = Modifier
                    .width(4.dp)
                    .fillMaxHeight()
                    .background(eventColor.copy(alpha = 1.0f))
            )

            ClassCardContent(
                session = session,
                endHourFormatted = endHourFormatted,
                textColor = textColor
            )
        }
    }
}
