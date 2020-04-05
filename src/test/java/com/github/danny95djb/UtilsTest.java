package com.github.danny95djb;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.ByteBuffer;

import static org.junit.jupiter.api.Assertions.*;

class UtilsTest {

    private Utils utils;

    @BeforeEach
    public void setUp() {
        utils = new Utils();
    }

    @Test
    public void convertToBytes() throws IOException, ClassNotFoundException {
        String testString = "test";
        byte[] result = utils.convertToBytes("test");
        assertEquals(utils.convertFromBytes(result), testString);
    }

    @Test
    public void convertByteArrayToInt() throws IOException {
        byte[] input = ByteBuffer.allocate(4).putInt(60000).array();
        int result = utils.convertByteArrayToInt(input);
        assertEquals(60000, result);
    }
}