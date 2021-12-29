package com.wangweijun.myapplication.unit6

/**
 * 单例
 */
class PlayerUI private constructor(){

    companion object {
        fun get(): PlayerUI {
            return Holder.instance
        }
    }

    private object Holder {
        val instance = PlayerUI()
    }

    fun showPlayer(user: User) {
        val playerView = getPlayerView(user.playerType)
        val mediaPlayerView = MediaPlayerView(playerView)
        mediaPlayerView.showView()
        mediaPlayerView.getPlayButton()
    }
}