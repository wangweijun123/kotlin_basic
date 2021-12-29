package com.wangweijun.myapplication.unit6

data class User(var id: Int,
                var name: String,
                val playerType: PlayerViewType = PlayerViewType.BLUE)
