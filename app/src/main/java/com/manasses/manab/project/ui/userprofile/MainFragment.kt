package com.manasses.manab.project.ui.userprofile



import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import com.google.android.gms.tasks.Task
import com.google.firebase.FirebaseApp
import com.irozon.alertview.AlertStyle
import com.irozon.alertview.AlertView
import com.manasses.manab.project.R
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_main.*




class MainFragment : BaseFragment() {

    private lateinit var viewModel: MainViewModel


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(com.manasses.manab.project.R.layout.fragment_main, container, false)

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



        atividade.setOnClickListener({
            parentActivity!!.
                getSupportFragmentManager().beginTransaction().remove(this).commit()

            val transaction = parentActivity!!.getSupportFragmentManager().beginTransaction()
                .add(R.id.container, AtividadeFragment()).commit()
        })

        scholl.setOnClickListener {
            parentActivity!!.
                getSupportFragmentManager().beginTransaction().remove(this).commit()

            val transaction = parentActivity!!.getSupportFragmentManager().beginTransaction()
                .add(R.id.container, MapsFragment()).commit()
        }

        about.setOnClickListener {
            parentActivity!!.
                getSupportFragmentManager().beginTransaction().remove(this).commit()

            val transaction = parentActivity!!.getSupportFragmentManager().beginTransaction()
                .add(R.id.container, AboutFragment()).commit()
        }

        close.setOnClickListener{
            onPreExecute(false, "Saindo")

            viewModel.Logout()
            parentActivity!!.
                getSupportFragmentManager().beginTransaction().remove(this).commit()

            val transaction = parentActivity!!.getSupportFragmentManager().beginTransaction()
                .add(R.id.container, UserProfileFragment()).commit()
            onPreExecute(true)
        }
    }


    fun setUpViewModel() {
        viewModel = ViewModelProviders
            .of(this, viewModelFastory)
            .get(MainViewModel::class.java)
    }


}
