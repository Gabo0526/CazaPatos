package com.vasconez.gabriel.cazapatos

import org.junit.Test
import org.junit.Assert.*
import org.junit.Before

/**
 * Unit tests for LoginValidator
 * Note: These tests require Robolectric or instrumented tests to work with Android's Patterns class
 */
class LoginValidatorTest {
    
    private lateinit var loginValidator: LoginValidator
    
    @Before
    fun setUp() {
        loginValidator = LoginValidator()
    }
    
    @Test
    fun validatePassword_withEmptyPassword_returnsError() {
        val result = loginValidator.validatePassword("")
        
        assertFalse(result.isValid)
        assertEquals("La contraseña no puede estar vacía", result.errorMessage)
    }
    
    @Test
    fun validatePassword_withLessThan8Characters_returnsError() {
        val result = loginValidator.validatePassword("1234567")
        
        assertFalse(result.isValid)
        assertEquals("La contraseña debe tener al menos 8 caracteres", result.errorMessage)
    }
    
    @Test
    fun validatePassword_withExactly8Characters_isValid() {
        val result = loginValidator.validatePassword("12345678")
        
        assertTrue(result.isValid)
        assertNull(result.errorMessage)
    }
    
    @Test
    fun validatePassword_withMoreThan8Characters_isValid() {
        val result = loginValidator.validatePassword("123456789")
        
        assertTrue(result.isValid)
        assertNull(result.errorMessage)
    }
    
    @Test
    fun validateEmail_withEmptyEmail_returnsError() {
        val result = loginValidator.validateEmail("")
        
        assertFalse(result.isValid)
        assertEquals("El email no puede estar vacío", result.errorMessage)
    }
    
    @Test
    fun validateCredentials_withValidEmailAndPassword_returnsValid() {
        val (emailResult, passwordResult) = loginValidator.validateCredentials(
            "test@example.com",
            "password123"
        )
        
        assertTrue(emailResult.isValid)
        assertTrue(passwordResult.isValid)
    }
    
    @Test
    fun validateCredentials_withInvalidPassword_returnsPasswordError() {
        val (emailResult, passwordResult) = loginValidator.validateCredentials(
            "test@example.com",
            "short"
        )
        
        assertTrue(emailResult.isValid)
        assertFalse(passwordResult.isValid)
        assertEquals("La contraseña debe tener al menos 8 caracteres", passwordResult.errorMessage)
    }
    
    @Test
    fun validateCredentials_withEmptyFields_returnsBothErrors() {
        val (emailResult, passwordResult) = loginValidator.validateCredentials("", "")
        
        assertFalse(emailResult.isValid)
        assertFalse(passwordResult.isValid)
        assertEquals("El email no puede estar vacío", emailResult.errorMessage)
        assertEquals("La contraseña no puede estar vacía", passwordResult.errorMessage)
    }
}
