package com.nistra.demy.admins.features.billing.domain.usecase

import com.nistra.demy.admins.features.billing.domain.model.BillingAccount
import com.nistra.demy.admins.features.billing.domain.repository.BillingAccountRepository
import javax.inject.Inject

class GetBillingAccountsUseCase @Inject constructor(
    private val repository: BillingAccountRepository
) {
    suspend operator fun invoke(): Result<List<BillingAccount>> {
        return runCatching {repository.getAllBillingAccounts()}
    }
}
