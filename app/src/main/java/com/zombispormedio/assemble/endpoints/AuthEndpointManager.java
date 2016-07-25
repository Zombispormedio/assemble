package com.zombispormedio.assemble.endpoints;

/**
 * Created by Master on 25/07/2016.
 */
public class AuthEndpointManager {
    private IAuthEndpoint endpoint;

    public AuthEndpointManager(IAuthEndpoint endpoint) {
        this.endpoint = endpoint;
    }
}
