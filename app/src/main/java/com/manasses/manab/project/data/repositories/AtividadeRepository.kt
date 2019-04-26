package com.manasses.manab.project.data.repositories

import android.arch.lifecycle.MutableLiveData
import android.widget.Toast
import com.google.firebase.FirebaseError
import com.manasses.manab.project.data.local.entity.Atividade
import java.util.concurrent.Executor
import javax.inject.Inject
import javax.inject.Singleton
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.ValueEventListener
import com.google.android.gms.tasks.Task
import com.google.android.gms.tasks.TaskCompletionSource
import com.manasses.manab.project.data.local.entity.Question
import com.manasses.manab.project.data.local.entity.User






@Singleton
class AtividadeRepository @Inject
constructor(var executor: Executor){

    private var mDatabase: DatabaseReference? = null


    init {
        mDatabase = FirebaseDatabase.getInstance().getReference();

    }
    fun getAtividade(onComplete: (ArrayList<Atividade>) -> Unit,
                     onError: (DatabaseError) -> Unit)
    {
        var d = ArrayList<Atividade>()

        mDatabase!!.child("atividade").addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {

                        for (item_snapshot in dataSnapshot.children) {
                            var atividade = Atividade(
                                item_snapshot.key,
                                item_snapshot.child("atividade").value!!.toString(),
                                item_snapshot.child("descricao").value!!.toString())


                            d.add(atividade)
                        }

                        onComplete(d)
                    }

                    override fun onCancelled(databaseError: DatabaseError) {
                        onError(databaseError)
                    }
                })
    }




}