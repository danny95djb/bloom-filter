package com.github.danny95djb.hashes;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 implements IBloomHash {

    private MessageDigest messageDigest;

    public MD5() throws NoSuchAlgorithmException {
        this.messageDigest = MessageDigest.getInstance("MD5");
    }

    public byte[] hash(byte[] bytes) {
        messageDigest.update(bytes);
        byte[] digest = messageDigest.digest();
        messageDigest.reset();
        return digest;
    }
}
