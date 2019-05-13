package com.manasses.manab.project.ui.userprofile
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.manasses.manab.project.data.local.entity.Pontos
import com.manasses.manab.project.data.local.entity.Question
import com.manasses.manab.project.util.ClickListener
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_quiz.*


class QuizFragment : BaseFragment() {

    private lateinit var viewModel: QuizViewModel
    private var arrayQuestion: ArrayList<Question> = ArrayList()
    private var posit: Int = 0


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(com.manasses.manab.project.R.layout.fragment_quiz, container, false)

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

    fun  setUpVIew(){
        val hash = arguments!!.getString("hash")
        viewModel.getQuiz(hash)
        viewModel.arrayQuestion.observe(parentActivity!!, Observer { arrays ->
            arrayQuestion = arrays!!

            txtPergunta.text = arrayQuestion!![posit].question
            txtPontos.text = arrayQuestion!![posit].pontos.toString()

            recyclerViewBotao.apply {
                adapter  = BotaoAdapter(arrayQuestion!![posit].anwser, parentActivity!!)
                layoutManager = GridLayoutManager(parentActivity!!, 2)
            }


        })


        recyclerViewBotao.addOnItemTouchListener(
            RecyclerTouchListener(
                parentActivity!!,
                recyclerViewBotao,
                object : ClickListener {
                    override fun onClick(view: View, position: Int) {
                        var at = arrayQuestion[posit]

                        var resposta = at.anwser[position]

                        if(at.correct_answer.equals(resposta)){
                            var pontos =  txtPontos.text.toString().toInt()
                            txtPontosGanhos.text = (pontos + at.pontos!!.toInt()).toString()
                            AlertExecute("Acertou", "Você ganhou $pontos")
                        }

                        posit = posit+1

                        if(arrayQuestion.size > posit){
                            arrayQuestion[posit]

                            txtPergunta.text = arrayQuestion[posit].question
                            txtPontos.text = arrayQuestion[posit].pontos.toString()
                            recyclerViewBotao.adapter = BotaoAdapter(arrayQuestion[posit].anwser,
                                parentActivity!!)
                        }else{
                            var pontos = Pontos(0,txtPontosGanhos.text.toString())
                            viewModel.saveQuiz(pontos)
                             nextFragment(this@QuizFragment, AtividadeFragment())
                        }
                    }

                    override fun onLongClick(view: View, position: Int) {

                    }
                })
        )

        voltar.setOnClickListener {
            nextFragment(this, AtividadeFragment())
         }
    }



    fun setUpViewModel() {
        viewModel = ViewModelProviders
            .of(this, viewModelFastory)
            .get(QuizViewModel::class.java)
    }


}
