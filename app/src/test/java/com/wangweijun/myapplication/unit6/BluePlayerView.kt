package com.wangweijun.myapplication.unit6

class BluePlayerView : PlayerView {
    override fun showView() {
        println("显示blue色的播放器")
    }

    override fun getPlayButton() {
        println("显示blue色的按钮")
    }
}