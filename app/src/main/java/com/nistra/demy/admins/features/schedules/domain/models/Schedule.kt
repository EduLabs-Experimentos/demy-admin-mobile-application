package com.nistra.demy.admins.features.schedules.domain.models


data class Schedule(
    val id: Long,
    val name: String,
    val classSessions: List<ClassSession>
)
