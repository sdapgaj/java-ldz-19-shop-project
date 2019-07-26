package pl.sda.intermediate.books;

import lombok.Getter;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class BooksCollection {

    List<Books> booksCollection = new ArrayList<>();

    void readBooksData() {

        ClassLoader classLoader = this.getClass().getClassLoader();
        List<String> allLines = new ArrayList<>();

        try {
            Path path = Paths.get(classLoader.getResource("GUTINDEX.txt").toURI());
            allLines = Files.readAllLines(path);
        } catch (IOException | URISyntaxException e) {
            System.out.println(e);
        }

        if (allLines.size() > 0) {

            String regex = "^(.)+(\\d)+$";

            for (String line : allLines) {
                if (line.matches(regex)) {

                    String[] tmp = line.split(regex);

                    booksCollection.add(new Books(
                            "Test",
                            234,
                            Arrays.asList()
                    ));
                }
            }

        }

    }

    @Override
    public String toString() {
        return super.toString();
    }
}
