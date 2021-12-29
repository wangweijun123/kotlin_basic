package com.wangweijun.myapplication.unit6

interface PlayerView {
    fun showView()
    fun getPlayButton()
}

class GreenPlayerView : PlayerView {
    override fun showView() {
        println("显示绿色的播放器")
    }

    override fun getPlayButton() {
        println("显示绿色的按钮")
    }
}

// 代理类
class MediaPlayerView(playerView: PlayerView) : PlayerView by playerView
