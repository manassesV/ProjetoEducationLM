package com.manasses.manab.project

import android.app.Application
import android.app.Activity
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject



class MyApp : Application(), HasActivityInjector {
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>
    override fun onCreate() {
        super.onCreate()
        this.initDagger()
    }
    override fun activityInjector(): DispatchingAndroidInjector<Activity>? {
        return dispatchingAndroidInjector
    }
    private fun initDagger() {
        DaggerAppComponent.builder().application(this).build().inject(this)

    }
}