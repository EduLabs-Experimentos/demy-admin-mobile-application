package com.nistra.demy.admins

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import com.nistra.demy.admins.core.designsystem.theme.DemyTheme
import com.nistra.demy.admins.core.navigation.AppNavigator
import dagger.hilt.android.AndroidEntryPoint

/**
 * Main activity of the app.
 *
 * This activity is the entry point of the application and sets up the main content view.
 * It uses Jetpack Compose for the UI and Hilt for dependency injection.
 * The activity enables edge-to-edge display and applies the app's theme.
 *
 * @AndroidEntryPoint indicates that Hilt should provide dependencies to this activity.
 * @author Nistra Team
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppGate {
                Demy()
            }
        }
    }
}

/**
 * Composable function that sets up the main navigation graph within the app's theme.
 *
 * It initializes a navigation controller and applies the DemyTheme to the RootNavGraph.
 * This function is called from the MainActivity to display the app's main content.
 *
 * @Composable indicates that this function can be used as a UI component in Jetpack Compose.
 * @author Nistra Team
 */
@Composable
private fun Demy() {
    DemyTheme {
        AppNavigator()
    }
}
