package com.nistra.demy.admins.features.accounting.presentation.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.nistra.demy.admins.R

/**
 * Translates a transaction type value from backend to localized string.
 *
 * @param type The transaction type value from backend (e.g., "INCOME", "EXPENSE")
 * @return Localized string resource
 * @author Salim Ramirez
 */
@Composable
fun getTransactionTypeString(type: String): String {
    return when (type.uppercase()) {
        "INCOME" -> stringResource(R.string.accounting_type_income)
        "EXPENSE" -> stringResource(R.string.accounting_type_expense)
        else -> type
    }
}

/**
 * Translates a transaction category value from backend to localized string.
 *
 * @param category The transaction category value from backend (e.g., "STUDENT_ENROLLMENT")
 * @return Localized string resource
 * @author Salim Ramirez
 */
@Composable
fun getTransactionCategoryString(category: String): String {
    return when (category.uppercase()) {
        "STUDENT_ENROLLMENT", "STUDENT ENROLLMENT" -> stringResource(R.string.accounting_category_student_enrollment)
        "STUDENT_MONTHLY_FEE", "STUDENT MONTHLY FEE" -> stringResource(R.string.accounting_category_student_monthly_fee)
        "STUDENT_ONE_TIME_PAYMENT", "STUDENT ONE TIME PAYMENT" -> stringResource(R.string.accounting_category_student_one_time_payment)
        "TEACHER_SALARY", "TEACHER SALARY" -> stringResource(R.string.accounting_category_teacher_salary)
        "OFFICE_SUPPLIES", "OFFICE SUPPLIES" -> stringResource(R.string.accounting_category_office_supplies)
        "OTHER" -> stringResource(R.string.accounting_category_other)
        else -> category
    }
}

/**
 * Translates a payment method value from backend to localized string.
 *
 * @param method The payment method value from backend (e.g., "DEBIT_CARD")
 * @return Localized string resource
 * @author Salim Ramirez
 */
@Composable
fun getPaymentMethodString(method: String): String {
    return when (method.uppercase()) {
        "CASH" -> stringResource(R.string.accounting_method_cash)
        "DEBIT_CARD", "DEBIT CARD" -> stringResource(R.string.accounting_method_debit_card)
        "CREDIT_CARD", "CREDIT CARD" -> stringResource(R.string.accounting_method_credit_card)
        "BANK_TRANSFER", "BANK TRANSFER" -> stringResource(R.string.accounting_method_bank_transfer)
        "WALLET" -> stringResource(R.string.accounting_method_wallet)
        "OTHER" -> stringResource(R.string.accounting_method_other)
        else -> method
    }
}

