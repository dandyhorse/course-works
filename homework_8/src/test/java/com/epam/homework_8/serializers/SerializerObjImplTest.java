package com.epam.homework_8.serializers;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import static org.junit.Assert.*;


public class SerializerObjImplTest {
    private String filePath;
    private String objectForSer;

    @Before
    public void setUp() throws Exception {
        filePath = "test.txt";
        objectForSer = "Fooooooooooooooo?";
    }

    @After
    public void tearDown() throws Exception {
        Files.deleteIfExists(Paths.get(filePath));
    }

    @Test
    public void readFromFile() throws Exception {
        Serializer<String> serializer = new SerializerObjImpl<>();
        serializer.serialize(filePath, objectForSer);
        Optional<String> strFromFile = serializer.deserialize(filePath);
        strFromFile.ifPresent(s -> assertEquals(objectForSer, s));
    }

    @Test
    public void writeIntoFile() throws Exception {
        Serializer<String> serializer = new SerializerObjImpl<>();
        Path path = Paths.get(filePath);
        Files.deleteIfExists(path);
        Files.createFile(path);
        long size = Files.size(path);
        serializer.serialize(filePath, objectForSer);
        long newSize = Files.size(path);
        assertNotEquals(size, newSize);
    }
}