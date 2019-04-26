package com.manasses.manab.project.di.modules

import com.manasses.manab.project.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivityModule {

    @ContributesAndroidInjector(modules =  [FragmentModule::class])
    abstract fun contributeMainActivity() : MainActivity

}