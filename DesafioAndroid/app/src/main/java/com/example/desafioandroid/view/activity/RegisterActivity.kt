package com.example.desafioandroid.view.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.widget.doOnTextChanged
import com.example.desafioandroid.R
import com.example.desafioandroid.util.FieldValidation
import com.example.desafioandroid.view.activity.LoginActivity.Companion.KEY_INTENT_EMAIL
import com.example.desafioandroid.view.activity.LoginActivity.Companion.KEY_INTENT_PASSWORD
import com.google.android.material.textfield.TextInputLayout


class RegisterActivity : AppCompatActivity() {

    private val registerButton: AppCompatButton by lazy { findViewById(R.id.rg_registerbutton) }
    private val nameField: TextInputLayout by lazy { findViewById(R.id.rgb_name) }
    private val emailField: TextInputLayout by lazy { findViewById(R.id.rgb_email) }
    private val passwordField: TextInputLayout by lazy { findViewById(R.id.rgb_password) }
    private val passwordConfirmField: TextInputLayout by lazy { findViewById(R.id.rgb_confirm_password) }
    private val fieldValidation: FieldValidation by lazy { FieldValidation() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val actionBar = supportActionBar
        actionBar?.let {
            it.title = "Register"
            it.setDisplayHomeAsUpEnabled(true)
        }

        emailField.editText?.setText(intent.getStringExtra(KEY_INTENT_EMAIL))

        registerButton.setOnClickListener {
            if (validateFields()) {
                val prevIntent = Intent().apply {
                    putExtra(KEY_INTENT_EMAIL, emailField.editText?.text.toString())
                    putExtra(KEY_INTENT_PASSWORD, passwordField.editText?.text.toString())
                }
                setResult(Activity.RESULT_OK, prevIntent)
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

        nameField.editText?.doOnTextChanged { _, _, _, _ -> fieldValidation.validateName(nameField) }
        emailField.editText?.doOnTextChanged { _, _, _, _ -> fieldValidation.validateEmail(emailField)}
        passwordField.editText?.doOnTextChanged { _, _, _, _ -> fieldValidation.validatePassword(passwordField)}
        passwordConfirmField.editText?.doOnTextChanged { _, _, _, _ -> fieldValidation.validatePasswordConfirm(passwordField, passwordConfirmField)}
    }
    private fun validateFields(): Boolean {
        val checkName = fieldValidation.validateName(nameField)
        val checkEmail = fieldValidation.validateEmail(emailField)
        val checkPassword = fieldValidation.validatePassword(passwordField)
        val checkPasswordConfirm = fieldValidation.validatePasswordConfirm(passwordField, passwordConfirmField)

        return checkName && checkEmail && checkPassword && checkPasswordConfirm
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}