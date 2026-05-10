package com.nistra.demy.admins.features.billing.data.repositories

import com.nistra.demy.admins.features.billing.data.datasource.remote.BillingAccountRemoteDataSource
import com.nistra.demy.admins.features.billing.data.mapper.toDomain as toBillingAccountDomain
import com.nistra.demy.admins.features.invoicing.data.mapper.toResourceDto
import com.nistra.demy.admins.features.billing.data.mapper.toRequestDto
import com.nistra.demy.admins.features.billing.domain.model.BillingAccount
import com.nistra.demy.admins.features.billing.domain.repository.BillingAccountRepository
import com.nistra.demy.admins.features.invoicing.data.mapper.toResourceDto
import com.nistra.demy.admins.features.invoicing.data.remote.dto.CreateInvoiceRequestDto
import com.nistra.demy.admins.features.invoicing.domain.model.Invoice

import javax.inject.Inject

class BillingAccountRepositoryImpl @Inject constructor(
    private val billingAccountsRemoteDataSource: BillingAccountRemoteDataSource
): BillingAccountRepository {

    override suspend fun  getAllBillingAccounts(): List<BillingAccount> {
        val billingAccountsResponse = billingAccountsRemoteDataSource.fetchBillingAccounts()
        return billingAccountsResponse.map { it.toBillingAccountDomain() }
    }

    override suspend fun getBillingAccountById(billingAccountId: String): BillingAccount {
        val billingAccountResponse = billingAccountsRemoteDataSource.fetchBillingAccountById(billingAccountId)
        return billingAccountResponse.toBillingAccountDomain()
    }

    override suspend fun  createBillingAccount(billingAccount: BillingAccount): BillingAccount {
        val createdBillingAccountResourceDto = billingAccountsRemoteDataSource.addBillingAccount(billingAccount.toRequestDto())
        return  createdBillingAccountResourceDto.toBillingAccountDomain()
    }

    override suspend fun addInvoiceToBillingAccount(billingAccountId: String, invoice: Invoice): BillingAccount {
        val requestDto = CreateInvoiceRequestDto(
            invoiceType = invoice.invoiceType,
            amount = invoice.amount,
            currency = invoice.currency,
            description = invoice.description,
            issueDate = invoice.issueDate,
            dueDate = invoice.dueDate,
            status = "PENDING"
        )

        val updatedAccountDto = billingAccountsRemoteDataSource.addInvoiceToBillingAccount(billingAccountId, requestDto)
        return updatedAccountDto.toBillingAccountDomain()

    }
}
