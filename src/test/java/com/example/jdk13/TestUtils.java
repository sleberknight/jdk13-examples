package com.example.jdk13;

import com.google.common.io.Resources;
import lombok.experimental.UtilityClass;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

@UtilityClass
@SuppressWarnings("squid:S00112")
public class TestUtils {

    @SuppressWarnings("UnstableApiUsage")
    public static String readFileOnClasspath(String fileName) {
        try {
            var uri = Resources.getResource(fileName).toURI();
            return Files.readString(Paths.get(uri));
        } catch (URISyntaxException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
