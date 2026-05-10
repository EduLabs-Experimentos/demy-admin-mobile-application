package com.nistra.demy.admins.features.invoicing.domain.usecase
import com.nistra.demy.admins.features.invoicing.domain.model.Invoice
import com.nistra.demy.admins.features.invoicing.domain.repository.InvoiceRepository
import javax.inject.Inject

class MarkInvoiceAsPaidUseCase @Inject constructor(
    private val repository: InvoiceRepository
) {
    suspend operator fun invoke(billingAccountId: String, invoiceId: String): Result<Invoice> {
        return runCatching { repository.markInvoiceAsPaid(billingAccountId, invoiceId) }
    }
}
