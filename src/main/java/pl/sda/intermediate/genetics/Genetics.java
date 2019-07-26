package pl.sda.intermediate.genetics;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

class Genetics {

    boolean changePossible(String s1, String s2) {

        if (s1.equals(s2)) {
            return true;
        }

        if (s2 == null) {
            return false;
        }

        Map<String, Integer> s1Map = new HashMap<>();
        Map<String, Integer> s2Map = new HashMap<>();

        Arrays.stream(s1.split(""))
                .collect(Collectors.groupingBy(v -> v))
                .forEach((k, v) -> s1Map.put(k, v.size()));

        Arrays.stream(s2.split(""))
                .collect(Collectors.groupingBy(v -> v))
                .forEach((k, v) -> s2Map.put(k, v.size()));

        for (String s : s1Map.keySet()) {
            if (!s2Map.containsKey(s)) {
                return false;
            }
            if (s1Map.get(s) < s2Map.get(s)) {
                return false;
            }
        }

        return true;

    }

    void readLinesFromFile() {

        ClassLoader classLoader = this.getClass().getClassLoader();

        try {

            Path path = Paths.get(classLoader.getResource("GeneticData").toURI());

            String value1 = "";
            String value2 = "";

            for (String item : Files.readAllLines(path)) {

                if (StringUtils.isEmpty(value1)) {
                    value1 = item;
                    continue;
                }

                if (StringUtils.isNotEmpty(value1) && StringUtils.isEmpty(value2)) {
                    value2 = item;
                }

                if (StringUtils.isNotEmpty(value1) && StringUtils.isNotEmpty(value2)) {
                    System.out.println(changePossible(value1, value2));
                    value1 = "";
                    value2 = "";
                }

            }

        } catch (IOException | URISyntaxException e) {
            System.out.println(e);
        }

    }

}
