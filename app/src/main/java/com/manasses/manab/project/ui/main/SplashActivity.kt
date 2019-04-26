package com.manasses.manab.project.ui.main

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.manasses.manab.project.R
import android.support.v4.os.HandlerCompat.postDelayed
import android.os.Handler


class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        getSupportActionBar()!!.hide();
        mostrarActivity();
    }

    private fun mostrarActivity() {
        val handle = Handler()
        handle.postDelayed(Runnable {

            var intent = Intent(this,
                MainActivity::class.java);
            startActivity(intent);
            finish();
        }, 3000)

    }
}
