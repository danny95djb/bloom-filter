package com.github.danny95djb.bloomfilter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.*;

public class BloomFilterTest {

    private BloomFilter bloomFilter;
    private final String testString = "TEST";

    @BeforeEach
    public void setUp() throws NoSuchAlgorithmException {
        bloomFilter = new BloomFilter();
    }

    @Test
    public void returnsBitSetSize() {
        assertEquals(192, bloomFilter.getBitSetSize());
    }

    @Test
    public void shouldSetBitForTestObject() throws IOException {
        bloomFilter.add(testString);
        assertTrue(bloomFilter.getBitSet().get(32747)); //sha1
        assertTrue(bloomFilter.getBitSet().get(58784)); //md5
    }

    @Test
    public void shouldReturnFalseIfBitNotSetForObject() throws IOException {
        boolean result = bloomFilter.isPresent(testString);
        assertFalse(result);
    }

    @Test
    public void shouldReturnTrueIfBitIsSetForObject() throws IOException {
        bloomFilter.add(testString);
        boolean result = bloomFilter.isPresent(testString);
        assertTrue(result);
    }
}