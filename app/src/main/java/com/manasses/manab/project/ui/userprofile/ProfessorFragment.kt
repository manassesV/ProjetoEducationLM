package com.manasses.manab.project.ui.userprofile


import android.Manifest
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.manasses.manab.project.R
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_professor.*






class ProfessorFragment : BaseFragment() {


    private val CALL_PHONE_RESULT_CODE = 111

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(com.manasses.manab.project.R.layout.fragment_professor, container, false)

    }
    fun setUpDagger() {
        AndroidSupportInjection.inject(this)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setUpDagger()
        setUpVIew()

    }

    private fun setUpVIew() {

            call.setOnClickListener {

                if (ContextCompat.checkSelfPermission(parentActivity!!,
                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(
                        parentActivity!!,
                        arrayOf(Manifest.permission.CALL_PHONE),
                        CALL_PHONE_RESULT_CODE)

                } else {
                    var intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "+5511955567278"))
                    startActivity(intent)
                }


        }


        share.setOnClickListener {
            val sendIntent = Intent()
            sendIntent.action = Intent.ACTION_SEND
            sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.")
            sendIntent.type = "text/plain"
            startActivity(sendIntent)
        }


      voltar.setOnClickListener {
          parentActivity!!.
              getSupportFragmentManager().beginTransaction().remove(this).commit()

          val transaction = parentActivity!!.getSupportFragmentManager().beginTransaction()
              .add(R.id.container, MainFragment()).commit()
      }

    }




}
