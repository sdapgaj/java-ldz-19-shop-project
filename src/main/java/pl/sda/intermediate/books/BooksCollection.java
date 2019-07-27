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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class BooksCollection {

    private List<Books> booksCollection = new ArrayList<>();

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

            String regex = "^(.*)(\\s+)(\\d+)$";

            for (String line : allLines) {
                if (line.matches(regex)) {

                    Pattern pattern = Pattern.compile(regex);
                    Matcher matcher = pattern.matcher(line.trim());
                    matcher.matches();

                    String name = matcher.group(1);
                    String id = matcher.group(3);

//                    System.out.println(String.format("%s - %s\n", id, name));

                    booksCollection.add(new Books(
                            name,
                            Integer.parseInt(id),
                            Arrays.asList()
                    ));

                }
            }

        }

    }

    public void getBooksCollection() {
        for (int i = 0; i < booksCollection.size(); i++) {

            System.out.println(String.format(
                    "%d. - %s: %s", i, booksCollection.get(i).getBookId(), booksCollection.get(i).getBookName()
            ));

//            System.out.println(String.format(
//                    "%d. - %s:", i, booksCollection.get(i).getBookId()
//            ));

        }
    }
}
