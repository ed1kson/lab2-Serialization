package library;
import java.util.ArrayList;


public class Author extends Human {
    private ArrayList<Book> books;
    

    public Author(String forename, String surename, int age, ArrayList<Book> books) {
        super(forename, surename, age);
        this.books = books;
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }

    public void addBook(Book book) {
        this.books.add(book);
    }

}
