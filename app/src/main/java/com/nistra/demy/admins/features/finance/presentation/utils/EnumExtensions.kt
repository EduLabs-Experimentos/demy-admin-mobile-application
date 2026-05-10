package com.nistra.demy.admins.features.finance.presentation.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.nistra.demy.admins.R
import com.nistra.demy.admins.features.finance.domain.model.Currency
import com.nistra.demy.admins.features.finance.domain.model.PaymentMethod
import com.nistra.demy.admins.features.finance.domain.model.TransactionCategory
import com.nistra.demy.admins.features.finance.domain.model.TransactionType

@Composable
fun TransactionType.toLocalizedString(): String {
    return when (this) {
        TransactionType.INCOME -> stringResource(R.string.finance_type_income)
        TransactionType.EXPENSE -> stringResource(R.string.finance_type_expense)
    }
}

@Composable
fun TransactionCategory.toLocalizedString(): String {
    return when (this) {
        TransactionCategory.STUDENT_ENROLLMENT -> stringResource(R.string.finance_category_student_enrollment)
        TransactionCategory.STUDENT_MONTHLY_FEE -> stringResource(R.string.finance_category_student_monthly_fee)
        TransactionCategory.STUDENT_ONE_TIME_PAYMENT -> stringResource(R.string.finance_category_student_one_time_payment)
        TransactionCategory.TEACHER_SALARY -> stringResource(R.string.finance_category_teacher_salary)
        TransactionCategory.OFFICE_SUPPLIES -> stringResource(R.string.finance_category_office_supplies)
        TransactionCategory.OTHER -> stringResource(R.string.finance_category_other)
    }
}

@Composable
fun PaymentMethod.toLocalizedString(): String {
    return when (this) {
        PaymentMethod.CASH -> stringResource(R.string.finance_method_cash)
        PaymentMethod.DEBIT_CARD -> stringResource(R.string.finance_method_debit_card)
        PaymentMethod.CREDIT_CARD -> stringResource(R.string.finance_method_credit_card)
        PaymentMethod.BANK_TRANSFER -> stringResource(R.string.finance_method_bank_transfer)
        PaymentMethod.WALLET -> stringResource(R.string.finance_method_wallet)
        PaymentMethod.OTHER -> stringResource(R.string.finance_method_other)
    }
}

@Composable
fun Currency.toLocalizedString(): String {
    return when (this) {
        Currency.PEN -> stringResource(R.string.finance_currency_pen)
        Currency.USD -> stringResource(R.string.finance_currency_usd)
    }
}

