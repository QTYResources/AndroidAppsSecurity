package com.example.android.apis.media

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.MediaController
import android.widget.Toast
import android.widget.VideoView

class MainActivity : AppCompatActivity() {

    /**
     * TODO: Set the path variable to a streaming video URL or a local media
     * file path.
     */
    private val mPath = "/storage/89AE-1703/Video/bbb.mp4"
    private lateinit var mVideoView: VideoView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mVideoView = findViewById(R.id.video_view)

        if (mPath == "") {
            // Tell the user to provide a media file URL/path.
            Toast.makeText(this, "Please edit VideoViewDemo Activity, and set path"
                    + " variable to your media file URL/path", Toast.LENGTH_SHORT).show()
        } else {
            /*
             * Alternatively, for streaming media you can use
             * mVideoView.setVideoURI(Uri.parse(URLString));
             */
            mVideoView.setVideoPath(mPath)
            mVideoView.setMediaController(MediaController(this))
            mVideoView.requestFocus()
        }
    }
}