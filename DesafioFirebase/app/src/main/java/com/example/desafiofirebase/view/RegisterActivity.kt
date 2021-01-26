package com.example.desafiofirebase.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import com.example.desafiofirebase.R
import com.example.desafiofirebase.util.Constants.Intent.KEY_INTENT_EMAIL
import com.example.desafiofirebase.util.FieldValidation
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegisterActivity : AppCompatActivity() {

    private val buttonRegister: Button by lazy { findViewById(R.id.rg_save) }
    private val fieldName: TextInputLayout by lazy { findViewById(R.id.rg_name) }
    private val fieldEmail: TextInputLayout by lazy { findViewById(R.id.rg_email) }
    private val fieldPassword: TextInputLayout by lazy { findViewById(R.id.rg_password) }
    private val fieldConfirmPassword: TextInputLayout by lazy { findViewById(R.id.rg_comfirmPassword) }
    private val fieldValidation: FieldValidation by lazy { FieldValidation() }

    private val firebaseAuth by lazy { Firebase.auth }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        initComponents()
        setupObservables()
    }

    private fun initComponents() {
        fieldEmail.editText?.setText(intent.getStringExtra(KEY_INTENT_EMAIL))
    }

    private fun setupObservables() {
        buttonRegister.setOnClickListener {
            if (validateFields()) {
                firebaseAuth.createUserWithEmailAndPassword(
                    fieldEmail.editText?.text.toString(),
                    fieldPassword.editText?.text.toString()
                )
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            Log.d("Firebase_Auth", "createUserWithEmail:success")
//                            val profileUpdates = userProfileChangeRequest {
//                                displayName = fieldName.editText.toString()
//                            }
//                            firebaseAuth.currentUser?.updateProfile(profileUpdates)?.addOnCompleteListener { task ->
//                                    if (task.isSuccessful) {
//                                        Log.d("Firebase_Auth", "User profile updated.")
//                                    }
//                                }
                            val intent = Intent(this, HomeActivity::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            Log.w("Firebase_Auth", "createUserWithEmail:failure", task.exception)
                            Toast.makeText(baseContext,"Authentication failed.",Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        }

        fieldName.editText?.doOnTextChanged { _, _, _, _ -> fieldValidation.validateName(fieldName) }
        fieldEmail.editText?.doOnTextChanged { _, _, _, _ ->
            fieldValidation.validateEmail(
                fieldEmail
            )
        }
        fieldPassword.editText?.doOnTextChanged { _, _, _, _ ->
            fieldValidation.validatePassword(
                fieldPassword
            )
        }
    }

    private fun validateFields(): Boolean {
        val checkName = fieldValidation.validateName(fieldName)
        val checkEmail = fieldValidation.validateEmail(fieldEmail)
        val checkPassword = fieldValidation.validatePassword(fieldPassword)
        val checkConfirmPassword = fieldValidation.validatePasswordConfirm(fieldPassword, fieldConfirmPassword)
        return checkName && checkEmail && checkPassword && checkConfirmPassword
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}