package com.manasses.manab.project.ui.userprofile
import android.arch.lifecycle.MutableLiveData
import com.manasses.manab.project.data.local.entity.Pontos
import com.manasses.manab.project.data.local.entity.Question
import com.manasses.manab.project.data.repositories.QuizRepository
import com.manasses.manab.project.util.quiz.QuizState
import javax.inject.Inject

class QuizViewModel @Inject constructor(var quizRepository: QuizRepository) :BaseViewModel(){

    var  arrayQuestion: MutableLiveData<ArrayList<Question>> = MutableLiveData()
    var  erroQuestion: MutableLiveData<QuizState> = MutableLiveData()
    var savePontos: Boolean = false

    fun getQuiz(hash: String){

        quizRepository.quiz(hash, {
            arrayQuestion.value = it
        }, {
            erroQuestion.value = QuizState.EXCEPTION
        }, {
            erroQuestion.value = QuizState.DATABASEERROR
        })
    }

    fun  saveQuiz(pontos: Pontos){

        quizRepository.insertPontos(pontos,{
            savePontos = it
        },{
            erroQuestion.value = QuizState.EXCEPTION

        })
    }

}