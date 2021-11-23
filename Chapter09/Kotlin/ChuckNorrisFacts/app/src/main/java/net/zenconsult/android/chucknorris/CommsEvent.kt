package net.zenconsult.android.chucknorris

interface CommsEvent {
    fun onTextReceived(text: String)
}