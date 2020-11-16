package com.example.demo;

import com.example.demo.exceptions.ReadFileException;
import com.example.demo.service.FileReaderService;
import com.example.demo.service.impl.FileReaderServiceImpl;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class FileReaderTest {
    private static final String PATH_TO_VALID_TEST_FILE = "src/test/resources/test_file.csv";
    private static final String PATH_TO_EMPTY_TEST_FILE = "src/test/resources/empty_test_file.csv";
    private static final String WRONG_PATH = "src/test/resources/file.csv";
    private static final String CONTENT = "I, want, to, find, job!\n"
            +"I will, find, job, of my dream!\nSoon!";
    private static final List<String> CONTENT_LIST = List.of("I, want, to, find, job!",
            "I will, find, job, of my dream!", "Soon!");

    private FileReaderService service = new FileReaderServiceImpl();

    @BeforeClass
    public static void createTestResources() {
        File validFile = new File(PATH_TO_VALID_TEST_FILE);
        try {
            FileWriter writer = new FileWriter(validFile, false);
            writer.write(CONTENT);
            writer.flush();
        } catch(IOException ex){
            throw new RuntimeException(ex.getMessage(), ex);
        }
    }

    @Test
    public void readingValidFile_Ok() {
        List<String> actual = service.read(PATH_TO_VALID_TEST_FILE);
        Assert.assertEquals(CONTENT_LIST, actual);
    }

    @Test
    public void readWithEmptyFile() {
        List<String> list = service.read(PATH_TO_EMPTY_TEST_FILE);
        Assert.assertTrue(list.isEmpty());
    }

    @Test(expected = ReadFileException.class)
    public void exceptionWithWrongPath() {
        service.read(WRONG_PATH);
    }

    @AfterClass
    public static void deleteTestFiles() {
        File validFile = new File(PATH_TO_VALID_TEST_FILE);
        validFile.delete();
    }
}
