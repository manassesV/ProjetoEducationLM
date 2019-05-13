package com.manasses.manab.project.ui.userprofile


import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.youtube.player.*
import com.manasses.manab.project.R
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.activity_video.*


class VideoActivity : YouTubeBaseActivity(), YouTubePlayer.OnInitializedListener {
    private var youTubePlayer: YouTubePlayer? = null
    override fun onInitializationSuccess(
        provider: YouTubePlayer.Provider,
        player: YouTubePlayer,
        wasRestored: Boolean
    ) {
        if (!wasRestored) {
            youTubePlayer = player
            youTubePlayer?.setFullscreen(true)
            youTubePlayer?.setShowFullscreenButton(false)
            player.cueVideo("XzLUcTLMqks"); // Plays https://www.youtube.com/watch?v=fhWaJi1Hsfo
            //player.loadVideo("https://www.youtube.com/watch?v=fhWaJi1Hsfo")
        }
    }

    override fun onInitializationFailure(provider: YouTubePlayer.Provider, errorReason: YouTubeInitializationResult) {
        val error = String.format("Erro", errorReason.toString())
        Toast.makeText(this, error, Toast.LENGTH_LONG).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video)
        ytVideo.initialize("AIzaSyCVzNNpjCnhqED7mLWwHvzNoi35GffZA68", this)
    }
}