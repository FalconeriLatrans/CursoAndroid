package com.example.desafiowebservices.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import com.example.desafiowebservices.R
import com.example.desafiowebservices.util.Constants.Api.KEY_INTENT_EMAIL
import com.example.desafiowebservices.util.Constants.Api.KEY_INTENT_PASSWORD
import com.example.desafiowebservices.util.FieldValidation
import com.google.android.material.textfield.TextInputLayout

class RegisterActivity : AppCompatActivity() {

    private val buttomRegister: Button by lazy { findViewById(R.id.rg_save) }
    private val fieldName: TextInputLayout by lazy { findViewById(R.id.rg_name) }
    private val fieldEmail: TextInputLayout by lazy { findViewById(R.id.rg_email) }
    private val fieldPassword: TextInputLayout by lazy { findViewById(R.id.rg_password) }
    private val fieldValidation: FieldValidation by lazy { FieldValidation() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val actionBar = supportActionBar
        actionBar?.let {
            it.title = "Register"
            it.setDisplayHomeAsUpEnabled(true)
        }

        fieldEmail.editText?.setText(intent.getStringExtra(KEY_INTENT_EMAIL))

        buttomRegister.setOnClickListener {
            if (validateFields()) {
                val prevIntent = Intent().apply {
                    putExtra(KEY_INTENT_EMAIL, fieldEmail.editText?.text.toString())
                    putExtra(KEY_INTENT_PASSWORD, fieldPassword.editText?.text.toString())
                }
                setResult(Activity.RESULT_OK, prevIntent)
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

        fieldName.editText?.doOnTextChanged { _, _, _, _ -> fieldValidation.validateName(fieldName) }
        fieldEmail.editText?.doOnTextChanged { _, _, _, _ -> fieldValidation.validateEmail(fieldEmail)}
        fieldPassword.editText?.doOnTextChanged { _, _, _, _ -> fieldValidation.validatePassword(fieldPassword)}
    }
    private fun validateFields(): Boolean {
        val checkName = fieldValidation.validateName(fieldName)
        val checkEmail = fieldValidation.validateEmail(fieldEmail)
        val checkPassword = fieldValidation.validatePassword(fieldPassword)

        return checkName && checkEmail && checkPassword
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}