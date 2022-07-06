package de.localchat.discovery;

import java.io.IOException;

public interface DiscoveryBackend {
    void start() throws IOException;

    void stop() throws IOException;

    void broadcastRequest(DiscoveryRequest request) throws IOException;
}
