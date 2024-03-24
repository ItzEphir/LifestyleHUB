package com.ephirium.lifestylehub.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ephirium.lifestylehub.androidBase.navigation.NavigationRoutes
import com.ephirium.lifestylehub.androidBase.navigation.ScreenProvider
import com.ephirium.lifestylehub.androidBase.navigation.navigate
import com.ephirium.lifestylehub.ui.navigation.mainGraph


@Composable
fun MainScaffold(screenProvider: ScreenProvider) {
    val navController = rememberNavController()
    
    val items = listOf(
        screenProvider.home,
        screenProvider.leisure,
        screenProvider.profile,
    )
    
    
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination by remember { derivedStateOf { navBackStackEntry?.destination } }
    
    Scaffold(bottomBar = {
        AnimatedVisibility(
            visible = currentDestination?.route in items.map { it.route },
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            
            NavigationBar(modifier = when (currentDestination?.route) {
                in items.map { it.route } -> Modifier
                else                      -> Modifier.height(0.dp)
            }, containerColor = MaterialTheme.colorScheme.primaryContainer.copy(
                alpha = 0.5f
            )) {
                items.forEach { screen ->
                    NavigationBarItem(
                        alwaysShowLabel = false,
                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                        colors = NavigationBarItemColors(
                            selectedIconColor = MaterialTheme.colorScheme.tertiary,
                            unselectedIconColor = MaterialTheme.colorScheme.secondary,
                            disabledIconColor = MaterialTheme.colorScheme.onPrimary,
                            selectedTextColor = MaterialTheme.colorScheme.tertiary,
                            unselectedTextColor = MaterialTheme.colorScheme.secondary,
                            disabledTextColor = MaterialTheme.colorScheme.onPrimary,
                            selectedIndicatorColor = MaterialTheme.colorScheme.secondaryContainer
                        ),
                        icon = {
                            Icon(
                                painterResource(id = screen.iconRes),
                                contentDescription = null,
                                modifier = Modifier.padding(4.dp),
                            )
                        },
                        label = {
                            Text(
                                text = stringResource(id = screen.textRes),
                                style = MaterialTheme.typography.bodyMedium,
                            )
                        },
                        onClick = {
                            navController.navigate(screen) {
                                popUpTo(navController.graph.startDestinationId) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                    )
                }
            }
        }
    }) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = NavigationRoutes.HOME,
            modifier = androidx.compose.ui.Modifier.padding(paddingValues)
        ) {
            mainGraph(navController, screenProvider)
        }
    }
}