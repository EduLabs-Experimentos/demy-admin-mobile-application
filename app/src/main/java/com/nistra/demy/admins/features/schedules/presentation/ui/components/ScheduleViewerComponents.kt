package com.nistra.demy.admins.features.schedules.presentation.ui.components

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.nistra.demy.admins.features.schedules.domain.models.Schedule
import androidx.compose.ui.res.stringResource
import com.nistra.demy.admins.R

internal val HOUR_HEIGHT: Dp = 80.dp
internal val START_HOUR = 7
internal val END_HOUR = 20
internal val LIGHT_GRID_COLOR_ALPHA = 0.15f


internal fun getDayStringResId(dayBackendName: String): Int {
    return when (dayBackendName) {
        "MONDAY" -> R.string.day_monday
        "TUESDAY" -> R.string.day_tuesday
        "WEDNESDAY" -> R.string.day_wednesday
        "THURSDAY" -> R.string.day_thursday
        "FRIDAY" -> R.string.day_friday
        "SATURDAY" -> R.string.day_saturday
        "SUNDAY" -> R.string.day_sunday
        else -> 0
    }
}

internal val eventColors = listOf(
    Color(0xFFB1EEFF),
    Color(0xFFFFBAB5),
    Color(0xFFC4CBFF),
    Color(0xFFD9FAC4),
    Color(0xFFFFEABC),
)


fun timeStringToMinutes(timeStr: String): Int {
    return try {
        val parts = timeStr.split(":")
        val hours = parts.getOrElse(0) { "0" }.toInt()
        val minutes = parts.getOrElse(1) { "0" }.toInt()
        hours * 60 + minutes
    } catch (_: Exception) { 0 }
}

internal fun formatHour(hour: Int): String {
    return if (hour >= 13) "${hour - 12} PM" else "$hour AM"
}


@Composable
fun ScheduleViewerSelected(
    allSchedules: List<Schedule>,
    selectedScheduleName: String,
    onScheduleSelected: (Long, String) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        ScheduleDropdown(
            allSchedules = allSchedules,
            selectedScheduleName = selectedScheduleName,
            onScheduleSelected = onScheduleSelected,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun ScheduleDropdown(
    allSchedules: List<Schedule>,
    selectedScheduleName: String,
    onScheduleSelected: (Long, String) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    Box(modifier = modifier.wrapContentSize(Alignment.TopStart)) {
        OutlinedTextField(
            value = selectedScheduleName,
            onValueChange = {},
            label = { Text(stringResource(R.string.schedules_viewer_dropdown_label)) },
            readOnly = true,
            shape = RoundedCornerShape(8.dp),
            trailingIcon = {
                Icon(
                    Icons.Default.ArrowDropDown,
                    contentDescription = stringResource(R.string.schedules_viewer_dropdown_cd),
                    Modifier.clickable { expanded = true }
                )
            },
            modifier = Modifier.fillMaxWidth()
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
        ) {
            allSchedules.forEach { schedule ->
                DropdownMenuItem(
                    text = { Text(schedule.name, style = MaterialTheme.typography.bodyLarge) },
                    onClick = {
                        onScheduleSelected(schedule.id, schedule.name)
                        expanded = false
                    },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Composable
fun ScheduleViewContainer(
    schedule: Schedule?,
    days: List<String>,
    scrollState: ScrollState,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        if (schedule != null) {
            ScheduleGridHeader(days = days)
            Row(modifier = Modifier.fillMaxWidth().weight(1f)) {
                ScheduleTimeColumn(modifier = Modifier.width(60.dp), scrollState = scrollState)
                ScheduleGridContent(
                    schedule = schedule,
                    days = days,
                    scrollState = scrollState,
                    modifier = Modifier.weight(1f)
                )
            }
        } else {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(
                    stringResource(R.string.schedules_viewer_no_schedule),
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
private fun ScheduleGridHeader(days: List<String>, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp)
            .background(
                color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(modifier = Modifier.width(60.dp), contentAlignment = Alignment.Center) {
            Text("Hora", style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.onPrimary)
        }

        days.forEach { dayBackendName ->
            val dayDisplayName = stringResource(getDayStringResId(dayBackendName))
            Box(
                modifier = Modifier.weight(1f).fillMaxHeight(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = dayDisplayName,
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onPrimary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Composable
private fun ScheduleTimeColumn(modifier: Modifier = Modifier, scrollState: ScrollState) {
    Column(
        modifier = modifier
            .fillMaxHeight()
            .background(MaterialTheme.colorScheme.surfaceContainerLow)
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.End
    ) {

        for (i in START_HOUR + 1..END_HOUR) {
            Box(
                modifier = Modifier
                    .height(HOUR_HEIGHT)
                    .fillMaxWidth()
                    .padding(end = 8.dp),
                contentAlignment = Alignment.TopEnd
            ) {
                Text(
                    text = formatHour(i - 1),
                    style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.SemiBold),
                    color = MaterialTheme.colorScheme.outline
                )
            }
        }
    }
}


@Composable
fun ScheduleGridContent(
    schedule: Schedule,
    days: List<String>,
    modifier: Modifier = Modifier,
    scrollState: ScrollState
) {
    val allSessions = remember(schedule.classSessions) { schedule.classSessions }
    val sessionsGroupedByDay = remember(allSessions) { allSessions.groupBy { it.dayOfWeek.name } }

    Row(
        modifier = modifier
            .verticalScroll(scrollState)
            .fillMaxHeight()
            .background(MaterialTheme.colorScheme.surface)
    ) {
        days.forEach { dayBackendName ->
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .border(
                        width = 0.5.dp,
                        color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = LIGHT_GRID_COLOR_ALPHA)
                    )
            ) {
                VerticalGridLines()
                val daySessions = sessionsGroupedByDay[dayBackendName] ?: emptyList()
                daySessions.forEach { session ->
                    val eventColor = eventColors[(session.id % eventColors.size).toInt()]
                    ClassCard(session, eventColor = eventColor)
                }
            }
        }
    }
}

@Composable
private fun VerticalGridLines(modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxHeight()) {
        Spacer(modifier = Modifier.height(HOUR_HEIGHT / 2))
        val minutesPerLine = 30
        val slotsPerHour = 60 / minutesPerLine
        val lineCount = (END_HOUR - START_HOUR) * slotsPerHour
        val slotHeight = HOUR_HEIGHT / slotsPerHour

        repeat(lineCount) { index ->
            val isHourLine = index % slotsPerHour == 0
            HorizontalDivider(
                modifier = Modifier.height(slotHeight).fillMaxWidth(),
                thickness = if (isHourLine) 1.dp else 0.5.dp,
                color = if (isHourLine) MaterialTheme.colorScheme.outline.copy(alpha = LIGHT_GRID_COLOR_ALPHA) else MaterialTheme.colorScheme.outlineVariant.copy(alpha = LIGHT_GRID_COLOR_ALPHA)
            )
        }
    }
}
