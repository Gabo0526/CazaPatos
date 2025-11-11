package com.vasconez.gabriel.cazapatos

import android.util.Patterns

/**
 * Validator for login credentials
 */
class LoginValidator {
    
    /**
     * Validates email address
     * @param email The email to validate
     * @return ValidationResult with success status and error message if any
     */
    fun validateEmail(email: String): ValidationResult {
        return when {
            email.isEmpty() -> ValidationResult(false, "El email no puede estar vacío")
            !isValidEmailFormat(email) -> ValidationResult(false, "El email debe ser válido")
            else -> ValidationResult(true, null)
        }
    }
    
    /**
     * Validates password
     * @param password The password to validate
     * @return ValidationResult with success status and error message if any
     */
    fun validatePassword(password: String): ValidationResult {
        return when {
            password.isEmpty() -> ValidationResult(false, "La contraseña no puede estar vacía")
            password.length < 8 -> ValidationResult(false, "La contraseña debe tener al menos 8 caracteres")
            else -> ValidationResult(true, null)
        }
    }
    
    /**
     * Validates both email and password
     * @param email The email to validate
     * @param password The password to validate
     * @return Pair of ValidationResult for email and password
     */
    fun validateCredentials(email: String, password: String): Pair<ValidationResult, ValidationResult> {
        return Pair(validateEmail(email), validatePassword(password))
    }
    
    private fun isValidEmailFormat(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
    
    data class ValidationResult(
        val isValid: Boolean,
        val errorMessage: String?
    )
}
