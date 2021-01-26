package com.example.desafiofirebase.view

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.Toast
import androidx.core.content.edit
import com.example.desafiofirebase.R
import com.example.desafiofirebase.util.Constants.Intent.KEY_INTENT_EMAIL
import com.example.desafiofirebase.util.Constants.SharedPreferences.KEY_SP_DBNAME
import com.example.desafiofirebase.util.Constants.SharedPreferences.KEY_SP_LOGIN_REMEMBER
import com.example.desafiofirebase.util.FieldValidation
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {

    private val fieldEmail: TextInputLayout by lazy {findViewById(R.id.lg_email)}
    private val fieldPassword: TextInputLayout by lazy {findViewById(R.id.lg_password)}
    private val checkboxPassword: CheckBox by lazy {findViewById(R.id.lg_passwordRemember)}
    private val buttonLogin: Button by lazy {findViewById(R.id.lg_login)}
    private val buttonRegister: Button by lazy {findViewById(R.id.lg_register)}
    private val fieldValidation: FieldValidation by lazy {FieldValidation()}
    private val sharedPreferences: SharedPreferences by lazy {getSharedPreferences(KEY_SP_DBNAME, MODE_PRIVATE)}

    private val firebaseAuth by lazy { Firebase.auth}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initComponents()
        setupObservables()
    }

    private fun initComponents() {
        checkboxPassword.isChecked = sharedPreferences.getBoolean(KEY_SP_LOGIN_REMEMBER,false) ?: false
    }

    private fun setupObservables() {
        buttonLogin.setOnClickListener {
            if (validateFields()) {
                firebaseAuth.signInWithEmailAndPassword(fieldEmail.editText?.text.toString(),fieldPassword.editText?.text.toString())
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("Firebase_Auth", "signInWithEmail:success")
                            finish()
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("Firebase_Auth", "signInWithEmail:failure", task.exception)
                            Toast.makeText(
                                baseContext, "Authentication failed.",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
            }
        }
        buttonRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            intent.putExtra(KEY_INTENT_EMAIL, fieldEmail.editText?.text.toString())
            startActivity(intent)
            finish()
        }
        checkboxPassword.setOnCheckedChangeListener{_, isChecked ->
            sharedPreferences.edit{
                putBoolean(KEY_SP_LOGIN_REMEMBER,isChecked)
            }
            Log.w("Shared_Preferences", "SharedPreferences: Update")
        }
    }
    private fun validateFields(): Boolean {
        val checkEmail = fieldValidation.validateEmail(fieldEmail)
        val checkPassword = fieldValidation.validatePassword(fieldPassword)

        return checkEmail && checkPassword
    }
}