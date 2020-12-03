package com.example.ws

import android.media.MediaPlayer.OnPreparedListener
import android.net.Uri
import android.os.Bundle
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity


class MainActivity4 : AppCompatActivity() {
    lateinit var vv1 : VideoView
    lateinit var vv2 : VideoView
    lateinit var vv3 : VideoView
    lateinit var vv4: VideoView
    lateinit var vv5: VideoView
    lateinit var vv6: VideoView
    lateinit var vv7: VideoView
    lateinit var vv8: VideoView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)

        vv1 = findViewById<VideoView>(R.id.vv1)
        vv2 = findViewById<VideoView>(R.id.vv2)
        vv3 = findViewById<VideoView>(R.id.vv3)
        vv4 = findViewById<VideoView>(R.id.vv4)
        vv5 = findViewById<VideoView>(R.id.vv5)
        vv6 = findViewById<VideoView>(R.id.vv6)
        vv7 = findViewById<VideoView>(R.id.vv7)
        vv8 = findViewById<VideoView>(R.id.vv8)








        var videopath1 = "android.resource://com.example.shake/"+R.raw.vv1
        var uri = Uri.parse(videopath1)
        vv1.setVideoPath(videopath1)
        vv1.start()
        vv1.setOnPreparedListener(OnPreparedListener { mp -> mp.isLooping = true })


        var videopath2 = "android.resource://com.example.shake/"+R.raw.vv2
        var uri1 = Uri.parse(videopath2)
        vv2.setVideoPath(videopath2)
        vv2.start()
        vv2.setOnPreparedListener(OnPreparedListener { mp -> mp.isLooping = true })

        var videopath3 = "android.resource://com.example.shake/"+R.raw.vv3
        var uri3 = Uri.parse(videopath3)
        vv3.setVideoPath(videopath3)
        vv3.start()
        vv3.setOnPreparedListener(OnPreparedListener { mp -> mp.isLooping = true })


        var videopath4 = "android.resource://com.example.shake/"+R.raw.vv4
        var uri4 = Uri.parse(videopath4)
        vv4.setVideoPath(videopath4)
        vv4.start()
        vv4.setOnPreparedListener(OnPreparedListener { mp -> mp.isLooping = true })

        var videopath5 = "android.resource://com.example.shake/"+R.raw.vv5
        var uri5 = Uri.parse(videopath5)
        vv5.setVideoPath(videopath5)
        vv5.start()
        vv5.setOnPreparedListener(OnPreparedListener { mp -> mp.isLooping = true })

        var videopath6 = "android.resource://com.example.shake/"+R.raw.vv6
        var uri6 = Uri.parse(videopath6)
        vv6.setVideoPath(videopath6)
        vv6.start()
        vv6.setOnPreparedListener(OnPreparedListener { mp -> mp.isLooping = true })

        var videopath7 = "android.resource://com.example.shake/"+R.raw.vv7
        var uri7 = Uri.parse(videopath7)
        vv7.setVideoPath(videopath7)
        vv7.start()
        vv7.setOnPreparedListener(OnPreparedListener { mp -> mp.isLooping = true })

        var videopath8 = "android.resource://com.example.shake/"+R.raw.vv8
        var uri8 = Uri.parse(videopath8)
        vv8.setVideoPath(videopath8)
        vv8.start()
        vv8.setOnPreparedListener(OnPreparedListener { mp -> mp.isLooping = true })


    }
}