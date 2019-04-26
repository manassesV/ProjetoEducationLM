package com.manasses.manab.project.ui.userprofile


import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import com.irozon.alertview.AlertStyle
import com.irozon.alertview.AlertView
import com.manasses.manab.project.R
import com.manasses.manab.project.util.user.AuthState
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_about.*
import kotlinx.android.synthetic.main.fragment_sign_up.*


class AboutFragment : BaseFragment() {



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(com.manasses.manab.project.R.layout.fragment_about, container, false)

    }
    fun setUpDagger() {
        AndroidSupportInjection.inject(this)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setUpDagger()
        setUpVIew()

    }

    private fun setUpVIew() {
      voltar.setOnClickListener {
          parentActivity!!.
              getSupportFragmentManager().beginTransaction().remove(this).commit()

          val transaction = parentActivity!!.getSupportFragmentManager().beginTransaction()
              .add(R.id.container, MainFragment()).commit()
      }

    }




}
