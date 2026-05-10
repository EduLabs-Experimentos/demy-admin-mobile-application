package com.nistra.demy.admins.features.billing.data.mapper

import com.nistra.demy.admins.features.billing.data.remote.dto.BillingAccountResourceDto
import com.nistra.demy.admins.features.billing.data.remote.dto.CreateBillingAccountRequestDto
import com.nistra.demy.admins.features.billing.domain.model.BillingAccount
import com.nistra.demy.admins.features.invoicing.data.mapper.toDomain


fun BillingAccountResourceDto.toDomain(): BillingAccount {
    // Obtenemos el ID de la cuenta "padre" que estamos procesando.
    val parentAccountId = this.id

    return BillingAccount (
        id = this.id,
        studentId = this.studentId,
        dniNumber = this.dniNumber,
        academyId = this.academyId,
        invoices = this.invoices.map { invoiceDto ->
            invoiceDto.toDomain(billingAccountId = parentAccountId)
        }
    )
}


fun BillingAccount.toRequestDto(): CreateBillingAccountRequestDto {
    return CreateBillingAccountRequestDto(
        studentId = this.studentId
    )
}


