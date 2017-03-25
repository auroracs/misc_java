package com.jengle.encryption;

public class EncryptionSample {

    public static void main(String[] args) {
    	
        String key = "Bar12345Bar12345"; // 128 bit key
        String initVector = "RandomInitVector"; // 16 bytes IV
        
        String str = "Indian Jim"; // string to be encrypted
        System.out.println("string to be encrypted: " + str);

        String enc = Encryptor.encrypt(key, initVector, str);
        System.out.println("encrypted string: " + enc);
        
        String dec = Decryptor.decrypt(key, initVector, enc);
        System.out.println("dencrypted string: " + dec);
        
    }

}
