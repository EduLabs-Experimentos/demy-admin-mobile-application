package com.nistra.demy.admins.features.invoicing.domain.repository

import com.nistra.demy.admins.features.invoicing.domain.model.Invoice

interface InvoiceRepository {

    suspend fun getAllInvoicesByStudentDni(dni: String): List<Invoice>

    suspend fun markInvoiceAsPaid(billingAccountId: String, invoiceId: String): Invoice

    suspend fun deleteInvoice(billingAccountId: String, invoiceId: String)

}
