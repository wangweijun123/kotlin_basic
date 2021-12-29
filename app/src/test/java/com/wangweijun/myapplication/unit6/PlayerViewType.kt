package com.wangweijun.myapplication.unit6

/**
 * 封闭 class 相当于枚举, 定义了两个常量
 */
sealed class PlayerViewType {
    object GREEN : PlayerViewType()
    object BLUE : PlayerViewType()
    object VIP : PlayerViewType()
}

fun getPlayerView(type: PlayerViewType) = when (type) {
    PlayerViewType.GREEN -> GreenPlayerView()
    PlayerViewType.BLUE -> BluePlayerView()
    PlayerViewType.VIP -> VIPPlayer("我是vip大客户哈哈")
}

/*fun getPlayerView(type: PlayerViewType): PlayerView {
    when (type) {
        PlayerViewType.GREEN -> GreenPlayerView()
        return PlayerViewType.BLUE -> BluePlayerView()
    }
}*/

