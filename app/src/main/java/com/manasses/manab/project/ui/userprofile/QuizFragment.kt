package com.manasses.manab.project.ui.userprofile
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.database.*
import com.manasses.manab.project.R
import com.manasses.manab.project.data.local.entity.Question
import com.manasses.manab.project.util.ClickListener
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_quiz.*


class QuizFragment : BaseFragment() {

    private lateinit var viewModel: QuizViewModel
    private var mDatabase: DatabaseReference? = null
    private lateinit var hash: String;
    private var recycleString: ArrayList<String> = ArrayList()
    private var arrayQuestion: ArrayList<Question> = ArrayList()
    private var posit: Int = 0

    init {
        mDatabase = FirebaseDatabase.getInstance().getReference();

    }

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

        mDatabase!!.child("question").addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                for (item_snapshot in dataSnapshot.children) {


                    for (ds in item_snapshot.child(hash).children){
                        recycleString = ArrayList()

                        for (botao in ds.child("answers").children){
                            recycleString.add(botao.value.toString())
                        }

                        var question = Question(
                            ds.child("pontos").value!!.toString().toInt(),
                            ds.child("question").value!!.toString(),
                            ds.child("correct_answer").value!!.toString(),
                            recycleString
                        )
                        arrayQuestion.add(question)


                    }
                }


                txtPergunta.text = arrayQuestion[posit].question
                txtPontos.text = arrayQuestion[posit].pontos.toString()



                recyclerViewBotao.apply {
                    adapter  = BotaoAdapter(arrayQuestion[0].anwser, parentActivity!!)
                    layoutManager = GridLayoutManager(parentActivity!!, 2)
                }


            }

            override fun onCancelled(p0: DatabaseError) {

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

                        }

                        posit = posit+1

                        if(arrayQuestion.size > posit){
                            arrayQuestion[posit]

                            txtPergunta.text = arrayQuestion[posit].question
                            txtPontos.text = arrayQuestion[posit].pontos.toString()
                            recyclerViewBotao.adapter = BotaoAdapter(arrayQuestion[posit].anwser,
                                parentActivity!!)
                        }else{


                            parentActivity!!.
                                getSupportFragmentManager().beginTransaction().remove(this@QuizFragment).commit()

                            val transaction = parentActivity!!.getSupportFragmentManager().beginTransaction()
                                .add(R.id.container, AtividadeFragment()).commit()
                        }
                    }

                    override fun onLongClick(view: View, position: Int) {

                    }
                })
        )

        voltar.setOnClickListener {
            parentActivity!!.
                getSupportFragmentManager().beginTransaction().remove(this).commit()

            val transaction = parentActivity!!.getSupportFragmentManager().beginTransaction()
                .add(R.id.container, AtividadeFragment()).commit()
         }
    }



    fun setUpViewModel() {
        viewModel = ViewModelProviders
            .of(this, viewModelFastory)
            .get(QuizViewModel::class.java)
    }


}
