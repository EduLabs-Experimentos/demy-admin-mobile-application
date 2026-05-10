package com.nistra.demy.admins.features.invoicing.domain.usecase

import com.nistra.demy.admins.features.invoicing.domain.model.Invoice
import com.nistra.demy.admins.features.invoicing.domain.repository.InvoiceRepository
import javax.inject.Inject

class GetInvoicesByStudentDniUseCase @Inject constructor(
    private val repository: InvoiceRepository
) {
    suspend operator fun invoke(dni: String): Result<List<Invoice>> {
        return runCatching { repository.getAllInvoicesByStudentDni(dni) }
    }
}
