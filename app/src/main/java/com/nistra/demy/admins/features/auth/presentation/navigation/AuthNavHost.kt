package com.nistra.demy.admins.features.auth.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nistra.demy.admins.core.designsystem.layout.AuthLayout
import com.nistra.demy.admins.features.auth.presentation.ui.screens.AcademySetupScreen
import com.nistra.demy.admins.features.auth.presentation.ui.screens.CompleteAccountScreen
import com.nistra.demy.admins.features.auth.presentation.ui.screens.SignInScreen
import com.nistra.demy.admins.features.auth.presentation.ui.screens.SignUpScreen
import com.nistra.demy.admins.features.auth.presentation.ui.screens.VerifyAccountScreen

@Composable
fun AuthNavHost(
    onLoggedIn: () -> Unit
) {
    val innerNavController = rememberNavController()

    AuthLayout {
        NavHost(
            navController = innerNavController,
            startDestination = AuthDestination.SignIn.route
        ) {
            composable(AuthDestination.SignIn.route) {
                SignInScreen(
                    onLoggedIn = onLoggedIn,
                    onGoToSignUp = { innerNavController.navigate(AuthDestination.SignUp.toRoute()) }
                )
            }
            composable(route = AuthDestination.SignUp.route) {
                SignUpScreen(
                    onSignUpSuccess = { email ->
                        innerNavController.navigate(AuthDestination.VerifyEmail.toRoute(email))
                    },
                    onGoToSignIn = {
                        innerNavController.navigate(AuthDestination.SignIn.toRoute())
                    }
                )
            }
            composable(route = AuthDestination.VerifyEmail.route) { backStackEntry ->
                val email = backStackEntry.arguments?.getString("email") ?: ""
                VerifyAccountScreen(
                    email = email,
                    onVerifiedSuccess = {
                        innerNavController.navigate(AuthDestination.CompleteAccount.toRoute())
                    }
                )
            }
            composable(route = AuthDestination.CompleteAccount.route) {
                CompleteAccountScreen(
                    onAccountCompletedSuccess = {
                        innerNavController.navigate(AuthDestination.SetUpAcademy.toRoute())
                    }
                )
            }
            composable(route = AuthDestination.SetUpAcademy.route) {
                AcademySetupScreen(
                    onAcademyCreated = {
                        onLoggedIn()
                    }
                )
            }
        }
    }
}
