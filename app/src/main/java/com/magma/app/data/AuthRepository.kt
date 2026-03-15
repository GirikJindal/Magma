package com.magma.app.data

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.auth.GoogleAuthProvider

class AuthRepository {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    fun currentUser(): FirebaseUser? = auth.currentUser

    fun signIn(email: String, password: String, callback: (Boolean, String?) -> Unit) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) callback(true, null) else callback(false, task.exception?.message)
            }
    }

    fun register(email: String, password: String, displayName: String?, callback: (Boolean, String?) -> Unit) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    if (user != null && !displayName.isNullOrBlank()) {
                        val profile = UserProfileChangeRequest.Builder().setDisplayName(displayName).build()
                        user.updateProfile(profile).addOnCompleteListener {}
                    }
                    callback(true, null)
                } else {
                    callback(false, task.exception?.message)
                }
            }
    }

    fun signOut() {
        auth.signOut()
    }

    fun signInWithGoogle(idToken: String, callback: (Boolean, String?) -> Unit) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential).addOnCompleteListener { task ->
            if (task.isSuccessful) callback(true, null) else callback(false, task.exception?.message)
        }
    }
}
