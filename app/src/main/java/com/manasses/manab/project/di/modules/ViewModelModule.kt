package com.manasses.manab.project.di.modules

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.manasses.manab.project.di.key.ViewModelKey
import com.manasses.manab.project.ui.userprofile.*
import com.manasses.manab.project.util.FactoryViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(UserProfileViewModel::class)
    abstract fun bindUserProfileViewModel(repoViewModel: UserProfileViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(repoViewModel: MainViewModel): ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(SignUpViewModel::class)
    abstract fun bindSignViewModel(signUpViewModel: SignUpViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MapsViewModel::class)
    abstract fun bindMapsViewModel(mapsViewModel: MapsViewModel): ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(AtividadeViewModel::class)
    abstract fun bindAtividadeViewModel(atividadeViewModel: AtividadeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(QuizViewModel::class)
    abstract fun bindQuizViewModel(quizViewModel: QuizViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(VideoViewModel::class)
    abstract fun bindVideoViewModel(videoViewModel: VideoViewModel): ViewModel


    @Binds
    abstract fun bindViewModelFactory(factory: FactoryViewModel): ViewModelProvider.Factory
}