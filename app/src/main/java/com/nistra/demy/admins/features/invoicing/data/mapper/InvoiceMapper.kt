package com.nistra.demy.admins.features.invoicing.data.mapper

import com.nistra.demy.admins.features.invoicing.data.remote.dto.InvoiceResourceDto
import com.nistra.demy.admins.features.invoicing.domain.model.Invoice

fun InvoiceResourceDto.toDomain(billingAccountId: String ): Invoice {
    return Invoice(
        id = this.id,
        invoiceType = this.invoiceType,
        amount = this.amount,
        currency = this.currency,
        description = this.description,
        issueDate = this.issueDate,
        dueDate = this.dueDate,
        status = this.status,

        billingAccountId = billingAccountId
    )
}

fun Invoice.toResourceDto(): InvoiceResourceDto {
    return InvoiceResourceDto(
        id = this.id,
        invoiceType = this.invoiceType,
        amount = this.amount,
        currency = this.currency,
        description = this.description,
        issueDate = this.issueDate,
        dueDate = this.dueDate,
        status = this.status

    )
}




