package com.manasses.manab.project.ui.userprofile

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.manasses.manab.project.data.local.entity.User
import com.manasses.manab.project.data.repositories.UserRepository
import com.manasses.manab.project.util.user.AuthState
import javax.inject.Inject

class SignUpViewModel @Inject constructor(var userRepository: UserRepository) :BaseViewModel(){

    var  authState: MutableLiveData<AuthState> = MutableLiveData()

    fun getUser(userName: String, userEmail: String, userPassword: String, confPassword: String){

        var user = User()
        user.email = userEmail
        user.password = userPassword
        user.login = false

        if(user.email.isNullOrEmpty()){
            authState.value = AuthState.EMAIL
            return
        }

        if(!isEmailValid(user.email)){
            authState.value = AuthState.EMAILINVALID
            return
        }

        if(user.password.isNullOrEmpty()){
            authState.value = AuthState.PASSWORD
            return
        }

        if(user.password.length < 8){
            authState.value = AuthState.PASSWORDLENGHT
            return
        }


        if(user.password != confPassword){
            authState.value = AuthState.PASSWORDCONFIRME
            return
        }

        user.login = false
        userRepository.getUser(user, {
            authState.value = AuthState.SUCCESS
        }, {
            authState.value = AuthState.FAILED
        })


    }

    fun isEmailValid(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}