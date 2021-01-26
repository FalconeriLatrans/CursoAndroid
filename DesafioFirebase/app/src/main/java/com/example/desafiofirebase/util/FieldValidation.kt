package com.example.desafiofirebase.util

import android.util.Patterns
import com.example.desafiofirebase.R
import com.google.android.material.textfield.TextInputLayout

class FieldValidation () {
    fun validateName(nameField: TextInputLayout): Boolean {
        return if (nameField.editText?.text?.isNotEmpty() == true){
            nameField.error = null
            true
        } else {
            nameField.error = nameField.context.getString(R.string.error_empty_field)
            false
        }
    }
    fun validateEmail(emailField:TextInputLayout): Boolean {
        val value = emailField.editText?.text?.trim().toString()
        return when {
            value.isEmpty() -> {
                emailField.error = emailField.context.getString(R.string.error_empty_field)
                false
            }
            !Patterns.EMAIL_ADDRESS.matcher(value).matches() -> {
                emailField.error = emailField.context.getString(R.string.error_invalid_email)
                false
            }
            else -> {
                emailField.error = null
                true
            }
        }
    }
    fun validatePassword(passwordField: TextInputLayout): Boolean {
        return if (passwordField.editText?.text?.isNotEmpty() ?: false == true){
            passwordField.error = null
            true
        } else {
            passwordField.error = passwordField.context.getString(R.string.error_empty_field)
            false
        }
    }
    fun validatePasswordConfirm(passwordField: TextInputLayout, passwordConfirmField: TextInputLayout): Boolean {
        val pssfld = passwordField.editText?.text.toString()
        val psscheck = passwordConfirmField.editText?.text.toString()
        return if (pssfld == psscheck){
            passwordConfirmField.error = null
            true
        } else {
            passwordConfirmField.error = passwordConfirmField.context.getString(R.string.error_password_check)
            false
        }
    }
}