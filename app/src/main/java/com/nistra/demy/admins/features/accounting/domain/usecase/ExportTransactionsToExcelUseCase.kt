package com.nistra.demy.admins.features.accounting.domain.usecase

import com.nistra.demy.admins.features.accounting.domain.repository.TransactionRepository
import java.io.File
import javax.inject.Inject

/**
 * Use case for exporting transactions to Excel format.
 *
 * @property repository Transaction repository.
 * @author Salim Ramirez
 */
class ExportTransactionsToExcelUseCase @Inject constructor(
    private val repository: TransactionRepository
) {
    suspend operator fun invoke(): Result<File> {
        return runCatching { repository.exportTransactionsToExcel() }
    }
}

