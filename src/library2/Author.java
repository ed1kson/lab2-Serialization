package library2;
import java.util.ArrayList;
import java.io.Serializable;


public class Author extends Human implements Serializable {
    static int nextId = 0;
    private final int id;
    private ArrayList<Book> books;
    private String name;

    Author(int id, String forename, String surename, String name, int age) {
        super(forename, surename, age);
        this.books = new ArrayList<>();
        this.name = name;
        this.id = id;
    }

    public Author(String forename, String surename, String name, int age) {
        super(forename, surename, age);
        this.books = new ArrayList<>();
        this.name = name;
        id = nextId++;
    }

    public Author(String forename, String surename, String name, int age, ArrayList<Book> books) {
        super(forename, surename, age);
        this.books = books;
        this.name = name;
        id = nextId++;
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getId() { return id; }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }

    public void addBook(Book book) {
        this.books.add(book);
    }

}
