package com.manasses.manab.project.ui.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import dagger.android.AndroidInjection
import dagger.android.DispatchingAndroidInjector
import javax.inject.Inject
import dagger.android.support.HasSupportFragmentInjector
import android.support.v4.app.Fragment
import com.google.firebase.FirebaseApp
import com.manasses.manab.project.R
import com.manasses.manab.project.ui.userprofile.UserProfileFragment
import dagger.android.AndroidInjector
import android.transition.TransitionInflater


class MainActivity : AppCompatActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return dispatchingAndroidInjector
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        FirebaseApp.initializeApp(this)
        getSupportActionBar()!!.hide()
        setUpDagger()
        setUpFragment()



    }

    private fun setUpDagger() {
        AndroidInjection.inject(this)
    }

    fun setUpFragment() {
        supportFragmentManager
            .beginTransaction().setCustomAnimations(android.R.anim.fade_in,
                android.R.anim.fade_out, android.R.anim.fade_in,
            android.R.anim.fade_out)
            .add(R.id.container, UserProfileFragment(), null)
            .commit()
    }

    companion object {
        private val activity: MainActivity? = null

    }

    private fun setupWindowAnimations() {
        val slide = TransitionInflater.from(this).inflateTransition(com.manasses.manab.project.R.transition.activity_slide)
        window.exitTransition = slide
    }
}