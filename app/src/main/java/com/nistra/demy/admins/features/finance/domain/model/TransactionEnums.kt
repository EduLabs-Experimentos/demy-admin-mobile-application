package com.nistra.demy.admins.features.finance.domain.model

enum class TransactionType(val value: String) {
    INCOME("Income"),
    EXPENSE("Expense");

    companion object {
        fun fromValue(value: String): TransactionType? {
            return entries.find { it.value.equals(value, ignoreCase = true) }
        }
    }
}

enum class TransactionCategory(val value: String) {
    STUDENT_ENROLLMENT("Student enrollment"),
    STUDENT_MONTHLY_FEE("Student monthly fee"),
    STUDENT_ONE_TIME_PAYMENT("Student one time payment"),
    TEACHER_SALARY("Teacher salary"),
    OFFICE_SUPPLIES("Office supplies"),
    OTHER("Other");

    companion object {
        fun fromValue(value: String): TransactionCategory? {
            return entries.find { it.value.equals(value, ignoreCase = true) }
        }
    }
}

enum class PaymentMethod(val value: String) {
    CASH("Cash"),
    DEBIT_CARD("Debit card"),
    CREDIT_CARD("Credit card"),
    BANK_TRANSFER("Bank transfer"),
    WALLET("Wallet"),
    OTHER("Other");

    companion object {
        fun fromValue(value: String): PaymentMethod? {
            return entries.find { it.value.equals(value, ignoreCase = true) }
        }
    }
}

enum class Currency(val code: String) {
    PEN("PEN"),
    USD("USD");

    companion object {
        fun fromCode(code: String): Currency? {
            return entries.find { it.code.equals(code, ignoreCase = true) }
        }
    }
}

