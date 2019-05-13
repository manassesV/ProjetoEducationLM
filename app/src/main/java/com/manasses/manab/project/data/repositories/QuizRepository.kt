package com.manasses.manab.project.data.repositories

import com.google.firebase.database.*
import com.manasses.manab.project.data.local.dao.QuizDao
import com.manasses.manab.project.data.local.entity.Pontos
import com.manasses.manab.project.data.local.entity.Question
import com.manasses.manab.project.util.user.AuthState
import java.util.concurrent.Executor
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class QuizRepository @Inject
constructor(
    private val executor: Executor,
    private val quizDao: QuizDao
) {

    private var mDatabase: DatabaseReference? = null
    private var recycleString: ArrayList<String> = ArrayList()
    private var arrayQuestion: ArrayList<Question> = ArrayList()

    init {
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    fun quiz(
        hash: String,
        onComplete: (ArrayList<Question>?) -> Unit,
        onError: (Throwable?) -> Unit,
        onDatabaseError: (DatabaseError?) -> Unit
    ) {
        returnDados(hash, onComplete, onError, onDatabaseError)
    }


    private fun returnDados(
        hash: String,
        onComplete: (ArrayList<Question>?) -> Unit,
        onError: (Throwable?) -> Unit,
        onDatabaseError: (DatabaseError?) -> Unit
    ) {
        executor.execute {
            try {

                mDatabase!!.child("question").addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onCancelled(databaseError: DatabaseError) {
                        onDatabaseError(databaseError)
                    }

                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        arrayQuestion = arrayListOf()
                        for (item_snapshot in dataSnapshot.children) {


                            for (ds in item_snapshot.child(hash).children) {
                                recycleString = ArrayList()

                                for (botao in ds.child("answers").children) {
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
                        onComplete(arrayQuestion)
                    }
                })

            } catch (e: Exception) {
                onError(Throwable(AuthState.EXCEPTION.msg))
            }

        }
    }

     fun insertPontos(
        pontos: Pontos,
        onComplete: (Boolean) -> Unit,
        onError: (Throwable?) -> Unit) {

        try {
            quizDao.save(pontos)
            onComplete(true)
        }
        catch (e: Exception) {
          onError(Throwable(AuthState.EXCEPTION.msg))
        }
    }
}

