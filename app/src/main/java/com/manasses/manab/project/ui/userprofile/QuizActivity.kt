package com.manasses.manab.project.ui.userprofile

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.manasses.manab.project.R
import android.support.v7.widget.GridLayoutManager
import com.google.firebase.database.*
import com.manasses.manab.project.data.local.entity.Question
import kotlinx.android.synthetic.main.activity_quiz.*


class QuizActivity : AppCompatActivity() {


    private var mDatabase: DatabaseReference? = null
    private lateinit var hash: String;
    private var recycleString: ArrayList<String> = ArrayList()
    private var arrayQuestion: ArrayList<Question> = ArrayList()

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


                         for (botao in ds.child("answers").children){
                             recycleString.add(botao.value.toString())
                         }

                         var question = Question(
                             ds.child("pontos").value!!.toString().toInt(),
                             ds.child("question").value!!.toString(),
                             recycleString
                         )
                         arrayQuestion.add(question)


                     }
                }

                recyclerViewBotao.apply {
                    adapter  = BotaoAdapter(recycleString, this@QuizActivity)
                    layoutManager = GridLayoutManager(this@QuizActivity, 4)
                }


            }

            override fun onCancelled(p0: DatabaseError) {

            }
        })
    }




}
