package com.magma.app.features

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.magma.app.data.AuthRepository
import com.magma.app.databinding.ActivitySimpleListBinding

class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySimpleListBinding
    private val authRepo = AuthRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySimpleListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.title.text = "Profile"

        val user = authRepo.currentUser()
        binding.subtitle.text = if (user != null) "Signed in as: ${user.email ?: user.displayName}" else "Not signed in"

        // Add a simple sign-out action if signed in
        val signOutBtn = Button(this).apply { text = "Sign Out" }
        (binding.root as? android.widget.LinearLayout)?.addView(signOutBtn)
        signOutBtn.setOnClickListener {
            if (user != null) {
                authRepo.signOut()
                Toast.makeText(this, "Signed out", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, com.magma.app.auth.LoginActivity::class.java))
                finish()
            }
        }
    }
}
