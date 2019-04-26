package com.manasses.manab.project.ui.userprofile

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.manasses.manab.project.data.local.entity.Atividade
import com.manasses.manab.project.data.repositories.AtividadeRepository
import javax.inject.Inject


 class AtividadeViewModel@Inject constructor(var atividadeRepository: AtividadeRepository) : BaseViewModel() {

    var  atividadeArray: MutableLiveData<ArrayList<Atividade>> = MutableLiveData()
    var  error: MutableLiveData<String> = MutableLiveData()

    fun getAtividade(){

        atividadeRepository.getAtividade({
            atividadeArray.value = it
        },{
            error.value = it.message
        })
    }


}