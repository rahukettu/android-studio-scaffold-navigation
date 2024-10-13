package com.example.scaffold_navigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.scaffold_navigation.ui.theme.Scaffold_NavigationTheme
import androidx.navigation.compose.composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Scaffold_NavigationTheme {
                ScaffoldApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldApp() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "Home"
    ) {
        composable(route = "Home") {
            MainScreen(navController)
        }
        composable(route = "Info") {
            InfoScreen(navController)
        }
        composable(route = "Settings") {
            SettingsScreen(navController)
        }
    }
}
@Composable
fun MainScreen(navController: NavController) {
    Scaffold(
        topBar = { MainTopBar("My App", navController) },
        content = { paddingValues ->
            Box(modifier = Modifier.padding(paddingValues)) {
                Text(text = "Content for Home Screen", modifier = Modifier.padding(16.dp))
            }
        }
    )
}

@Composable
fun InfoScreen(navController: NavController) {
    Scaffold(
        topBar = { ScreenTopBar("Info", navController) },
        content = { paddingValues ->
            Box(modifier = Modifier.padding(paddingValues)) {
                Text(text = "Content for Info Screen", modifier = Modifier.padding(16.dp))
            }
        }
    )
}

@Composable
fun SettingsScreen(navController: NavController) {
    Scaffold(
        topBar = { ScreenTopBar("Settings", navController) },
        content = { paddingValues ->
            Box(modifier = Modifier.padding(paddingValues)) {
                Text(text = "Content for Settings Screen", modifier = Modifier.padding(16.dp))
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopBar(title: String, navController: NavController) {
    // State for Dropdown menu expansion
    var expanded by remember { mutableStateOf(false) }

    TopAppBar(
        title = { Text(title) },
        navigationIcon = {
            IconButton(onClick = { expanded = !expanded }) {
                Icon(Icons.Filled.Menu, contentDescription = null)
            }
        },
        actions = {
            IconButton(onClick = { expanded = !expanded }) {
                Icon(Icons.Filled.MoreVert, contentDescription = "More Options")
            }
            // Dropdown Menu for action items
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                DropdownMenuItem(
                    text = { Text("Info") },
                    onClick = {
                        expanded = false
                        navController.navigate("Info") // Navigate to Info screen
                    }
                )
                DropdownMenuItem(
                    text = { Text("Settings") },
                    onClick = {
                        expanded = false
                        navController.navigate("Settings") // Navigate to Settings screen
                    }
                )
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenTopBar(title: String, navController: NavController) {
    TopAppBar(
        title = { Text(title) },
        navigationIcon = {
            IconButton(onClick = { navController.navigateUp() }) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
            }
        }
    )
}


@Preview(showBackground = true)
@Composable
fun ScaffoldPreview() {
    Scaffold_NavigationTheme {
        ScaffoldApp()
    }
}
