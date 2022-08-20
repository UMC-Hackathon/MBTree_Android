package com.umc.project.mbtree.view.chat

interface MessageListener {
    fun onConnectSuccess()
    fun onConnectFailed()
    fun onClose()
    fun onMessage(text: String?)
}