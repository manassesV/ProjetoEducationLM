package com.manasses.manab.project.ui.userprofile



import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import com.irozon.alertview.AlertStyle
import com.irozon.alertview.AlertView
import com.manasses.manab.project.R
import com.manasses.manab.project.util.user.AuthState
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_sign_up.*




class SignUpFragment : BaseFragment() {

    private lateinit var viewModel: SignUpViewModel


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(com.manasses.manab.project.R.layout.fragment_sign_up, container, false)

    }
    fun setUpDagger() {
        AndroidSupportInjection.inject(this)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setUpDagger()
        setUpViewModel()
        setUpVIew()

    }

    private fun setUpVIew() {
        userscholl.setAnimation(AnimationUtils.loadAnimation(parentActivity!!, R.anim.fade_animation))
        viewModel.authState.observe(this,  Observer { viewState ->

            if(!viewState.toString().equals(AuthState.HOPE.toString())) {
                if (viewState.toString().equals(AuthState.SUCCESS.toString())) {

                    val alert = AlertView("Atenção", "Usuário cadastrado com sucesso",
                        AlertStyle.BOTTOM_SHEET).show(parentActivity!!)

                    parentActivity!!.
                        getSupportFragmentManager().beginTransaction().remove(this).commit()

                    val transaction = parentActivity!!.getSupportFragmentManager().beginTransaction()
                        .add(R.id.container, MainFragment()).commit()

                } else {
                    val alert = AlertView("Atenção", viewState!!.msg, AlertStyle.BOTTOM_SHEET).show(parentActivity!!)
                }
                onPreExecute(true)

            }
        })

        btSignUp.setOnClickListener({
            onPreExecute(false, "Cadastrando")
            viewModel.getUser(txtName.text.toString(), txtEmail.text.toString(), txtPassword.text.toString(), txtConfPassword.text.toString())
        })

        btLogin.setOnClickListener({
            parentActivity!!.
                getSupportFragmentManager().beginTransaction().remove(this).commit()

            val transaction = parentActivity!!.getSupportFragmentManager().beginTransaction()
                .add(R.id.container, UserProfileFragment()).commit()
        })

    }


    fun setUpViewModel() {
        viewModel = ViewModelProviders
            .of(this, viewModelFastory)
            .get(SignUpViewModel::class.java)
    }

}



