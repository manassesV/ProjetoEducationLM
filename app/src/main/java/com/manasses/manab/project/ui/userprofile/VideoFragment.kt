package com.manasses.manab.project.ui.userprofile


import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView
import com.manasses.manab.project.R
import dagger.android.support.AndroidSupportInjection


const val YOUTUBE_VIDEO_ID = "2Dpd_8n3A5U"
const val YOUTUBE_PLAYLIST_ID = "PLTHOlLMWEwVy2ZNmdrwRlRlVfZ8fiR_ms"

class VideoFragment : BaseFragment(), YouTubePlayer.OnInitializedListener {

    private val TAG = "YoutubeActivity"
    private val DIALOG_REQUEST_CODE = 1

    private val playerView by lazy { YouTubePlayerView(parentActivity) }

    override fun onInitializationSuccess(provider: YouTubePlayer.Provider?, player: YouTubePlayer?, wasRestored: Boolean) {
        Toast.makeText(parentActivity, "Initialized YoutubePlayer successfully", Toast.LENGTH_LONG).show()

        if (!wasRestored) {
            player?.loadVideo(YOUTUBE_VIDEO_ID)
            player?.setPlayerStateChangeListener(playerStateListener)
            player?.setPlaybackEventListener(playerPlaybackListener)
        } else {
            player?.play()
        }
    }

    private val playerPlaybackListener = object : YouTubePlayer.PlaybackEventListener {
        override fun onPlaying() {
            Toast.makeText(parentActivity, "OnPlaying", Toast.LENGTH_SHORT).show()
        }

        override fun onPaused() {
            Toast.makeText(parentActivity, "OnPaused", Toast.LENGTH_SHORT).show()
        }

        override fun onSeekTo(p0: Int) {}

        override fun onBuffering(p0: Boolean) {}

        override fun onStopped() {
            Toast.makeText(parentActivity, "OnStopped", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onInitializationFailure(provider: YouTubePlayer.Provider?, result: YouTubeInitializationResult?) {
        if (result?.isUserRecoverableError == true) {
            result.getErrorDialog(parentActivity, DIALOG_REQUEST_CODE).show()
        } else {
            Toast.makeText(parentActivity, "The YouTubePlayer initialization error $(result)", Toast.LENGTH_LONG).show()
        }
    }

    private val playerStateListener = object: YouTubePlayer.PlayerStateChangeListener {
        override fun onAdStarted() {
            Toast.makeText(parentActivity, "Ad started", Toast.LENGTH_SHORT).show()
        }

        override fun onLoading() {}

        override fun onVideoStarted() {
            Toast.makeText(parentActivity, "Video started", Toast.LENGTH_SHORT).show()
        }

        override fun onLoaded(p0: String?) {}

        override fun onVideoEnded() {
            Toast.makeText(parentActivity, "Video ended", Toast.LENGTH_SHORT).show()
        }

        override fun onError(p0: YouTubePlayer.ErrorReason?) {}

    }

    private lateinit var viewModel: VideoViewModel
    private lateinit var youTubePlayerView: YouTubePlayerView


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val layout = layoutInflater.inflate(R.layout.fragment_video, null) as ConstraintLayout

        playerView.layoutParams = ConstraintLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        layout.addView(playerView)

        playerView.initialize(getString(R.string.yotub), this)


        return layout

    }
    fun setUpDagger() {
        AndroidSupportInjection.inject(this)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setUpDagger()
        setUpVIew()
        setUpViewModel()
    }

    private fun setUpVIew() {


    }



    fun setUpViewModel() {
        viewModel = ViewModelProviders
            .of(this, viewModelFastory)
            .get(VideoViewModel::class.java)
    }



}
