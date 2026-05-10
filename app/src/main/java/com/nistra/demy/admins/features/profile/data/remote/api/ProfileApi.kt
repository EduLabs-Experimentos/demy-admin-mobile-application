package com.nistra.demy.admins.features.profile.data.remote.api

import com.nistra.demy.admins.features.profile.data.remote.dto.CurrentAcademyDto
import com.nistra.demy.admins.features.profile.data.remote.dto.CurrentAdministratorDto
import retrofit2.http.GET

interface ProfileApi {

    @GET("/api/v1/administrators/me")
    suspend fun getCurrentAdministrator(): CurrentAdministratorDto

    @GET("/api/v1/academies/current")
    suspend fun getCurrentAcademy(): CurrentAcademyDto
}
