package com.manasses.manab.project.ui.userprofile

import android.app.Activity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.content.pm.ActivityInfo
import android.graphics.ColorSpace
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import com.irozon.alertview.AlertStyle
import com.irozon.alertview.AlertView
import com.manasses.manab.project.R
import dagger.android.support.AndroidSupportInjection
import android.widget.Toast
import com.manasses.manab.project.data.local.entity.Atividade
import kotlinx.android.synthetic.main.fragment_atividade_list.*
import com.manasses.manab.project.util.ClickListener











class AtividadeFragment : BaseFragment() {
    private lateinit var viewModel: AtividadeViewModel
    private lateinit var adapter: AtividadeAdapter
    private var atividades = ArrayList<Atividade>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(com.manasses.manab.project.R.layout.fragment_atividade_list,
            container, false)
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

        try {

            onPreExecute(false, "Carregando dados")
            recyclerView.addOnItemTouchListener(
                RecyclerTouchListener(
                    parentActivity!!,
                    recyclerView,
                    object : ClickListener {
                        override fun onClick(view: View, position: Int) {
                            var at = atividades[position]


                            val bun = Bundle()
                            bun.putString("hash", at.hash)
                            val quiz = QuizFragment()
                            quiz.setArguments(bun)

                            parentActivity!!.
                                getSupportFragmentManager().beginTransaction().remove(this@AtividadeFragment).commit()

                            val transaction = parentActivity!!.getSupportFragmentManager().beginTransaction()
                                .add(R.id.container, quiz).commit()





                        }

                        override fun onLongClick(view: View, position: Int) {

                        }
                    })
            )
            viewModel.getAtividade()
            viewModel.atividadeArray.observe(parentActivity!!,  Observer { viewState ->


                atividades = viewState!!
                recyclerView.apply {
                    layoutManager = GridLayoutManager(parentActivity, 2)
                    adapter = AtividadeAdapter(viewState!!, parentActivity!!)
                }
                onPreExecute(true)

            })

            viewModel.error.observe(parentActivity!!, Observer{
                val alert = AlertView("Atenção", it!!,
                    AlertStyle.BOTTOM_SHEET).show(parentActivity!!)

            })
        }catch (ex: Exception){
            Toast.makeText(parentActivity, ex.message, Toast.LENGTH_LONG)
        }


        voltar.setOnClickListener{
            parentActivity!!.
                getSupportFragmentManager().beginTransaction().remove(this).commit()

            val transaction = parentActivity!!.getSupportFragmentManager().beginTransaction()
                .add(R.id.container, MainFragment()).commit()
        }



    }


    fun setUpViewModel() {
        viewModel = ViewModelProviders
            .of(this, viewModelFastory)
            .get(AtividadeViewModel::class.java)
    }


}

