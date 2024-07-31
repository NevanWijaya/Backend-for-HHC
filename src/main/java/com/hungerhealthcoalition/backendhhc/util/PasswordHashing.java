package com.hungerhealthcoalition.backendhhc.util;

import java.sql.*;
import java.security.*;
import java.util.Base64;
public class PasswordHashing {
    String hashedPassword = "";

    public PasswordHashing(String password){
        try{
            byte[] salt = salt();
            hashedPassword = hashed(password, salt);
        }
        catch (NoSuchAlgorithmException ex){
            ex.printStackTrace();
        }
    }

    public String getPassword(){
        return hashedPassword;
    }

    public static String hashed(String password, byte[] salt) throws NoSuchAlgorithmException{
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        md.update(salt);
        byte[] hash = md.digest(password.getBytes());
        return Base64.getEncoder().encodeToString(hash);
    }
    public static byte[] salt() throws NoSuchAlgorithmException {
        SecureRandom rand = SecureRandom.getInstanceStrong();
        byte[] salt = new byte[16];
        rand.nextBytes(salt);
        return salt;
    }
}