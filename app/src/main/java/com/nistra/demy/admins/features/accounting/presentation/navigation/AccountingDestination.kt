package com.nistra.demy.admins.features.accounting.presentation.navigation

sealed interface AccountingDestination {
    val route: String

    data object Accounting : AccountingDestination {
        override val route = "accounting"
        fun toRoute() = route
    }
}

