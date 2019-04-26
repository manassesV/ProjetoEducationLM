package com.manasses.manab.project.data.repositories

import android.arch.lifecycle.MutableLiveData
import com.manasses.manab.project.data.local.dao.UserDao
import com.manasses.manab.project.data.local.entity.User
import java.util.concurrent.Executor
import javax.inject.Inject
import javax.inject.Singleton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.AuthResult
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.manasses.manab.project.ui.main.MainActivity
import com.manasses.manab.project.util.user.AuthState
import com.manasses.manab.project.util.user.AuthValidation
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.auth.FirebaseUser






@Singleton
class UserRepository @Inject
constructor(
    private val userDao: UserDao,
    private val executor: Executor
) {
    private lateinit var mAuth: FirebaseAuth
    var authState: MutableLiveData<AuthState> = MutableLiveData<AuthState>()
    private var authValidation: AuthValidation = AuthValidation()


    fun getUser(
        user: User,
        onComplete: () -> Unit,
        onError: (Throwable?) -> Unit
    ) {

        mAuth = FirebaseAuth.getInstance()

        if (user.login) {
            loginUser(user, {
                onComplete()
            }, {onError(Throwable(""))})
        } else {
            createUser(user,{onComplete()},{ onError(Throwable(""))})
        }
    }


    private fun loginUser(
        user: User,
        onComplete: () -> Unit,
        onError: (Throwable?) -> Unit
    ) {
        executor.execute {
            try {

                if (authValidation.isValid(user))
                    mAuth.signInWithEmailAndPassword(user.email, user.password)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                onComplete()
                            } else if (!task.isSuccessful) {
                                onError(Throwable(AuthState.FAILED.msg))
                            }

                        }

            } catch (e: Exception) {
                onError(Throwable(AuthState.EXCEPTION.msg))
            }

        }
    }


    private fun createUser(user: User, onComplete: () -> Unit, onError: (Throwable?) -> Unit) {

        try {

            if (authValidation.isValid(user))
                    mAuth.createUserWithEmailAndPassword(user.email, user.password)
                        .addOnCompleteListener{ task ->

                            if (task.isSuccessful) {
                               // val profileUpdates =
                                 //   UserProfileChangeRequest.Builder().setDisplayName(user.name).build()
                                //mAuth.currentUser!!.updateProfile(profileUpdates)
                                mAuth.signInWithEmailAndPassword(user.email, user.password)
                                onComplete()
                            } else {
                                try {
                                    throw task.getException()!!;
                                } catch (e: FirebaseAuthUserCollisionException) {
                                    onError(Throwable(AuthState.EMAILEXISTENTE.msg))
                                }
                            }

                        }

        } catch (e: Exception) {
            onError(Throwable(AuthState.EXCEPTION.msg))
        }

    }

    fun Logado():Boolean{
        mAuth = FirebaseAuth.getInstance()
        val user = mAuth.currentUser

        if(user != null) {
            return true;
        }
     return false;
    }
    fun Logout(){
        mAuth.signOut()
    }
}
