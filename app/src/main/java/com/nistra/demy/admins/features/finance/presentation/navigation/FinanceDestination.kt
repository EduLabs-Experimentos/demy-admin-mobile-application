package com.nistra.demy.admins.features.finance.presentation.navigation

sealed interface FinanceDestination {
    val route: String

    data object Finance : FinanceDestination {
        override val route = "finance"
        fun toRoute() = route
    }
}

