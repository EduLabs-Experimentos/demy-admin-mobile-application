package com.nistra.demy.admins.features.accounting.presentation.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.nistra.demy.admins.R
import com.nistra.demy.admins.features.finance.domain.model.Currency
import com.nistra.demy.admins.features.finance.domain.model.PaymentMethod
import com.nistra.demy.admins.features.finance.domain.model.TransactionCategory
import com.nistra.demy.admins.features.finance.domain.model.TransactionType

/**
 * Extension function to convert TransactionType enum to localized string.
 */
@Composable
fun TransactionType.toLocalizedString(): String {
    return when (this) {
        TransactionType.INCOME -> stringResource(R.string.accounting_type_income)
        TransactionType.EXPENSE -> stringResource(R.string.accounting_type_expense)
    }
}

/**
 * Extension function to convert TransactionCategory enum to localized string.
 */
@Composable
fun TransactionCategory.toLocalizedString(): String {
    return when (this) {
        TransactionCategory.STUDENT_ENROLLMENT -> stringResource(R.string.accounting_category_student_enrollment)
        TransactionCategory.STUDENT_MONTHLY_FEE -> stringResource(R.string.accounting_category_student_monthly_fee)
        TransactionCategory.STUDENT_ONE_TIME_PAYMENT -> stringResource(R.string.accounting_category_student_one_time_payment)
        TransactionCategory.TEACHER_SALARY -> stringResource(R.string.accounting_category_teacher_salary)
        TransactionCategory.OFFICE_SUPPLIES -> stringResource(R.string.accounting_category_office_supplies)
        TransactionCategory.OTHER -> stringResource(R.string.accounting_category_other)
    }
}

/**
 * Extension function to convert PaymentMethod enum to localized string.
 */
@Composable
fun PaymentMethod.toLocalizedString(): String {
    return when (this) {
        PaymentMethod.CASH -> stringResource(R.string.accounting_method_cash)
        PaymentMethod.DEBIT_CARD -> stringResource(R.string.accounting_method_debit_card)
        PaymentMethod.CREDIT_CARD -> stringResource(R.string.accounting_method_credit_card)
        PaymentMethod.BANK_TRANSFER -> stringResource(R.string.accounting_method_bank_transfer)
        PaymentMethod.WALLET -> stringResource(R.string.accounting_method_wallet)
        PaymentMethod.OTHER -> stringResource(R.string.accounting_method_other)
    }
}

/**
 * Extension function to convert Currency enum to localized string.
 */
@Composable
fun Currency.toLocalizedString(): String {
    return when (this) {
        Currency.PEN -> stringResource(R.string.accounting_currency_pen)
        Currency.USD -> stringResource(R.string.accounting_currency_usd)
    }
}

/**
 * Parse a string value to TransactionType enum.
 */
fun String.toTransactionType(): TransactionType? {
    return TransactionType.entries.find { it.value.equals(this, ignoreCase = true) }
}

/**
 * Parse a string value to TransactionCategory enum.
 */
fun String.toTransactionCategory(): TransactionCategory? {
    return TransactionCategory.entries.find { it.value.equals(this, ignoreCase = true) }
}

/**
 * Parse a string value to PaymentMethod enum.
 */
fun String.toPaymentMethod(): PaymentMethod? {
    return PaymentMethod.entries.find { it.value.equals(this, ignoreCase = true) }
}

/**
 * Parse a string code to Currency enum.
 */
fun String.toCurrency(): Currency? {
    return Currency.entries.find { it.code.equals(this, ignoreCase = true) }
}

