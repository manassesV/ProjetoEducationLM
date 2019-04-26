package com.manasses.manab.project.ui.userprofile

import android.arch.lifecycle.ViewModel
import com.manasses.manab.project.data.repositories.MainRepository
import com.manasses.manab.project.data.repositories.UserRepository
import javax.inject.Inject

class MainViewModel @Inject constructor(private var userRepository: UserRepository) :BaseViewModel() {



    fun Logout(){
        userRepository.Logout()
    }

}