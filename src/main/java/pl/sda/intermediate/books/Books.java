package pl.sda.intermediate.books;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;

@Getter
@Setter
class Books {

    private String bookName;
    private Integer bookId;
    private List<String> bookData = Arrays.asList();

    public Books(String bookName, Integer bookId, List<String> bookData) {
        this.bookName = bookName;
        this.bookId = bookId;
        this.bookData = bookData;
    }

}
