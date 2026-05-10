package com.nistra.demy.admins.features.invoicing.domain.usecase

import com.nistra.demy.admins.features.invoicing.domain.repository.InvoiceRepository
import javax.inject.Inject

class DeleteInvoiceUseCase @Inject constructor(
    private val repository: InvoiceRepository
) {
    suspend operator fun invoke(billingAccountId: String, invoiceId: String): Result<Unit> {
        return runCatching { repository.deleteInvoice(billingAccountId, invoiceId) }
    }
}
