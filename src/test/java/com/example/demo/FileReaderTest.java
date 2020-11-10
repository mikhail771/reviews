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
    private static final String PATH_TO_VALID_TEST_FILE = "src/main/resources/test_file.csv";
    private static final String WRONG_PATH = "src/main/resources/file.csv";
    private static final String CONTENT = "I, wont, to, find, job!";

    private FileReaderService service = new FileReaderServiceImpl();
    @BeforeClass
    public static void createTestResources() {
        File validFile = new File(PATH_TO_VALID_TEST_FILE);
        try(FileWriter writer = new FileWriter(validFile, false))
        {
            writer.write(CONTENT);
            writer.flush();
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    @Test
    public void readingValidFile_Ok() {
        List<String> expected = List.of(CONTENT);
        List<String> actual = service.read(PATH_TO_VALID_TEST_FILE);
        Assert.assertEquals(expected, actual);
    }

    @Test(expected = ReadFileException.class)
    public void exceptionWithWrongPath () {
        service.read(WRONG_PATH);
    }

    @AfterClass
    public static void deleteTestFiles() {
        File validFile = new File(PATH_TO_VALID_TEST_FILE);
        validFile.delete();
    }
}
