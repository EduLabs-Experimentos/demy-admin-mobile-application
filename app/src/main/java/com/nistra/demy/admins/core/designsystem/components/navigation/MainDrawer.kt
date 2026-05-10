package com.nistra.demy.admins.core.designsystem.components.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.nistra.demy.admins.R
import com.nistra.demy.admins.core.designsystem.components.indicators.BottomEdgeFade
import com.nistra.demy.admins.core.designsystem.components.indicators.TopEdgeFade
import com.nistra.demy.admins.core.designsystem.model.DrawerSection
import com.nistra.demy.admins.core.navigation.model.DrawerDestination

@Composable
fun DrawerHeader(appName: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.demy_combination_mark_original),
            contentDescription = appName,
            modifier = Modifier.height(56.dp),
            contentScale = ContentScale.FillHeight
        )
    }
}

@Composable
fun DrawerContent(
    sections: List<DrawerSection>,
    selectedId: String,
    onDestinationClick: (DrawerDestination) -> Unit
) {
    val listState = rememberLazyListState()

    val canScrollBackward by remember {
        derivedStateOf { listState.firstVisibleItemIndex > 0 || listState.firstVisibleItemScrollOffset > 0 }
    }
    val canScrollForward by remember {
        derivedStateOf {
            val info = listState.layoutInfo
            val visible = info.visibleItemsInfo
            if (visible.isEmpty()) false
            else {
                val last = visible.last()
                last.index < info.totalItemsCount - 1 ||
                    (last.offset + last.size) > info.viewportEndOffset
            }
        }
    }

    Box {
        LazyColumn(
            state = listState,
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(vertical = 8.dp)
        ) {
            sections.forEach { section ->
                item(key = "header-${section.headerResId}") {
                    Text(
                        text = stringResource(section.headerResId),
                        style = MaterialTheme.typography.labelMedium,
                        modifier = Modifier.padding(top = 8.dp, start = 20.dp, end = 20.dp, bottom = 4.dp)
                    )
                }
                items(
                    items = section.items,
                    key = { it.id }
                ) { destination ->
                    val isSelected = selectedId == destination.id
                    NavigationDrawerItem(
                        label = { Text(stringResource(destination.labelResId), style = MaterialTheme.typography.bodyMedium) },
                        selected = isSelected,
                        onClick = { onDestinationClick(destination) },
                        icon = { Icon(destination.icon, contentDescription = null) },
                        modifier = Modifier.padding(horizontal = 12.dp),
                        colors = NavigationDrawerItemDefaults.colors(
                            selectedContainerColor = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.2f),
                            selectedIconColor = MaterialTheme.colorScheme.secondary,
                            selectedTextColor = MaterialTheme.colorScheme.secondary
                        )
                    )
                }
                item(key = "spacer-${section.headerResId}") { Spacer(Modifier.height(8.dp)) }
            }
            item(key = "bottom-padding") { Spacer(Modifier.height(8.dp)) }
        }

        TopEdgeFade(
            visible = canScrollBackward,
            modifier = Modifier.align(Alignment.TopCenter)
        )
        BottomEdgeFade(
            visible = canScrollForward,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}
