package com.example.mycommercejetpack.repository

import com.example.mycommercejetpack.data.User
import com.example.mycommercejetpack.singleton.UserSingleTon
import com.example.mycommercejetpack.utils.Util
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine


class AuthRepository  @Inject constructor(private val userSingleTon: UserSingleTon){

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()



    fun registerWithEmail(email: String, password: String, onResult: (Boolean, String?) -> Unit) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onResult(true, "User created: ${auth.currentUser?.uid}")
                } else {
                    onResult(false, task.exception?.message)
                }
            }
    }

    fun loginWithEmail(email: String, password: String, onResult: (Boolean, String?) -> Unit) {

        val emailCheck = Util.isValidEmail(email)


        if (emailCheck) {
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {

                        onResult(true, "Login successful: ${auth.currentUser?.uid}")
                    } else {
                        onResult(false, task.exception?.message)
                    }
                }
        } else {
            onResult(false, "please enter valid email")

        }
    }

    suspend fun loginWithEmailSecond(email: String, password: String): Result<User> {
        if (!Util.isValidEmail(email)) return Result.failure(Exception("Invalid email"))

        return suspendCoroutine { continuation ->
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val firebaseUser = auth.currentUser
                        if (firebaseUser != null) {
                            val user = User(
                                id = firebaseUser.uid.toIntOrNull() ?: 0,
                                email = firebaseUser.email,
                                password = password
                            )
                            continuation.resume(Result.success(user))
                        } else {
                            continuation.resume(Result.failure(Exception("User is null")))
                        }
                    } else {
                        continuation.resume(Result.failure(task.exception ?: Exception("Login failed")))
                    }
                }
        }.also { result ->
            // Save user to DataStore if login succeeds
            result.getOrNull()?.let { user ->
                userSingleTon.saveUser(user)
            }
        }
    }
}
