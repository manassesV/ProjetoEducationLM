package com.manasses.manab.project.util.user

import com.manasses.manab.project.data.local.entity.User

class  AuthValidation{

    fun isValid(user: User):Boolean = user.email.isNotEmpty() && user.password.isNotEmpty()

}