package com.example.demo.service.impl;

import com.example.demo.exceptions.ReadFileException;
import com.example.demo.service.FileReaderService;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.stereotype.Service;

@Service
public class FileReaderServiceImpl implements FileReaderService {
    @Override
    public List<String> read(String path) throws ReadFileException {
        try (Stream<String> stream = Files.lines(Paths.get(path))) {
            return stream.collect(Collectors.toList());
        } catch (FileNotFoundException e) {
            throw new ReadFileException("File at the path " + path + " not found.", e);
        } catch (IOException e) {
            throw new ReadFileException("Cannot read the file at the path " + path, e);
        }
    }
}
