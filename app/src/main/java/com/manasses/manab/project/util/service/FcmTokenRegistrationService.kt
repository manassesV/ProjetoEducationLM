package com.manasses.manab.project.util.service


import com.google.firebase.internal.FirebaseAppHelper.getToken
import com.google.firebase.iid.InstanceIdResult
import com.google.android.gms.tasks.Task
import android.support.annotation.NonNull
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId
import android.content.Intent
import android.app.IntentService
import android.util.Log


class FcmTokenRegistrationService : IntentService("FcmTokenRegistrationService") {

    override fun onHandleIntent(intent: Intent?) {

        FirebaseInstanceId.getInstance().instanceId
            .addOnCompleteListener(OnCompleteListener { task ->
                if (!task.isSuccessful) {

                    return@OnCompleteListener
                }

                // Get new Instance ID token
                val token = task.result!!.token


                //TODO register token to your server.
            })
    }


}