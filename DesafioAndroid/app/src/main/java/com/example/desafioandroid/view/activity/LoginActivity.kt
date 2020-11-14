package com.example.desafioandroid.view.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.widget.doOnTextChanged
import com.example.desafioandroid.R
import com.example.desafioandroid.util.FieldValidation
import com.google.android.material.textfield.TextInputLayout

class LoginActivity : AppCompatActivity() {

    private val loginButton: AppCompatButton by lazy {findViewById(R.id.lg_loginbutton)}
    private val registerButton: AppCompatButton by lazy {findViewById(R.id.lg_registerbutton)}
    private val emailField: TextInputLayout by lazy {findViewById(R.id.lgb_email)}
    private val passwordField: TextInputLayout by lazy{findViewById(R.id.lgb_password)}
    private val fieldValidation: FieldValidation by lazy { FieldValidation() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        emailField.editText?.doOnTextChanged { _, _, _, _ -> fieldValidation.validateEmail(emailField) }
        passwordField.editText?.doOnTextChanged { _, _, _, _ -> fieldValidation.validatePassword(passwordField) }

        loginButton.setOnClickListener {
            if (validateFields()) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }
        registerButton.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            intent.putExtra(KEY_INTENT_EMAIL, emailField.editText?.text.toString())
            startActivityForResult(intent, FROM_REGISTER)
        }
    }
    private fun validateFields(): Boolean {
        val checkEmail = fieldValidation.validateEmail(emailField)
        val checkPassword = fieldValidation.validatePassword(passwordField)

        return checkEmail && checkPassword
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        emailField.editText?.setText(data?.getStringExtra(KEY_INTENT_EMAIL))
        passwordField.editText?.setText(data?.getStringExtra(KEY_INTENT_PASSWORD))
    }
    companion object {
        const val KEY_INTENT_EMAIL = "email"
        const val KEY_INTENT_PASSWORD = "password"
        const val FROM_REGISTER = 0
    }
}
