package com.umc.project.mbtree.data

data class Chat(
    var content:String,
    var time:String,
    var viewType:Int,    //1:왼쪽 2:오른쪽 3:센터
    var question: String? = "",
    var answer1: String? = "",
    var answer2: String? = ""
)
