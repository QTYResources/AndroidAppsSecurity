package net.zenconsult.android.chucknorris

class CommsNotifier(
    private val event: CommsEvent
): Thread() {

    override fun run() {
        val c = Comms()
        event.onTextReceived(c.get())
    }
}