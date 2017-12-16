package com.truelaurel.encore;

import java.net.URI;
import java.net.URISyntaxException;

class DomainNameExtractor {

    static String domain(String url) {
        try {
            return new URI(url).getHost();
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException("URL is illegal, unable to find domain name");
        }
    }
}
