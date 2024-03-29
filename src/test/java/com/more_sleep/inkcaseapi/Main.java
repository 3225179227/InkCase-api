package com.more_sleep.inkcaseapi;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Base64;


/**
 *
 */
public class Main {
    public static void main(String[] args) {
// Generate secret key
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
        byte[] secretBytes = key.getEncoded();
        String base64Secret = Base64.getEncoder().encodeToString(secretBytes);

        System.out.println("New secret: " + base64Secret);
    }
}
