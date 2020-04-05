package com.github.danny95djb.hashes;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA1 implements IBloomHash {

    private MessageDigest messageDigest;

    public SHA1() throws NoSuchAlgorithmException {
        this.messageDigest = MessageDigest.getInstance("SHA1");
    }

    public byte[] hash(byte[] bytes) {
        messageDigest.update(bytes);
        byte[] digest = messageDigest.digest();
        messageDigest.reset();
        return digest;
    }
}
