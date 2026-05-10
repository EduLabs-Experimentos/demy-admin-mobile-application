package com.nistra.demy.admins.features.billing.presentation.navigation

const val BILLING_ACCOUNT_ID_ARG = "billingAccountId"
sealed interface BillingDestination {
    val route: String

    data object Billing : BillingDestination {
        override val route = "billing"
        fun toRoute() = route
    }
    data object BillingAccountDetails: BillingDestination {

        override val route = "billing_details"

        fun toRoute() = "$route/{$BILLING_ACCOUNT_ID_ARG}"

        fun createRoute(billingAccountId: String) = "$route/$billingAccountId"
    }
}

