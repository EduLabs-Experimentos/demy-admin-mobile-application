package com.nistra.demy.admins.features.invoicing.presentation.navigation

sealed interface InvoicesDestination {
    val route: String

    data object Invoices : InvoicesDestination {
        override val route = "invoices"
        fun toRoute() = route
    }
}

