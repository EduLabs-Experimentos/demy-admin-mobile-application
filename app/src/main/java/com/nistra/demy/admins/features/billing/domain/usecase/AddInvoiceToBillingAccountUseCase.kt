package com.nistra.demy.admins.features.billing.domain.usecase

import com.nistra.demy.admins.features.billing.domain.model.BillingAccount
import com.nistra.demy.admins.features.billing.domain.repository.BillingAccountRepository
import com.nistra.demy.admins.features.invoicing.domain.model.Invoice
import javax.inject.Inject

class AddInvoiceToBillingAccountUseCase @Inject constructor(
    private val repository: BillingAccountRepository
) {
    suspend operator fun invoke(billingAccountId: String, invoice: Invoice): Result<BillingAccount> {
     return runCatching { repository.addInvoiceToBillingAccount(billingAccountId, invoice) }
    }
}
