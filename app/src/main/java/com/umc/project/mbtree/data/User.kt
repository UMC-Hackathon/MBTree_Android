package com.umc.project.mbtree.data

import androidx.room.Entity
import androidx.room.PrimaryKey


//@Entity(tableName = "UserTable")
//data class User(
//    val userId : Int,
//    var name : String,
//    var mbti: String
//){
//    @PrimaryKey(autoGenerate = true) var id:Int = 0
//}


data class User(
    val id: Int,
    var uuid: String?,
    var name: String,
    var email: String,
    var mbti: String,
    var location: String,
    var token: String,
    var point: Int,
    var createDate: String?
)
