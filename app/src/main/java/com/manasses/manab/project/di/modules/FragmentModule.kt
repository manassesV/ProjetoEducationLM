package com.manasses.manab.project.di.modules

import com.manasses.manab.project.ui.userprofile.*
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeUserProfileFragment() : UserProfileFragment

    @ContributesAndroidInjector
    abstract fun  contributeMainFragment() : MainFragment

    @ContributesAndroidInjector
    abstract fun  contributeSignFragment() : SignUpFragment

    @ContributesAndroidInjector
    abstract fun  contributeMapsFragment() : MapsFragment

    @ContributesAndroidInjector
    abstract fun  contributeAtividadeFragment() : AtividadeFragment

    @ContributesAndroidInjector
    abstract fun contributeAboutFragment() : AboutFragment


    @ContributesAndroidInjector
    abstract fun contributeQuizFragment() : QuizFragment

    @ContributesAndroidInjector
    abstract fun contributeVideoFragment() : VideoFragment

    @ContributesAndroidInjector
    abstract fun contributeProfessorFragment() : ProfessorFragment

}