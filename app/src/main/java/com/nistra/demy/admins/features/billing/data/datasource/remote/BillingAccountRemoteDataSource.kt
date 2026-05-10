package com.nistra.demy.admins.features.billing.data.datasource.remote

import com.nistra.demy.admins.features.billing.data.remote.dto.BillingAccountResourceDto
import com.nistra.demy.admins.features.billing.data.remote.dto.CreateBillingAccountRequestDto
import com.nistra.demy.admins.features.invoicing.data.remote.dto.CreateInvoiceRequestDto
import com.nistra.demy.admins.features.invoicing.data.remote.dto.InvoiceResourceDto

interface BillingAccountRemoteDataSource {

    suspend fun fetchBillingAccounts(): List<BillingAccountResourceDto>

    suspend fun fetchBillingAccountById(billingAccountId: String): BillingAccountResourceDto

    suspend fun addBillingAccount(request: CreateBillingAccountRequestDto): BillingAccountResourceDto

    suspend fun addInvoiceToBillingAccount(billingAccountId: String ,request: CreateInvoiceRequestDto): BillingAccountResourceDto

    suspend fun markInvoiceAsPaid(billingAccountId: String, invoiceId: String): InvoiceResourceDto

    suspend fun fetchInvoicesByStudentDni(dni: String): List<InvoiceResourceDto>

    suspend fun deleteInvoice(billingAccountId: String, invoiceId: String)


}
