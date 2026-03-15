package com.magma.app.auth

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.magma.app.data.AuthRepository
import com.magma.app.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val authRepo = AuthRepository()
    private var googleClient: GoogleSignInClient? = null

    private val googleLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(Exception::class.java)
                val idToken = account?.idToken
                if (!idToken.isNullOrBlank()) {
                    authRepo.signInWithGoogle(idToken) { success, error ->
                        runOnUiThread {
                            if (success) {
                                startActivity(Intent(this, com.magma.app.MainActivity::class.java))
                                finish()
                            } else {
                                Toast.makeText(this, error ?: "Google sign-in failed", Toast.LENGTH_LONG).show()
                            }
                        }
                    }
                } else {
                    Toast.makeText(this, "Google sign-in returned no token", Toast.LENGTH_LONG).show()
                }
            } catch (e: Exception) {
                Toast.makeText(this, "Google sign-in failed: ${e.message}", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSignIn.setOnClickListener {
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString()
            if (email.isBlank() || password.isBlank()) {
                Toast.makeText(this, "Enter email and password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            authRepo.signIn(email, password) { success, error ->
                runOnUiThread {
                    if (success) {
                        startActivity(Intent(this, com.magma.app.MainActivity::class.java))
                        finish()
                    } else {
                        Toast.makeText(this, error ?: "Sign-in failed", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }

        binding.tvRegister.setOnClickListener {
            startActivity(Intent(this, com.magma.app.auth.RegisterActivity::class.java))
        }

        // Configure Google Sign-In to request the user's ID token.
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("YOUR_WEB_CLIENT_ID.apps.googleusercontent.com")
            .requestEmail()
            .build()
        googleClient = GoogleSignIn.getClient(this, gso)

        binding.btnGoogleSignIn.setOnClickListener {
            val signInIntent = googleClient?.signInIntent
            if (signInIntent != null) googleLauncher.launch(signInIntent)
        }
    }
}
