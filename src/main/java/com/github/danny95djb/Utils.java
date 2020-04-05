package com.github.danny95djb;

import java.io.*;
import java.nio.ByteBuffer;

public class Utils {

    public byte[] convertToBytes(Object object) throws IOException {
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            ObjectOutput out = new ObjectOutputStream(bos);
            out.writeObject(object);
            return bos.toByteArray();
        }
    }

    public Object convertFromBytes(byte[] bytes) throws IOException, ClassNotFoundException {
        try(ByteArrayInputStream bis = new ByteArrayInputStream(bytes)) {
            ObjectInput in = new ObjectInputStream(bis);
            return in.readObject();
        }
    }

    public int convertByteArrayToInt(byte[] bytes) {
        ByteBuffer wrapper = ByteBuffer.wrap(bytes);
        return wrapper.getShort() & 0xFFFF;
    }
}
