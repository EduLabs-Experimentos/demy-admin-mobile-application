package com.nistra.demy.admins.features.periods.data.mapper

import com.nistra.demy.admins.features.periods.data.remote.models.AcademicPeriodResourceDto
import com.nistra.demy.admins.features.periods.data.remote.models.CreateAcademicPeriodRequestDto
import com.nistra.demy.admins.features.periods.data.remote.models.UpdateAcademicPeriodRequestDto
import com.nistra.demy.admins.features.periods.domain.model.AcademicPeriod
import java.time.LocalDate

fun AcademicPeriodResourceDto.toDomain(): AcademicPeriod {
    return AcademicPeriod(
        id = this.id,
        periodName = this.periodName,
        startDate = this.startDate,
        endDate = this.endDate,
        isActive = this.isActive
    )
}

fun AcademicPeriod.toCreateRequestDto(): CreateAcademicPeriodRequestDto {
    return CreateAcademicPeriodRequestDto(
        periodName = this.periodName,
        startDate = this.startDate,
        endDate = this.endDate,
        isActive = this.isActive
    )
}

fun AcademicPeriod.toUpdateRequestDto(): UpdateAcademicPeriodRequestDto {
    return UpdateAcademicPeriodRequestDto(
        periodName = this.periodName,
        startDate = this.startDate,
        endDate = this.endDate,
        isActive = this.isActive
    )
}
