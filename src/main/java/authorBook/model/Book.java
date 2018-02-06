package authorBook.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    private long id;
    private String name;
    private String author;
    private Double price;
    private String description;
    private  long userId;

    public Book(String name, String author, Double price, String description, long userId) {
        this.name = name;
        this.author = author;
        this.price = price;
        this.description = description;
        this.userId = userId;
    }

    public Book(String name, String author, Double price, String description) {
        this.name = name;
        this.author = author;
        this.price = price;
        this.description = description;
    }
}
