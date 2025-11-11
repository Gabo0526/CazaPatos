package com.vasconez.gabriel.cazapatos

import android.os.Bundle
import android.util.Patterns
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {
    
    private lateinit var emailInputLayout: TextInputLayout
    private lateinit var passwordInputLayout: TextInputLayout
    private lateinit var emailEditText: TextInputEditText
    private lateinit var passwordEditText: TextInputEditText
    private lateinit var loginButton: MaterialButton
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        
        initializeViews()
        setupLoginButton()
    }
    
    private fun initializeViews() {
        emailInputLayout = findViewById(R.id.emailInputLayout)
        passwordInputLayout = findViewById(R.id.passwordInputLayout)
        emailEditText = findViewById(R.id.emailEditText)
        passwordEditText = findViewById(R.id.passwordEditText)
        loginButton = findViewById(R.id.loginButton)
    }
    
    private fun setupLoginButton() {
        loginButton.setOnClickListener {
            validateAndLogin()
        }
    }
    
    private fun validateAndLogin() {
        val email = emailEditText.text.toString().trim()
        val password = passwordEditText.text.toString()
        
        var isValid = true
        
        // Clear previous errors
        emailInputLayout.error = null
        passwordInputLayout.error = null
        
        // Validate email
        if (email.isEmpty()) {
            emailInputLayout.error = getString(R.string.error_empty_email)
            isValid = false
        } else if (!isValidEmail(email)) {
            emailInputLayout.error = getString(R.string.error_invalid_email)
            isValid = false
        }
        
        // Validate password
        if (password.isEmpty()) {
            passwordInputLayout.error = getString(R.string.error_empty_password)
            isValid = false
        } else if (password.length < 8) {
            passwordInputLayout.error = getString(R.string.error_password_length)
            isValid = false
        }
        
        if (isValid) {
            // Login successful - implement your login logic here
            // For now, we just clear the errors
            emailInputLayout.error = null
            passwordInputLayout.error = null
        }
    }
    
    private fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}