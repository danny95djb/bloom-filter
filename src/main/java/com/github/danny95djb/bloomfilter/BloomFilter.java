package com.github.danny95djb.bloomfilter;

import com.github.danny95djb.Utils;
import com.github.danny95djb.hashes.IBloomHash;
import com.github.danny95djb.hashes.MD5;
import com.github.danny95djb.hashes.SHA1;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.BitSet;
import java.util.List;

public class BloomFilter {

    private BitSet bitSet;
    private Utils utils;
    private List<IBloomHash> hashes;

    public BloomFilter() throws NoSuchAlgorithmException {
        this.bitSet = new BitSet(160);
        this.hashes = Arrays.asList( new SHA1(), new MD5());
        this.utils = new Utils();
    }

    public BloomFilter(List<IBloomHash> bloomHashes) {
        this.bitSet = new BitSet(160);
        this.hashes = bloomHashes;
        this.utils = new Utils();
    }

    public void add(Object input) throws IOException {
        byte[] bytes = utils.convertToBytes(input);
        hashes.forEach(hash -> {
            byte[] hashedBytes = hash.hash(bytes);
            int value = utils.convertByteArrayToInt(hashedBytes);
            this.bitSet.set(value);
        });

    }

    public boolean isPresent(Object input) throws IOException {
        byte[] bytes = utils.convertToBytes(input);
        boolean result = false;
        for(IBloomHash hash: hashes) {
            byte[] hashedBytes = hash.hash(bytes);
            int value = utils.convertByteArrayToInt(hashedBytes);
            result = this.bitSet.get(value);
            if(!result) {
                return false;
            }
        }
        return result;
    }

    protected int getBitSetSize() {
        return this.bitSet.size();
    }

    protected BitSet getBitSet() {
        return this.bitSet;
    }
}
