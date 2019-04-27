package com.manasses.manab.project.ui.userprofile

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.manasses.manab.project.R
import android.support.v7.widget.GridLayoutManager
import android.view.View
import com.google.firebase.database.*
import com.manasses.manab.project.data.local.entity.Question
import com.manasses.manab.project.util.ClickListener
import kotlinx.android.synthetic.main.activity_quiz.*
import com.manasses.manab.project.ui.main.MainActivity





class QuizActivity : AppCompatActivity() {


    private var mDatabase: DatabaseReference? = null
    private lateinit var hash: String;
    private var recycleString: ArrayList<String> = ArrayList()
    private var arrayQuestion: ArrayList<Question> = ArrayList()
    private var posit: Int = 0

    init {
        mDatabase = FirebaseDatabase.getInstance().getReference();

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)
        getSupportActionBar()!!.hide()
        processDatas()


    }


    fun processDatas(){
        val bundle = intent.extras
        hash = bundle!!.getString("hash")

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
                    adapter  = BotaoAdapter(arrayQuestion[0].anwser, this@QuizActivity)
                    layoutManager = GridLayoutManager(this@QuizActivity, 4)
                }


            }

            override fun onCancelled(p0: DatabaseError) {

            }
        })


        recyclerViewBotao.addOnItemTouchListener(
            RecyclerTouchListener(
                this,
                recyclerViewBotao,
                object : ClickListener {
                    override fun onClick(view: View, position: Int) {
                        var at = arrayQuestion[posit]

                        var resposta = at.anwser[position]

                        if(at.correct_answer.equals(resposta)){
                          //  var pontos =  txtseusPontos.text.toString().toInt()
                            //txtseusPontos.text = (pontos + at.pontos!!.toInt()).toString()

                        }

                        posit = posit+1

                        if(arrayQuestion.size >= posit){
                            arrayQuestion[posit]

                            txtPergunta.text = arrayQuestion[posit].question
                            txtPontos.text = arrayQuestion[posit].pontos.toString()
                            recyclerViewBotao.adapter = BotaoAdapter(arrayQuestion[posit].anwser, this@QuizActivity)
                        }else{

                            val it = Intent(this@QuizActivity, MainActivity::class.java)
                            startActivity(it)
                            finish()
                        }
                    }

                    override fun onLongClick(view: View, position: Int) {

                    }
                })
        )
    }




}
