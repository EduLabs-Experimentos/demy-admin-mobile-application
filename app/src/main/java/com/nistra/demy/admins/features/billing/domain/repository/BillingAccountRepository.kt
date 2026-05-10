package com.nistra.demy.admins.features.billing.domain.repository

import com.nistra.demy.admins.features.billing.domain.model.BillingAccount
import com.nistra.demy.admins.features.invoicing.domain.model.Invoice

interface BillingAccountRepository {

    suspend fun createBillingAccount(billingAccount: BillingAccount): BillingAccount

    suspend fun getBillingAccountById(billingAccountId: String): BillingAccount

    suspend fun getAllBillingAccounts(): List<BillingAccount>

    suspend fun addInvoiceToBillingAccount(billingAccountId: String, invoice: Invoice): BillingAccount


}
