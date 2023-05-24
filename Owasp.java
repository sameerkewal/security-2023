package org.example;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Owasp {



    public static void doStuff() {
        String password = "doesnotreallymatter";
        String username = "Jerry";
        String passwordSaltWeak = "DeliberatelyInsecure1234";
        String passwordSaltStrong = "DeliberatelyInsecure1235";
        try {

            MessageDigest md = MessageDigest.getInstance("SHA-256");
            String salted = password + passwordSaltStrong + username;

            byte[] hash = md.digest(salted.getBytes(StandardCharsets.UTF_8));

            System.out.println(Base64.getEncoder().encodeToString(hash));



    } catch (Error | NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

    }


}
