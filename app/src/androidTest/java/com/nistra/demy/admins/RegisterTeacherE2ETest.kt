package com.nistra.demy.admins

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RegisterTeacherE2ETest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun deberiaRegistrarProfesorYMostrarloEnLaLista() {
        // Arrange
        val timestamp = System.currentTimeMillis().toString()
        val uniqueEmail = "carlos_$timestamp@nistra.com"

        // Esperamos a que el Splash Screen termine
        composeTestRule.waitUntil(timeoutMillis = 8000) {
            composeTestRule.onAllNodesWithTag("input-email-login").fetchSemanticsNodes().isNotEmpty()
        }

        // Act

        // Iniciar sesión
        composeTestRule.onNodeWithTag("input-email-login").performTextInput("diegovilcatut@gmail.com")
        composeTestRule.onNodeWithTag("input-password-login").performTextInput("Sofiamia")
        composeTestRule.onNodeWithTag("btn-submit-login").performScrollTo().performClick()

        // Esperar navegación y entrar a Profesores
        composeTestRule.waitUntil(timeoutMillis = 8000) {
            composeTestRule.onAllNodesWithTag("btn-submit-login").fetchSemanticsNodes().isEmpty()
        }
        composeTestRule.onNodeWithText("Teachers", ignoreCase = true).performClick()

        // Registrar al profesor
        composeTestRule.onNodeWithTag("teacher-firstName").performTextInput("Carlos")
        composeTestRule.onNodeWithTag("teacher-lastName").performTextInput("Mendoza")
        composeTestRule.onNodeWithTag("teacher-email").performTextInput(uniqueEmail)
        composeTestRule.onNodeWithTag("teacher-submit-button").performClick()

        // ASSERT (Verificación de resultados)

        // Esperamos a que la red responda y validamos la UI
        composeTestRule.waitUntil(timeoutMillis = 5000) {
            composeTestRule.onAllNodesWithText(uniqueEmail).fetchSemanticsNodes().isNotEmpty()
        }
        composeTestRule.onNodeWithText(uniqueEmail).assertIsDisplayed()
    }
}
