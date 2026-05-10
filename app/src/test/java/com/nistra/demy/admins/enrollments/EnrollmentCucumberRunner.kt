package com.nistra.demy.admins.features.enrollments

import io.cucumber.junit.Cucumber
import io.cucumber.junit.CucumberOptions
import org.junit.runner.RunWith

/**
 * Runner de Cucumber para los tests de Gestión de Matrículas.
 *
 * Coloca este archivo en:
 *   app/src/test/java/com/nistra/demy/admins/features/enrollments/EnrollmentCucumberRunner.kt
 *
 * Coloca el .feature en:
 *   app/src/test/resources/features/enrollment_management.feature
 *
 * Coloca los step definitions en:
 *   app/src/test/java/com/nistra/demy/admins/features/enrollments/steps/EnrollmentStepDefinitions.kt
 */
@RunWith(Cucumber::class)
@CucumberOptions(
    features = ["src/test/resources/features/enrollment_management.feature"],
    glue     = ["com.nistra.demy.admins.features.enrollments.steps"],
    plugin   = [
        "pretty",
        "html:build/reports/cucumber/enrollment-report.html",
        "json:build/reports/cucumber/enrollment-report.json"
    ],
    tags     = "",
    monochrome = true
)
class EnrollmentCucumberRunner
