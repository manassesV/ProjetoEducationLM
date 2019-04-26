package com.manasses.manab.project.ui.userprofile


import android.app.Dialog
import android.app.ProgressDialog
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.support.v4.app.Fragment
import com.manasses.manab.project.R
import com.manasses.manab.project.ui.main.MainActivity
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

open class BaseFragment: Fragment(){

    @Inject
    lateinit var viewModelFastory: ViewModelProvider.Factory
    protected var parentActivity: MainActivity? = null
    lateinit var  dialog: Dialog



    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is MainActivity) {
            val activity = context as MainActivity?
            this.parentActivity = activity

        }
    }

    protected fun getBaseActivity(): MainActivity? {
        return parentActivity
    }


    protected fun onPreExecute(estado: Boolean, msg:String = "") {
        if(estado == false){
            dialog = ProgressDialog(this.parentActivity)
            dialog.setTitle("$msg...");
            dialog.setContentView(R.layout.custom)
            dialog.setCancelable(false)
            dialog.show();
        }else{
            dialog.dismiss()
        }

    }

}