package com.molinax.manager.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.molinax.manager.R

// Keys back stack Navigation 3 — merepresentasikan 4 tab utama App Host.
// Isi layar aktual (Player/Editor/Terminal/Utilities) dipasang di Phase 4-8
// via entry point openEditor()/playMedia()/openTerminal()/openUtility()
// (Phase 7, blueprint SS5 dan SS7).
sealed interface MolinaXDestination

data object PlayerTab : MolinaXDestination
data object EditorTab : MolinaXDestination
data object TerminalTab : MolinaXDestination
data object UtilitiesTab : MolinaXDestination

private data class BottomNavItemSpec(
    val destination: MolinaXDestination,
    val labelRes: Int,
    val iconRes: Int,
)

private val bottomNavItems = listOf(
    BottomNavItemSpec(PlayerTab, R.string.tab_player, R.drawable.ic_tab_player),
    BottomNavItemSpec(EditorTab, R.string.tab_editor, R.drawable.ic_tab_editor),
    BottomNavItemSpec(TerminalTab, R.string.tab_terminal, R.drawable.ic_tab_terminal),
    BottomNavItemSpec(UtilitiesTab, R.string.tab_utilities, R.drawable.ic_tab_utilities),
)

@Composable
fun MolinaXNavHost() {
    val backStack = remember { mutableStateListOf<MolinaXDestination>(PlayerTab) }

    Scaffold(
        bottomBar = {
            NavigationBar {
                val currentTab = backStack.lastOrNull()
                bottomNavItems.forEach { item ->
                    NavigationBarItem(
                        selected = currentTab == item.destination,
                        onClick = {
                            if (currentTab != item.destination) {
                                backStack.clear()
                                backStack.add(item.destination)
                            }
                        },
                        icon = {
                            Icon(
                                painter = painterResource(item.iconRes),
                                contentDescription = stringResource(item.labelRes),
                            )
                        },
                        label = { Text(stringResource(item.labelRes)) },
                    )
                }
            }
        }
    ) { innerPadding ->
        NavDisplay(
            modifier = Modifier.padding(innerPadding),
            backStack = backStack,
            onBack = { backStack.removeLastOrNull() },
            entryProvider = entryProvider {
                entry<PlayerTab> { TabRootScreen(stringResource(R.string.tab_player)) }
                entry<EditorTab> { TabRootScreen(stringResource(R.string.tab_editor)) }
                entry<TerminalTab> { TabRootScreen(stringResource(R.string.tab_terminal)) }
                entry<UtilitiesTab> { TabRootScreen(stringResource(R.string.tab_utilities)) }
            },
        )
    }
}

@Composable
private fun TabRootScreen(title: String) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(title)
    }
}
