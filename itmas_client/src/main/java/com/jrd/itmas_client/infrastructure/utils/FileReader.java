package com.jrd.itmas_client.infrastructure.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by jakub on 23.06.16.
 */
public class FileReader {
    public static List<String[]> readUserData(String fileName) throws IOException {
        try (Stream<String> lines = Files.lines(Paths.get(fileName))) {
            return lines.map(s -> s.split(":"))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw e;
        }
    }
}
