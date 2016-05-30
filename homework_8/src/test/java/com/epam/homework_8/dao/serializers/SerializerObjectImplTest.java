package com.epam.homework_8.dao.serializers;

import com.epam.homework_8.dao.serializers.interfaces.Serializer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.*;


public class SerializerObjectImplTest {
    private String filePath;
    private String stringForSer;

    @Before
    public void setUp() throws Exception {
        filePath = "test.ser";
        stringForSer = "Fooooooooooooooo?";
    }

    @After
    public void tearDown() throws Exception {
        Files.deleteIfExists(Paths.get(filePath));
    }

    @Test
    public void readFromFile() throws Exception {
        Serializer<String> serializer = new SerializerObjectImpl<>();
        serializer.serialize(filePath, stringForSer);
        String strFromFile = serializer.deserialize(filePath);
        assertEquals(strFromFile, stringForSer);
    }

    @Test
    public void writeIntoFile() throws Exception {
        Serializer<String> serializer = new SerializerObjectImpl<>();
        Path path = Paths.get(filePath);
        Files.deleteIfExists(path);
        Files.createFile(path);
        long size = Files.size(path);
        serializer.serialize(filePath, stringForSer);
        long newSize = Files.size(path);
        assertNotEquals(size, newSize);
    }
}