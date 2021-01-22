package com.example.desafiowebservices.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import com.example.desafiowebservices.R
import com.example.desafiowebservices.util.Constants.Api.FROM_REGISTER
import com.example.desafiowebservices.util.Constants.Api.KEY_INTENT_EMAIL
import com.example.desafiowebservices.util.FieldValidation
import com.google.android.material.textfield.TextInputLayout

class LoginActivity : AppCompatActivity() {

    private val fieldEmail: TextInputLayout by lazy {findViewById(R.id.lg_email)}
    private val fieldPassword: TextInputLayout by lazy {findViewById(R.id.lg_password)}
    private val checkboxPassword: CheckBox by lazy {findViewById(R.id.lg_passwordRemember)}
    private val buttonLogin: Button by lazy {findViewById(R.id.lg_login)}
    private val buttonRegister: Button by lazy {findViewById(R.id.lg_register)}
    private val fieldValidation: FieldValidation by lazy { FieldValidation() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        setupObservables()
    }

    private fun setupObservables() {
        buttonLogin.setOnClickListener {
            if (validateFields()) {
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
            }
        }
        buttonRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            intent.putExtra(KEY_INTENT_EMAIL, fieldEmail.editText?.text.toString())
            startActivityForResult(intent, FROM_REGISTER)
        }
    }
    private fun validateFields(): Boolean {
        val checkEmail = fieldValidation.validateEmail(fieldEmail)
        val checkPassword = fieldValidation.validatePassword(fieldPassword)

        return checkEmail && checkPassword
    }
}