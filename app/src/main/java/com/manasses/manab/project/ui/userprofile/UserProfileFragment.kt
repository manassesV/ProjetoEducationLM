package com.manasses.manab.project.ui.userprofile

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.irozon.alertview.AlertActionStyle
import com.irozon.alertview.AlertStyle
import com.irozon.alertview.AlertTheme
import com.irozon.alertview.AlertView
import com.irozon.alertview.objects.AlertAction
import com.manasses.manab.project.R
import com.manasses.manab.project.util.user.AuthState
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_user_profile.*


class UserProfileFragment() : BaseFragment() {



    private lateinit var viewModel: UserProfileViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(com.manasses.manab.project.R.layout.fragment_user_profile, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        setUpDagger()
        setUpViewModel()
        setUpVIew()
        logado()
    }

    fun setUpDagger() {
        AndroidSupportInjection.inject(this)
    }


    fun logado(){

        if(viewModel.logado()){
            nextFragment(this, MainFragment())
        }
    }
    fun setUpVIew() {
        userscholl.setAnimation(AnimationUtils.loadAnimation(parentActivity!!, R.anim.fade_animation))


        viewModel.authState.observe(parentActivity!!, Observer { viewState ->
            viewState.let {

                if (!it.toString().equals(AuthState.HOPE.toString())) {
                    if (it.toString().equals(AuthState.SUCCESS.toString())) {

                        nextFragment(this, MainFragment())
                    } else {
                        AlertExecute("Atenção", it!!.msg)
                    }

                }
                onPreExecute(true)

            }
        })
          btLogin.setOnClickListener({
              onPreExecute(false, "Entrando")
              viewModel.getUser(txtEmail.text.toString(), txtPassword.text.toString())
          })

          btSignUp.setOnClickListener({

              nextFragment(this, SignUpFragment())


          })


    }




    fun setUpViewModel() {
        viewModel = ViewModelProviders
            .of(this, viewModelFastory)
            .get(UserProfileViewModel::class.java)
    }


}