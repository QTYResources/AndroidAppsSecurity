package net.zenconsult.android;

public class CommsNotifier extends Thread {

    private final CommsEvent event;

    public CommsNotifier(CommsEvent evt) {
        event = evt;
    }

    public void run() {
        Comms c = new Comms();
        event.onTextReceived(c.get());
    }
}
