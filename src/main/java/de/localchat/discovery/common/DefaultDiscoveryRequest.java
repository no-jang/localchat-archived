package de.localchat.discovery.common;

import de.localchat.discovery.DiscoveryRequest;

public class DefaultDiscoveryRequest implements DiscoveryRequest {
    private final String host;
    private final int webPort;

    public DefaultDiscoveryRequest(String host, int webPort) {
        this.host = host;
        this.webPort = webPort;
    }

    @Override
    public String getHost() {
        return host;
    }

    @Override
    public int getWebPort() {
        return webPort;
    }
}
