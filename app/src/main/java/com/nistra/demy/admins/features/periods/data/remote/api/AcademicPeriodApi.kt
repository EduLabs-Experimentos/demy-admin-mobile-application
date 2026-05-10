package com.nistra.demy.admins.features.periods.data.remote.api

import com.nistra.demy.admins.features.periods.data.remote.models.AcademicPeriodResourceDto
import com.nistra.demy.admins.features.periods.data.remote.models.CreateAcademicPeriodRequestDto
import com.nistra.demy.admins.features.periods.data.remote.models.UpdateAcademicPeriodRequestDto
import com.nistra.demy.admins.features.periods.domain.usecase.CreatePeriodUseCase
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path


interface AcademicPeriodApi {

    @GET("academic-periods")
    suspend fun getAllPeriods(): List<AcademicPeriodResourceDto>

    @GET("academic-periods/{id}")
    suspend fun getPeriodById(@Path("id") id: Long): AcademicPeriodResourceDto

    @POST("academic-periods")
    suspend fun createPeriod(@Body request: CreateAcademicPeriodRequestDto): AcademicPeriodResourceDto

    @PUT("academic-periods/{id}")
    suspend fun updatePeriod(
        @Path("id") id: Long,
        @Body request: UpdateAcademicPeriodRequestDto
    ): AcademicPeriodResourceDto

    @DELETE("academic-periods/{id}")
    suspend fun deletePeriod(@Path("id") id: Long)
}
