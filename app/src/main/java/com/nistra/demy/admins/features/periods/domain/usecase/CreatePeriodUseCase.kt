package com.nistra.demy.admins.features.periods.domain.usecase

import com.nistra.demy.admins.features.periods.domain.model.AcademicPeriod
import com.nistra.demy.admins.features.periods.domain.repository.AcademicPeriodRepository
import javax.inject.Inject

class CreatePeriodUseCase @Inject constructor(
    private val repository: AcademicPeriodRepository
) {
    suspend operator fun invoke(academicPeriod: AcademicPeriod): Result<AcademicPeriod> {
        return runCatching {
            repository.createPeriod(academicPeriod)
        }
    }

}
