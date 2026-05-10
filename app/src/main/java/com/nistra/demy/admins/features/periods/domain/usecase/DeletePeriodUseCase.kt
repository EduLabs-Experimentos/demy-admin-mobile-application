package com.nistra.demy.admins.features.periods.domain.usecase

import com.nistra.demy.admins.features.periods.domain.repository.AcademicPeriodRepository
import javax.inject.Inject

class DeletePeriodUseCase @Inject constructor(
    private val repository: AcademicPeriodRepository
){
    suspend operator fun invoke(id: Long): Result<Unit> {
        return runCatching {
            repository.deletePeriod(id)
        }
    }
}
