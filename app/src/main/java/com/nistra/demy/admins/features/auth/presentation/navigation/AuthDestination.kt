package com.nistra.demy.admins.features.auth.presentation.navigation

sealed interface AuthDestination {
    val route: String

    data object SignIn : AuthDestination {
        override val route = "auth/sign-in"
        fun toRoute() = route
    }

    data object SignUp : AuthDestination {
        override val route = "auth/sign-up"
        fun toRoute() = route
    }

    data object VerifyEmail : AuthDestination {
        override val route = "auth/verify-email/{email}"
        fun toRoute(email: String) = "auth/verify-email/$email"
    }

    data object CompleteAccount : AuthDestination {
        override val route = "auth/complete-account"
        fun toRoute() = route
    }

    data object SetUpAcademy : AuthDestination {
        override val route = "auth/set-up-academy"
        fun toRoute() = route
    }
}
