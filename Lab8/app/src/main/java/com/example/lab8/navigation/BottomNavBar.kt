package com.example.lab8

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.lab8.BottomNavigation.BottomNavigationItem
import com.example.lab8.BottomNavigation.menuItems

@Composable
fun BottomNavBar(
    checkItemSelected: (Any) -> Boolean,
    onNavItemClick : (Any) -> Unit
){
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.onPrimary,
    ) {
        menuItems.forEach{ BottomNavigationItem ->
            val itemSelected = checkItemSelected(BottomNavigationItem.destination)

            NavigationBarItem(
                selected = itemSelected,
                label = { Text(text = BottomNavigationItem.title) },
                onClick = {
                    onNavItemClick(BottomNavigationItem.destination)
                },
                icon = {
                    Icon(
                        imageVector = if (itemSelected) {
                            BottomNavigationItem.selectedIcon
                        } else BottomNavigationItem.unselectedIcon,
                        contentDescription = BottomNavigationItem.title
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.White,
                    selectedTextColor = Color.White,
                    unselectedIconColor = Color.White.copy(alpha = 0.6f),
                    unselectedTextColor = Color.White.copy(alpha = 0.6f)
                )

            )

        }
    }
}
