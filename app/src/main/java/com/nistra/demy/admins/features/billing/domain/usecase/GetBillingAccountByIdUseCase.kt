package com.nistra.demy.admins.features.billing.domain.usecase

import com.nistra.demy.admins.features.billing.domain.model.BillingAccount
import com.nistra.demy.admins.features.billing.domain.repository.BillingAccountRepository
import javax.inject.Inject

class GetBillingAccountByIdUseCase @Inject constructor(
    private val repository: BillingAccountRepository
) {
    suspend operator fun invoke(billingAccountId: String): Result<BillingAccount> {
        return runCatching {repository.getBillingAccountById(billingAccountId)}
    }
}
