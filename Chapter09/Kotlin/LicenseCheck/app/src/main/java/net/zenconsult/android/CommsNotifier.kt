package net.zenconsult.android

class CommsNotifier(
    private val event: CommsEvent
): Thread() {

    override fun run() {
        val c = Comms()
        event.onTextReceived(c.get())
    }
}